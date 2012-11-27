

/* First created by JCasGen Tue Oct 02 12:01:59 CEST 2012 */
package edu.ucdenver.ccp.nlp.core.uima.mention;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.StringArray;


/** 
 * Updated by JCasGen Tue Oct 02 12:01:59 CEST 2012
 * XML source: D:/ChemSpot/git/ChemSpot/desc/cr/CraftCR.xml
 * @generated */
public class CCPStringSlotMention extends CCPPrimitiveSlotMention {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(CCPStringSlotMention.class);
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
  protected CCPStringSlotMention() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public CCPStringSlotMention(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public CCPStringSlotMention(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: slotValues

  /** getter for slotValues - gets 
   * @generated */
  public StringArray getSlotValues() {
    if (CCPStringSlotMention_Type.featOkTst && ((CCPStringSlotMention_Type)jcasType).casFeat_slotValues == null)
      jcasType.jcas.throwFeatMissing("slotValues", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPStringSlotMention");
    return (StringArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((CCPStringSlotMention_Type)jcasType).casFeatCode_slotValues)));}
    
  /** setter for slotValues - sets  
   * @generated */
  public void setSlotValues(StringArray v) {
    if (CCPStringSlotMention_Type.featOkTst && ((CCPStringSlotMention_Type)jcasType).casFeat_slotValues == null)
      jcasType.jcas.throwFeatMissing("slotValues", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPStringSlotMention");
    jcasType.ll_cas.ll_setRefValue(addr, ((CCPStringSlotMention_Type)jcasType).casFeatCode_slotValues, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for slotValues - gets an indexed value - 
   * @generated */
  public String getSlotValues(int i) {
    if (CCPStringSlotMention_Type.featOkTst && ((CCPStringSlotMention_Type)jcasType).casFeat_slotValues == null)
      jcasType.jcas.throwFeatMissing("slotValues", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPStringSlotMention");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((CCPStringSlotMention_Type)jcasType).casFeatCode_slotValues), i);
    return jcasType.ll_cas.ll_getStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((CCPStringSlotMention_Type)jcasType).casFeatCode_slotValues), i);}

  /** indexed setter for slotValues - sets an indexed value - 
   * @generated */
  public void setSlotValues(int i, String v) { 
    if (CCPStringSlotMention_Type.featOkTst && ((CCPStringSlotMention_Type)jcasType).casFeat_slotValues == null)
      jcasType.jcas.throwFeatMissing("slotValues", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPStringSlotMention");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((CCPStringSlotMention_Type)jcasType).casFeatCode_slotValues), i);
    jcasType.ll_cas.ll_setStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((CCPStringSlotMention_Type)jcasType).casFeatCode_slotValues), i, v);}
  }

    