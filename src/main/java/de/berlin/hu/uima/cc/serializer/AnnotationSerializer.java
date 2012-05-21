package de.berlin.hu.uima.cc.serializer;

import de.berlin.hu.util.AnnotationObject;
import de.berlin.hu.util.Document;
import de.berlin.hu.util.IO;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.collection.base_cpm.CasObjectProcessor;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;
import org.apache.uima.util.ProcessTrace;
import org.u_compare.shared.semantic.NamedEntity;
import org.u_compare.shared.semantic.chemical.Chemical;

/**
 * An example of CAS Consumer. <br>
 * AnnotationPrinter prints to an output file all annotations in the CAS. <br>
 * Parameters needed by the AnnotationPrinter are
 * <ol>
 * <li> "outputFile" : file to which the output files should be written.</li>
 * </ol>
 * <br>
 * These parameters are set in the initialize method to the values specified in the descriptor file.
 * <br>
 * These may also be set by the application by using the setConfigParameterValue methods.
 * 
 * 
 */

public class AnnotationSerializer extends CasConsumer_ImplBase implements CasObjectProcessor {

  Map<String, Document> documents = new HashMap<String,Document>();

  
  //int no_comp = 0;
  //int no_match = 0;

  public AnnotationSerializer() {
  }

  /**
   * Initializes this CAS Consumer with the parameters specified in the descriptor.
   * 
   * @throws ResourceInitializationException
   *           if there is error in initializing the resources
   */
  public void initialize() throws ResourceInitializationException {


  }

  /**
   * Processes the CasContainer which was populated by the TextAnalysisEngines. <br>
   * In this case, the CAS index is iterated over selected annotations and printed out into an
   * output file
   * 
   * @param aCAS
   *          CasContainer which has been populated by the TAEs
   * 
   * @throws ResourceProcessException
   *           if there is an error in processing the Resource
   * 
   * @see org.apache.uima.collection.base_cpm.CasObjectProcessor#processCas(CAS)
   */
  public synchronized void processCas(CAS aCAS) throws ResourceProcessException {
    JCas jcas;
    try {
      jcas = aCAS.getJCas();
    } catch (CASException e) {
      throw new ResourceProcessException(e);
    }

    
    String docUri = null;
    String pmid = null;
    
   	
    Iterator<Annotation> it = jcas.getAnnotationIndex(SourceDocumentInformation.type).iterator();
    if (it.hasNext()) {
      SourceDocumentInformation srcDocInfo = (SourceDocumentInformation) it.next();
      docUri = srcDocInfo.getUri();
      
      //adjusted: "+1" to ignore the slash for the pmid
      pmid = docUri.substring(docUri.lastIndexOf("/")+1, docUri.length()-4); //getting the file name (PMID) and using it for the db_query
      //System.out.println(pmid);
      System.out.println(docUri + " -> " + pmid);

    }

    // iterate and print annotations
    //Iterator annotationIter = jcas.getAnnotationIndex().iterator();
    Iterator<Annotation> chemIter = jcas.getAnnotationIndex(Chemical.type).iterator(); 
    
    //NamedEntity statt Chemical nutzen dort getsource() --> linnaeus, banner, oscar3, oscar4)
    
    Map<String, AnnotationObject> ent = new HashMap<String, AnnotationObject>();
    
    
    
    while (chemIter.hasNext()) {
    	Chemical annot = (Chemical) chemIter.next();
    	    	   	
    	String entity = annot.getCoveredText();
    	entity = entity.replace('\n', ' ');
    	entity = entity.replace('\r', ' ');
    	entity = entity.toLowerCase();
    	//entities.put(entity, annot);
    	
    	if (ent.containsKey(entity)){
    		AnnotationObject ao = ent.get(entity);
    		if ("linnaeus".equals(annot.getSource())) {
    			ao.setDbid(annot.getId()); //nochmal zuweisen zum HashSet oder bereits ge�ndert ?
			}
    	} else{    		
    		AnnotationObject ao = new AnnotationObject();
    		ao.setAnnot(annot.getCoveredText());
    		ao.setBegin(annot.getBegin());
    		ao.setEnd(annot.getEnd());
    		ao.setConfidence(annot.getConfidence()); //for drug ?
    		if ("linnaeus".equals(annot.getSource())) {
    			ao.setDbid(annot.getId());
    		}
    		ent.put(entity,ao);
    	}
    }
  //Document to hold the PMID-Entry
    Document doc = new Document (pmid,ent);
    //doc.setPmid(pmid);   
    //Append the computed Map (Name, AnnotationObject) to the document (PMID)
    //doc.setAnnotations(ent);
    //doc.setFp_list(fp_list)
    
    //Append the document to the whole corpus of documents
    System.out.println(doc.toString());
    documents.put(pmid, doc);
    //documents.toString();
    
    //Structure:
    //documents (PMID1, Document1)
    //doc (PMID, Map<Name, AnnotationObject>, no_of_annot>
    //AnnotationObject (annot, begin, end)
    
    
  }
  


  
  /**
   * Called when a batch of processing is completed.
   * 
   * @param aTrace
   *          ProcessTrace object that will log events in this method.
   * @throws ResourceProcessException
   *           if there is an error in processing the Resource
   * @throws IOException
   *           if there is an IO Error
   * 
   * @see org.apache.uima.collection.CasConsumer#batchProcessComplete(ProcessTrace)
   */
  public void batchProcessComplete(ProcessTrace aTrace) throws ResourceProcessException,
          IOException {
    // nothing to do in this case as AnnotationPrinter doesnot do
    // anything cumulatively
  }

  /**
   * Called when the entire collection is completed.
   * 
   * @param aTrace
   *          ProcessTrace object that will log events in this method.
   * @throws ResourceProcessException
   *           if there is an error in processing the Resource
   * @throws IOException
   *           if there is an IO Error
   * @see org.apache.uima.collection.CasConsumer#collectionProcessComplete(ProcessTrace)
   */
  public void collectionProcessComplete(ProcessTrace aTrace) throws ResourceProcessException,
          IOException{
	
	  //Here serialization of the HashMap
	  System.out.println("Anzahl pmids: " + documents.size());
	  //System.out.println(documents.toString());
	  IO.serialize(documents, "alldocuments_serialized.txt");
	  
	  
	  //System.out.println("no_comp:"+ no_comp+ "\n" + "no_match: "+ no_match);
/*    if (fileWriter != null) {
      fileWriter.close();
    }*/
  }

  /**
   * Reconfigures the parameters of this Consumer. <br>
   * This is used in conjunction with the setConfigurationParameterValue to set the configuration
   * parameter values to values other than the ones specified in the descriptor.
   * 
   * @throws ResourceConfigurationException
   *           if the configuration parameter settings are invalid
   * 
   * @see org.apache.uima.resource.ConfigurableResource#reconfigure()
   */
  public void reconfigure() throws ResourceConfigurationException {
    super.reconfigure();

  }

  /**
   * Called if clean up is needed in case of exit under error conditions.
   * 
   * @see org.apache.uima.resource.Resource#destroy()
   */
  public void destroy() {

  }

}
