

/* First created by JCasGen Thu Jul 14 13:32:47 CEST 2011 */
package sprint.uima.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Thu Jul 14 14:25:40 CEST 2011
 * XML source: /vol/home-vol3/wbi/wbi_stud/VLKB/Tim/vlkb/chemspotVSoscar3/desc/util/DDIToUCompareConverterAE.xml
 * @generated */
public class Sentence extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(Sentence.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Sentence() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Sentence(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Sentence(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Sentence(JCas jcas, int begin, int end) {
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
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_ID == null)
      jcasType.jcas.throwFeatMissing("ID", "sprint.uima.types.Sentence");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Sentence_Type)jcasType).casFeatCode_ID);}
    
  /** setter for ID - sets  
   * @generated */
  public void setID(String v) {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_ID == null)
      jcasType.jcas.throwFeatMissing("ID", "sprint.uima.types.Sentence");
    jcasType.ll_cas.ll_setStringValue(addr, ((Sentence_Type)jcasType).casFeatCode_ID, v);}    
   
    
  //*--------------*
  //* Feature: Pairs

  /** getter for Pairs - gets 
   * @generated */
  public FSArray getPairs() {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_Pairs == null)
      jcasType.jcas.throwFeatMissing("Pairs", "sprint.uima.types.Sentence");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Sentence_Type)jcasType).casFeatCode_Pairs)));}
    
  /** setter for Pairs - sets  
   * @generated */
  public void setPairs(FSArray v) {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_Pairs == null)
      jcasType.jcas.throwFeatMissing("Pairs", "sprint.uima.types.Sentence");
    jcasType.ll_cas.ll_setRefValue(addr, ((Sentence_Type)jcasType).casFeatCode_Pairs, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for Pairs - gets an indexed value - 
   * @generated */
  public Pair getPairs(int i) {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_Pairs == null)
      jcasType.jcas.throwFeatMissing("Pairs", "sprint.uima.types.Sentence");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Sentence_Type)jcasType).casFeatCode_Pairs), i);
    return (Pair)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Sentence_Type)jcasType).casFeatCode_Pairs), i)));}

  /** indexed setter for Pairs - sets an indexed value - 
   * @generated */
  public void setPairs(int i, Pair v) { 
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_Pairs == null)
      jcasType.jcas.throwFeatMissing("Pairs", "sprint.uima.types.Sentence");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Sentence_Type)jcasType).casFeatCode_Pairs), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Sentence_Type)jcasType).casFeatCode_Pairs), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    