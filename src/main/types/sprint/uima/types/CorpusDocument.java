

/* First created by JCasGen Thu Jul 14 13:32:47 CEST 2011 */
package sprint.uima.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.DocumentAnnotation;


/** 
 * Updated by JCasGen Thu Jul 14 14:25:40 CEST 2011
 * XML source: /vol/home-vol3/wbi/wbi_stud/VLKB/Tim/vlkb/chemspotVSoscar3/desc/util/ddiToUCompareConverterAEDescriptor.xml
 * @generated */
public class CorpusDocument extends DocumentAnnotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(CorpusDocument.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected CorpusDocument() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public CorpusDocument(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public CorpusDocument(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public CorpusDocument(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
 
    
  //*--------------*
  //* Feature: ID

  /** getter for ID - gets 
   * @generated */
  public String getID() {
    if (CorpusDocument_Type.featOkTst && ((CorpusDocument_Type)jcasType).casFeat_ID == null)
      jcasType.jcas.throwFeatMissing("ID", "sprint.uima.types.CorpusDocument");
    return jcasType.ll_cas.ll_getStringValue(addr, ((CorpusDocument_Type)jcasType).casFeatCode_ID);}
    
  /** setter for ID - sets  
   * @generated */
  public void setID(String v) {
    if (CorpusDocument_Type.featOkTst && ((CorpusDocument_Type)jcasType).casFeat_ID == null)
      jcasType.jcas.throwFeatMissing("ID", "sprint.uima.types.CorpusDocument");
    jcasType.ll_cas.ll_setStringValue(addr, ((CorpusDocument_Type)jcasType).casFeatCode_ID, v);}    
  }

    