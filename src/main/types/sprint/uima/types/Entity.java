

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
public class Entity extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(Entity.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Entity() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Entity(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Entity(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Entity(JCas jcas, int begin, int end) {
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
    if (Entity_Type.featOkTst && ((Entity_Type)jcasType).casFeat_ID == null)
      jcasType.jcas.throwFeatMissing("ID", "sprint.uima.types.Entity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Entity_Type)jcasType).casFeatCode_ID);}
    
  /** setter for ID - sets  
   * @generated */
  public void setID(String v) {
    if (Entity_Type.featOkTst && ((Entity_Type)jcasType).casFeat_ID == null)
      jcasType.jcas.throwFeatMissing("ID", "sprint.uima.types.Entity");
    jcasType.ll_cas.ll_setStringValue(addr, ((Entity_Type)jcasType).casFeatCode_ID, v);}    
   
    
  //*--------------*
  //* Feature: EntityType

  /** getter for EntityType - gets 
   * @generated */
  public String getEntityType() {
    if (Entity_Type.featOkTst && ((Entity_Type)jcasType).casFeat_EntityType == null)
      jcasType.jcas.throwFeatMissing("EntityType", "sprint.uima.types.Entity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Entity_Type)jcasType).casFeatCode_EntityType);}
    
  /** setter for EntityType - sets  
   * @generated */
  public void setEntityType(String v) {
    if (Entity_Type.featOkTst && ((Entity_Type)jcasType).casFeat_EntityType == null)
      jcasType.jcas.throwFeatMissing("EntityType", "sprint.uima.types.Entity");
    jcasType.ll_cas.ll_setStringValue(addr, ((Entity_Type)jcasType).casFeatCode_EntityType, v);}    
   
    
  //*--------------*
  //* Feature: CharOffset

  /** getter for CharOffset - gets 
   * @generated */
  public String getCharOffset() {
    if (Entity_Type.featOkTst && ((Entity_Type)jcasType).casFeat_CharOffset == null)
      jcasType.jcas.throwFeatMissing("CharOffset", "sprint.uima.types.Entity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Entity_Type)jcasType).casFeatCode_CharOffset);}
    
  /** setter for CharOffset - sets  
   * @generated */
  public void setCharOffset(String v) {
    if (Entity_Type.featOkTst && ((Entity_Type)jcasType).casFeat_CharOffset == null)
      jcasType.jcas.throwFeatMissing("CharOffset", "sprint.uima.types.Entity");
    jcasType.ll_cas.ll_setStringValue(addr, ((Entity_Type)jcasType).casFeatCode_CharOffset, v);}    
  }

    