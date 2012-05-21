

/* First created by JCasGen Thu Jul 14 13:32:47 CEST 2011 */
package sprint.uima.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Thu Jul 14 14:25:40 CEST 2011
 * XML source: /vol/home-vol3/wbi/wbi_stud/VLKB/Tim/vlkb/chemspotVSoscar3/desc/util/ddiToUCompareConverterAEDescriptor.xml
 * @generated */
public class Token extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(Token.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Token() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Token(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Token(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Token(JCas jcas, int begin, int end) {
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
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_ID == null)
      jcasType.jcas.throwFeatMissing("ID", "sprint.uima.types.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_ID);}
    
  /** setter for ID - sets  
   * @generated */
  public void setID(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_ID == null)
      jcasType.jcas.throwFeatMissing("ID", "sprint.uima.types.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_ID, v);}    
   
    
  //*--------------*
  //* Feature: POS

  /** getter for POS - gets 
   * @generated */
  public String getPOS() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_POS == null)
      jcasType.jcas.throwFeatMissing("POS", "sprint.uima.types.Token");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Token_Type)jcasType).casFeatCode_POS);}
    
  /** setter for POS - sets  
   * @generated */
  public void setPOS(String v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_POS == null)
      jcasType.jcas.throwFeatMissing("POS", "sprint.uima.types.Token");
    jcasType.ll_cas.ll_setStringValue(addr, ((Token_Type)jcasType).casFeatCode_POS, v);}    
   
    
  //*--------------*
  //* Feature: IsEntity

  /** getter for IsEntity - gets 
   * @generated */
  public boolean getIsEntity() {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_IsEntity == null)
      jcasType.jcas.throwFeatMissing("IsEntity", "sprint.uima.types.Token");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((Token_Type)jcasType).casFeatCode_IsEntity);}
    
  /** setter for IsEntity - sets  
   * @generated */
  public void setIsEntity(boolean v) {
    if (Token_Type.featOkTst && ((Token_Type)jcasType).casFeat_IsEntity == null)
      jcasType.jcas.throwFeatMissing("IsEntity", "sprint.uima.types.Token");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((Token_Type)jcasType).casFeatCode_IsEntity, v);}    
  }

    