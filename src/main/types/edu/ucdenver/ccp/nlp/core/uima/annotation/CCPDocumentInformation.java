

/* First created by JCasGen Tue Oct 02 12:01:59 CEST 2012 */
package edu.ucdenver.ccp.nlp.core.uima.annotation;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.jcas.cas.StringArray;


/** The CCPDocumentInformation annotation includes document metadata such as the document ID, document collection ID, secondary document IDs, document size, etc.
 * Updated by JCasGen Tue Oct 02 12:01:59 CEST 2012
 * XML source: D:/ChemSpot/git/ChemSpot/desc/cr/CraftCR.xml
 * @generated */
public class CCPDocumentInformation extends TOP {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(CCPDocumentInformation.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated  */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected CCPDocumentInformation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public CCPDocumentInformation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public CCPDocumentInformation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: documentID

  /** getter for documentID - gets The document ID is a String representing a unique identifier for a particular document within a particular document collection.
   * @generated */
  public String getDocumentID() {
    if (CCPDocumentInformation_Type.featOkTst && ((CCPDocumentInformation_Type)jcasType).casFeat_documentID == null)
      jcasType.jcas.throwFeatMissing("documentID", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPDocumentInformation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((CCPDocumentInformation_Type)jcasType).casFeatCode_documentID);}
    
  /** setter for documentID - sets The document ID is a String representing a unique identifier for a particular document within a particular document collection. 
   * @generated */
  public void setDocumentID(String v) {
    if (CCPDocumentInformation_Type.featOkTst && ((CCPDocumentInformation_Type)jcasType).casFeat_documentID == null)
      jcasType.jcas.throwFeatMissing("documentID", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPDocumentInformation");
    jcasType.ll_cas.ll_setStringValue(addr, ((CCPDocumentInformation_Type)jcasType).casFeatCode_documentID, v);}    
   
    
  //*--------------*
  //* Feature: documentCollectionID

  /** getter for documentCollectionID - gets The document collection ID is an Integer that uniquely identifies a particular document collection.
   * @generated */
  public int getDocumentCollectionID() {
    if (CCPDocumentInformation_Type.featOkTst && ((CCPDocumentInformation_Type)jcasType).casFeat_documentCollectionID == null)
      jcasType.jcas.throwFeatMissing("documentCollectionID", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPDocumentInformation");
    return jcasType.ll_cas.ll_getIntValue(addr, ((CCPDocumentInformation_Type)jcasType).casFeatCode_documentCollectionID);}
    
  /** setter for documentCollectionID - sets The document collection ID is an Integer that uniquely identifies a particular document collection. 
   * @generated */
  public void setDocumentCollectionID(int v) {
    if (CCPDocumentInformation_Type.featOkTst && ((CCPDocumentInformation_Type)jcasType).casFeat_documentCollectionID == null)
      jcasType.jcas.throwFeatMissing("documentCollectionID", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPDocumentInformation");
    jcasType.ll_cas.ll_setIntValue(addr, ((CCPDocumentInformation_Type)jcasType).casFeatCode_documentCollectionID, v);}    
   
    
  //*--------------*
  //* Feature: documentSize

  /** getter for documentSize - gets The size of a document is logged as the number of characters it contains.
   * @generated */
  public int getDocumentSize() {
    if (CCPDocumentInformation_Type.featOkTst && ((CCPDocumentInformation_Type)jcasType).casFeat_documentSize == null)
      jcasType.jcas.throwFeatMissing("documentSize", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPDocumentInformation");
    return jcasType.ll_cas.ll_getIntValue(addr, ((CCPDocumentInformation_Type)jcasType).casFeatCode_documentSize);}
    
  /** setter for documentSize - sets The size of a document is logged as the number of characters it contains. 
   * @generated */
  public void setDocumentSize(int v) {
    if (CCPDocumentInformation_Type.featOkTst && ((CCPDocumentInformation_Type)jcasType).casFeat_documentSize == null)
      jcasType.jcas.throwFeatMissing("documentSize", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPDocumentInformation");
    jcasType.ll_cas.ll_setIntValue(addr, ((CCPDocumentInformation_Type)jcasType).casFeatCode_documentSize, v);}    
   
    
  //*--------------*
  //* Feature: secondaryDocumentIDs

  /** getter for secondaryDocumentIDs - gets This StringArray is used for secondary document ID storage. For example, in the biomedical domain, a particular document might be associated with a PubMed ID, however it might also have a deprecated Medline ID, or perhaps a PubMed Central ID, either of which could be stored in this StringArray. It is recommended that the type of ID along with the ID itself be stored, e.g. "MedlineID:12345".
   * @generated */
  public StringArray getSecondaryDocumentIDs() {
    if (CCPDocumentInformation_Type.featOkTst && ((CCPDocumentInformation_Type)jcasType).casFeat_secondaryDocumentIDs == null)
      jcasType.jcas.throwFeatMissing("secondaryDocumentIDs", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPDocumentInformation");
    return (StringArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((CCPDocumentInformation_Type)jcasType).casFeatCode_secondaryDocumentIDs)));}
    
  /** setter for secondaryDocumentIDs - sets This StringArray is used for secondary document ID storage. For example, in the biomedical domain, a particular document might be associated with a PubMed ID, however it might also have a deprecated Medline ID, or perhaps a PubMed Central ID, either of which could be stored in this StringArray. It is recommended that the type of ID along with the ID itself be stored, e.g. "MedlineID:12345". 
   * @generated */
  public void setSecondaryDocumentIDs(StringArray v) {
    if (CCPDocumentInformation_Type.featOkTst && ((CCPDocumentInformation_Type)jcasType).casFeat_secondaryDocumentIDs == null)
      jcasType.jcas.throwFeatMissing("secondaryDocumentIDs", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPDocumentInformation");
    jcasType.ll_cas.ll_setRefValue(addr, ((CCPDocumentInformation_Type)jcasType).casFeatCode_secondaryDocumentIDs, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for secondaryDocumentIDs - gets an indexed value - This StringArray is used for secondary document ID storage. For example, in the biomedical domain, a particular document might be associated with a PubMed ID, however it might also have a deprecated Medline ID, or perhaps a PubMed Central ID, either of which could be stored in this StringArray. It is recommended that the type of ID along with the ID itself be stored, e.g. "MedlineID:12345".
   * @generated */
  public String getSecondaryDocumentIDs(int i) {
    if (CCPDocumentInformation_Type.featOkTst && ((CCPDocumentInformation_Type)jcasType).casFeat_secondaryDocumentIDs == null)
      jcasType.jcas.throwFeatMissing("secondaryDocumentIDs", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPDocumentInformation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((CCPDocumentInformation_Type)jcasType).casFeatCode_secondaryDocumentIDs), i);
    return jcasType.ll_cas.ll_getStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((CCPDocumentInformation_Type)jcasType).casFeatCode_secondaryDocumentIDs), i);}

  /** indexed setter for secondaryDocumentIDs - sets an indexed value - This StringArray is used for secondary document ID storage. For example, in the biomedical domain, a particular document might be associated with a PubMed ID, however it might also have a deprecated Medline ID, or perhaps a PubMed Central ID, either of which could be stored in this StringArray. It is recommended that the type of ID along with the ID itself be stored, e.g. "MedlineID:12345".
   * @generated */
  public void setSecondaryDocumentIDs(int i, String v) { 
    if (CCPDocumentInformation_Type.featOkTst && ((CCPDocumentInformation_Type)jcasType).casFeat_secondaryDocumentIDs == null)
      jcasType.jcas.throwFeatMissing("secondaryDocumentIDs", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPDocumentInformation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((CCPDocumentInformation_Type)jcasType).casFeatCode_secondaryDocumentIDs), i);
    jcasType.ll_cas.ll_setStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((CCPDocumentInformation_Type)jcasType).casFeatCode_secondaryDocumentIDs), i, v);}
   
    
  //*--------------*
  //* Feature: classificationType

  /** getter for classificationType - gets This String provides a means for classifying a particular document.
   * @generated */
  public String getClassificationType() {
    if (CCPDocumentInformation_Type.featOkTst && ((CCPDocumentInformation_Type)jcasType).casFeat_classificationType == null)
      jcasType.jcas.throwFeatMissing("classificationType", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPDocumentInformation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((CCPDocumentInformation_Type)jcasType).casFeatCode_classificationType);}
    
  /** setter for classificationType - sets This String provides a means for classifying a particular document. 
   * @generated */
  public void setClassificationType(String v) {
    if (CCPDocumentInformation_Type.featOkTst && ((CCPDocumentInformation_Type)jcasType).casFeat_classificationType == null)
      jcasType.jcas.throwFeatMissing("classificationType", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPDocumentInformation");
    jcasType.ll_cas.ll_setStringValue(addr, ((CCPDocumentInformation_Type)jcasType).casFeatCode_classificationType, v);}    
   
    
  //*--------------*
  //* Feature: encoding

  /** getter for encoding - gets 
   * @generated */
  public String getEncoding() {
    if (CCPDocumentInformation_Type.featOkTst && ((CCPDocumentInformation_Type)jcasType).casFeat_encoding == null)
      jcasType.jcas.throwFeatMissing("encoding", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPDocumentInformation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((CCPDocumentInformation_Type)jcasType).casFeatCode_encoding);}
    
  /** setter for encoding - sets  
   * @generated */
  public void setEncoding(String v) {
    if (CCPDocumentInformation_Type.featOkTst && ((CCPDocumentInformation_Type)jcasType).casFeat_encoding == null)
      jcasType.jcas.throwFeatMissing("encoding", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPDocumentInformation");
    jcasType.ll_cas.ll_setStringValue(addr, ((CCPDocumentInformation_Type)jcasType).casFeatCode_encoding, v);}    
  }

    