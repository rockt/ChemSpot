package de.berlin.hu.uima.ae;

import de.berlin.hu.types.PubmedDocument;
import de.berlin.hu.util.Constants;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.u_compare.shared.semantic.NamedEntity;
import org.u_compare.shared.semantic.chemical.Chemical;
import org.u_compare.shared.syntactic.Sentence;
import org.uimafit.util.JCasUtil;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Merges annotations from the CRF and the dictionary, favoring entities extracted by the CRF over the dictionary.
 */
public class AnnotationMergerAE extends JCasAnnotator_ImplBase {

	/* (non-Javadoc)
	 * @see org.apache.uima.analysis_component.JCasAnnotator_ImplBase#process(org.apache.uima.jcas.JCas)
	 */
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		List<PubmedDocument> documents = new ArrayList<PubmedDocument>();
		for (PubmedDocument doc : JCasUtil.iterate(aJCas, PubmedDocument.class)) {
			documents.add(doc);
		}
		
		if (documents.isEmpty()) {
			documents.add(null);
		}
		
		for (PubmedDocument document : documents) {
			//System.out.println("\nMerging annotations of document " + (document != null ? document.getPmid() : ""));
				
			Iterator<NamedEntity> entityIterator = document != null ? JCasUtil.iterator(document, NamedEntity.class, true, true) :  JCasUtil.iterator(aJCas, NamedEntity.class);
			
			List<NamedEntity> entities = new ArrayList<NamedEntity>();
			List<String> abbreviations = new ArrayList<String>();
			Map<String, List<NamedEntity>> chemicalsMap = new HashMap<String, List<NamedEntity>>();
			while (entityIterator.hasNext()) {
				NamedEntity namedEntity = (NamedEntity) entityIterator.next();

				if (!Constants.GOLDSTANDARD.equals(namedEntity.getSource())) {
					entities.add(namedEntity);
					
					String chemName = namedEntity.getCoveredText().trim().toLowerCase();
					if (!chemicalsMap.containsKey(chemName)) {
						chemicalsMap.put(chemName, new ArrayList<NamedEntity>());
					}
					chemicalsMap.get(chemName).add(namedEntity);
				}
			}
			
			Comparator<NamedEntity> comp = new Comparator<NamedEntity>() {
				public int compare(NamedEntity m1, NamedEntity m2) {
					return m1.getBegin() - m2.getBegin(); 
				}
			};
				
			Collections.sort(entities, comp);
			
			List<NamedEntity> chemicals = new ArrayList<NamedEntity>();
			boolean filtered = false;
			
			//FIXME: use drug if it is identical to CRF match to obtain CAS Registry ID
			NamedEntity lastEntity = null;
			List<String> nonChemicalAbbreviations = new ArrayList<String>();
			Iterator<Sentence> sentenceIterator = document != null ? JCasUtil.iterator(document, Sentence.class, true, true) :  JCasUtil.iterator(aJCas, Sentence.class);
			Sentence sentence = sentenceIterator.hasNext() ? sentenceIterator.next() : null;
			for (NamedEntity entity : entities) {
				if (sentence != null) {
					while (sentence.getEnd() < entity.getBegin() && sentenceIterator.hasNext()) {
						sentence = sentenceIterator.next();
						lastEntity = null;
					}
				}
				
				if (nonChemicalAbbreviations.contains(entity.getCoveredText().trim().toLowerCase())) {
					entity.removeFromIndexes(aJCas);
					filtered = true;
					//System.out.println("Filtered subsequent mention of non-Chemical abbreviation: " + entity.getCoveredText() + " -> " + entity.getId());
				}
				
				boolean isChemAbbreviation = abbreviations.contains(entity.getCoveredText().trim().toLowerCase());
				if (!isChemAbbreviation && !filtered && Constants.ABBREV.equals(entity.getSource())) {
					String name = null;
					
					if (entity.getId() != null) {
						name = entity.getId().trim().toLowerCase();
						entity.setId(null);
					}
					
					if (name != null && chemicalsMap.containsKey(name)) {
						List<NamedEntity> chems = chemicalsMap.get(name);
						
						for (NamedEntity c : chems) {
							if (!Constants.ABBREV.equals(c.getSource())) {
								isChemAbbreviation = true;
								break;
							}
						}
					}
					
					if (isChemAbbreviation) {
						abbreviations.add(entity.getCoveredText().trim().toLowerCase());
					} else {
						entity.removeFromIndexes(aJCas);
						filtered = true;
						nonChemicalAbbreviations.add(entity.getCoveredText().trim().toLowerCase());
						
						List<NamedEntity> filteredEntities = new ArrayList<NamedEntity>();
						for (NamedEntity e : chemicals) {
							if (entity.getCoveredText().trim().equalsIgnoreCase(e.getCoveredText().trim())) {
								filteredEntities.add(e);
								e.removeFromIndexes();
								//System.out.println("Removing previous occurence of " + entity.getCoveredText());
							}
						}
						
						chemicals.removeAll(filteredEntities);
					}
				}
				
				if (!filtered && lastEntity != null && crosses(lastEntity, entity)) {					
					if (Constants.ABBREV.equals(lastEntity.getSource())) {
						if (isReplaceByAbbreviation(lastEntity, entity) && (!Constants.ABBREV.equals(entity.getSource()) || isChemAbbreviation)) {
							//System.out.printf("replacing %s annotation %s at [%d-%d], because it was identified as an abbreviation for %s%n", entity.getSource(), entity.getCoveredText(), entity.getBegin(), entity.getEnd(), lastEntity.getId());
							entity.removeFromIndexes(aJCas);
							filtered = true;
						}
					} else if (Constants.ABBREV.equals(entity.getSource())) {
						boolean isRemove = false;
						
						if (isChemAbbreviation && isReplaceByAbbreviation(entity, lastEntity)) {
							//System.out.printf("replacing %s annotation %s at [%d-%d], because it was identified as an abbreviation for %s%n", lastEntity.getSource(), lastEntity.getCoveredText(), lastEntity.getBegin(), lastEntity.getEnd(), entity.getId());
							isRemove = true;
						} else if (!isChemAbbreviation) {
							//System.out.printf("removing %s annotation %s at [%d-%d], because it does not appear to be a chemical abbreviation (%s = %s)%n", lastEntity.getSource(), lastEntity.getCoveredText(), lastEntity.getBegin(), lastEntity.getEnd(), entity.getCoveredText(), entity.getId());
							isRemove = true;
							filtered = true;
						}
						
						if (isRemove) {
							lastEntity.removeFromIndexes(aJCas);
							chemicals.remove(lastEntity);
						}
					} else if (Constants.DICTIONARY.equals(lastEntity.getSource()) 
							&& !Constants.DICTIONARY.equals(entity.getSource())) {
						lastEntity.removeFromIndexes(aJCas);
						chemicals.remove(lastEntity);
					} else if (!Constants.DICTIONARY.equals(lastEntity.getSource()) 
							&& Constants.DICTIONARY.equals(entity.getSource())) {
						entity.removeFromIndexes(aJCas);
						filtered = true;
					} else if (Constants.SUM_TAGGER.equals(lastEntity.getSource()) 
							&& !Constants.SUM_TAGGER.equals(entity.getSource())) {
						lastEntity.removeFromIndexes(aJCas);
						chemicals.remove(lastEntity);
					} else if (!Constants.SUM_TAGGER.equals(lastEntity.getSource()) 
							&& Constants.SUM_TAGGER.equals(entity.getSource())) {
						entity.removeFromIndexes(aJCas);
						filtered = true;
					} else if (Constants.CRF.equals(entity.getSource())) {
						lastEntity.removeFromIndexes(aJCas);
						chemicals.remove(lastEntity);
					}
				}
				
				if (lastEntity != null && !filtered && !crosses(lastEntity, entity) && entity.getBegin() - lastEntity.getEnd() < 10) {
					if (entity.getCAS().getDocumentText().substring(lastEntity.getEnd(), entity.getBegin()).matches("\\s+")) {
						entity.setBegin(lastEntity.getBegin());
						lastEntity.removeFromIndexes();
						chemicals.remove(lastEntity);
					}
				}
				
				if (!filtered) {
					chemicals.add(entity);
					lastEntity = entity;
				}
				filtered = false;
			}
			convertNamedEntitiesToChemicals(aJCas, chemicals);
		}
	}

	private void convertNamedEntitiesToChemicals(JCas aJCas,
			List<NamedEntity> chemicals) {
		for (NamedEntity entity : chemicals) {
			Chemical chemical = new Chemical(aJCas, entity.getBegin(), entity.getEnd());
			chemical.setSource(entity.getSource());
			chemical.setId(/*entity.getSource() + ": " + */entity.getId());
			chemical.setConfidence(entity.getConfidence());
			chemical.setEntityType(entity.getEntityType());
			chemical.addToIndexes();
			entity.removeFromIndexes(aJCas);
		}
	}

	private boolean crosses(NamedEntity lastEntity, NamedEntity entity) {
		if (lastEntity == null) {
			return false;
		}
		
		if (lastEntity.getBegin() <= entity.getBegin() && entity.getEnd() <= lastEntity.getEnd()) {
			return true;
		}
		
		if (entity.getBegin() <= lastEntity.getBegin() && lastEntity.getEnd() <= entity.getEnd()) {
			return true;
		}
		
		if (lastEntity.getBegin() <= entity.getBegin() && entity.getBegin() <= lastEntity.getEnd()) {
			return true;
		}
		return false;
	}

	private boolean isReplaceByAbbreviation(NamedEntity abbr, NamedEntity otherEntity) {
		if (otherEntity == null) {
			return true;
		}
		
		String abbrText = abbr.getCoveredText();
		String othText = otherEntity.getCoveredText();
		
		// prefer longer abbreviation
		if (Constants.ABBREV.equals(otherEntity.getSource())) {
			return abbr.getCoveredText().length() > otherEntity.getCoveredText().length();
		} else {
			// replace annotations such as "ATP-site-directed"
			if (othText.matches(Pattern.quote(abbrText) + "-[a-z\\-]+")) {
				return true;
			}
		}
		
		// prefer more meaningful abbreviation annotation
		return abbr.getBegin() == otherEntity.getBegin() && abbr.getEnd() == otherEntity.getEnd();
	}
}
