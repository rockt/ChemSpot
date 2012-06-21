package de.berlin.hu.uima.ae;

import de.berlin.hu.util.Constants;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.u_compare.shared.semantic.NamedEntity;
import org.u_compare.shared.semantic.chemical.Chemical;
import org.u_compare.shared.syntactic.Sentence;
import org.uimafit.util.JCasUtil;

import java.util.*;

/**
 * Merges annotations from the CRF and the dictionary, favoring entities extracted by the CRF over the dictionary.
 */
public class AnnotationMergerAE extends JCasAnnotator_ImplBase {

	/* (non-Javadoc)
	 * @see org.apache.uima.analysis_component.JCasAnnotator_ImplBase#process(org.apache.uima.jcas.JCas)
	 */
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		Iterator<Sentence> sentenceIterator = JCasUtil.iterator(aJCas, Sentence.class);
		
		while (sentenceIterator.hasNext()) {
			Sentence sentence = (Sentence) sentenceIterator.next();
			
			Iterator<NamedEntity> entityIterator = JCasUtil.iterator(sentence, NamedEntity.class, true, true);
			
			List<NamedEntity> entities = new ArrayList<NamedEntity>();
			while (entityIterator.hasNext()) {
				NamedEntity namedEntity = (NamedEntity) entityIterator.next();
				if (!Constants.GOLDSTANDARD.equals(namedEntity.getSource())) {
					entities.add(namedEntity);
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
			
			//FIXME: use drug if it is identical to CRF match to obtain ChemIDplus ID
			
			NamedEntity lastEntity = null;
			for (NamedEntity entity : entities) {
				if (lastEntity != null 
						&& Constants.DICTIONARY.equals(lastEntity.getSource()) 
						&& !Constants.DICTIONARY.equals(entity.getSource()) 
						&& crosses(lastEntity, entity)) {
					lastEntity.removeFromIndexes(aJCas);
				} else if (lastEntity != null 
						&& !Constants.DICTIONARY.equals(lastEntity.getSource()) 
						&& Constants.DICTIONARY.equals(entity.getSource()) 
						&& crosses(lastEntity, entity)) {
					entity.removeFromIndexes(aJCas);
					filtered = true;
				}
				
				if (!filtered && !Constants.DICTIONARY.equals(entity.getSource())) {
					chemicals.add(entity);
				}
				
				if (!filtered) {
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
			chemical.setId(entity.getId());
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

}
