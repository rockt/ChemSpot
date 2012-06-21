

/* First created by JCasGen Thu Jul 14 13:32:47 CEST 2011 */
package sprint.uima.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Thu Jul 14 14:25:40 CEST 2011
 * XML source: /vol/home-vol3/wbi/wbi_stud/VLKB/Tim/vlkb/chemspotVSoscar3/desc/util/DDIToUCompareConverterAE.xml
 * @generated */
public class Pair extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(Pair.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Pair() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Pair(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Pair(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Pair(JCas jcas, int begin, int end) {
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
    if (Pair_Type.featOkTst && ((Pair_Type)jcasType).casFeat_ID == null)
      jcasType.jcas.throwFeatMissing("ID", "sprint.uima.types.Pair");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Pair_Type)jcasType).casFeatCode_ID);}
    
  /** setter for ID - sets  
   * @generated */
  public void setID(String v) {
    if (Pair_Type.featOkTst && ((Pair_Type)jcasType).casFeat_ID == null)
      jcasType.jcas.throwFeatMissing("ID", "sprint.uima.types.Pair");
    jcasType.ll_cas.ll_setStringValue(addr, ((Pair_Type)jcasType).casFeatCode_ID, v);}    
   
    
  //*--------------*
  //* Feature: Interaction

  /** getter for Interaction - gets 
   * @generated */
  public boolean getInteraction() {
    if (Pair_Type.featOkTst && ((Pair_Type)jcasType).casFeat_Interaction == null)
      jcasType.jcas.throwFeatMissing("Interaction", "sprint.uima.types.Pair");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((Pair_Type)jcasType).casFeatCode_Interaction);}
    
  /** setter for Interaction - sets  
   * @generated */
  public void setInteraction(boolean v) {
    if (Pair_Type.featOkTst && ((Pair_Type)jcasType).casFeat_Interaction == null)
      jcasType.jcas.throwFeatMissing("Interaction", "sprint.uima.types.Pair");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((Pair_Type)jcasType).casFeatCode_Interaction, v);}    
   
    
  //*--------------*
  //* Feature: Entity1

  /** getter for Entity1 - gets 
   * @generated */
  public Entity getEntity1() {
    if (Pair_Type.featOkTst && ((Pair_Type)jcasType).casFeat_Entity1 == null)
      jcasType.jcas.throwFeatMissing("Entity1", "sprint.uima.types.Pair");
    return (Entity)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Pair_Type)jcasType).casFeatCode_Entity1)));}
    
  /** setter for Entity1 - sets  
   * @generated */
  public void setEntity1(Entity v) {
    if (Pair_Type.featOkTst && ((Pair_Type)jcasType).casFeat_Entity1 == null)
      jcasType.jcas.throwFeatMissing("Entity1", "sprint.uima.types.Pair");
    jcasType.ll_cas.ll_setRefValue(addr, ((Pair_Type)jcasType).casFeatCode_Entity1, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: Entity2

  /** getter for Entity2 - gets 
   * @generated */
  public Entity getEntity2() {
    if (Pair_Type.featOkTst && ((Pair_Type)jcasType).casFeat_Entity2 == null)
      jcasType.jcas.throwFeatMissing("Entity2", "sprint.uima.types.Pair");
    return (Entity)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Pair_Type)jcasType).casFeatCode_Entity2)));}
    
  /** setter for Entity2 - sets  
   * @generated */
  public void setEntity2(Entity v) {
    if (Pair_Type.featOkTst && ((Pair_Type)jcasType).casFeat_Entity2 == null)
      jcasType.jcas.throwFeatMissing("Entity2", "sprint.uima.types.Pair");
    jcasType.ll_cas.ll_setRefValue(addr, ((Pair_Type)jcasType).casFeatCode_Entity2, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    