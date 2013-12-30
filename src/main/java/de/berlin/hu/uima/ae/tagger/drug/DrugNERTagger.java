package de.berlin.hu.uima.ae.tagger.drug;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.util.List;

import org.apache.commons.io.output.NullOutputStream;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.u_compare.shared.semantic.Chemical;
import org.u_compare.shared.syntactic.Sentence;
import org.u_compare.shared.syntactic.Token;
import org.uimafit.util.JCasUtil;

import scala.Tuple2;
import scala.collection.Iterator;
import simplexnlp.core.Entity;

import de.berlin.hu.eumed.Config;
import de.berlin.hu.eumed.EntityTagger;
import de.berlin.hu.types.PubmedDocument;
import de.berlin.hu.util.Constants;

public class DrugNERTagger extends JCasAnnotator_ImplBase {
	public static final String PATH_TO_DRUG_MODEL = "pathToDrugModel";
	
	private EntityTagger tagger = null;
	
	public void loadCRFAndPipe(InputStream in) throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(in);
	    ois.close();
	}
	
	@Override
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		String drugModel = (String)aContext.getConfigParameterValue(PATH_TO_DRUG_MODEL);
		
		tagger = new EntityTagger();
		tagger.add(new Tuple2<String, String>("path", drugModel));
		tagger.add(new Tuple2<String, Config>("config", new Config(new String[0])));
		tagger.initialize();
	}
	
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		Iterable<PubmedDocument> documents = JCasUtil.iterate(aJCas, PubmedDocument.class);
		for (PubmedDocument doc : documents) {
			String docId = doc.getPmid();
			simplexnlp.core.Document simplexDoc = new simplexnlp.core.Document(docId, doc.getCoveredText());
			
			Iterable<Sentence> sentences = JCasUtil.selectCovered(aJCas, Sentence.class,  doc);
			int i = 0;
			for (Sentence sentence: sentences) {
				simplexnlp.example.Sentence simplexSentence = new simplexnlp.example.Sentence(sentence.getBegin() - doc.getBegin(), sentence.getEnd() - doc.getBegin() - 1);
				simplexSentence.id_$eq(docId + ".s" + i++);
				simplexSentence.origId_$eq(simplexSentence.id());
				simplexDoc.add(simplexSentence);
				//System.out.println("--- Sentence " + i + " ---");
				
				List<Token> tokens = JCasUtil.selectCovered(aJCas, Token.class, sentence);
				for (Token token : tokens) {
					simplexnlp.core.Token simplexToken = new simplexnlp.core.Token(token.getBegin() - sentence.getBegin(), token.getEnd() - sentence.getBegin() - 1);
					simplexToken.pos_$eq(token.getLabel());
					simplexSentence.add(simplexToken);
					//System.out.println(simplexToken.toString());
				}
			}
			
			// ugly hack to avoid debug output from model
			synchronized(System.out) {
				PrintStream out = System.out;
				System.setOut(new PrintStream(new NullOutputStream()));
				
				tagger.process(simplexDoc);
				
				System.setOut(out);
			}
			
			Iterator<simplexnlp.core.Sentence> simplexSentences = simplexDoc.sentences().toIterator();
			while (simplexSentences.hasNext()) {
				simplexnlp.core.Sentence sentence = simplexSentences.next();
				 
				Iterator<Entity> entities = sentence.entities().toIterator();
				while (entities.hasNext()) {
					Entity entity = entities.next();
			            
					createDrugAnnotation(aJCas, sentence.start() + entity.start() + doc.getBegin(), sentence.start() + doc.getBegin() + entity.end() + 1, entity.className());
				}
			}
		}
	}
	 
	
	private Chemical createDrugAnnotation(JCas aJCas, int begin, int end, String type) {
   		Chemical drug = new Chemical(aJCas);
   		drug.setBegin(begin);
   		drug.setEnd(end);
   		drug.setSource(Constants.DRUG);
   		drug.setEntityType(type);
   		drug.addToIndexes();
   		//System.out.println("'" + drug.getCoveredText() + "'");
   		
   		return drug;
   	}
}
