

/* First created by JCasGen Tue Oct 02 12:01:59 CEST 2012 */
package edu.ucdenver.ccp.nlp.core.uima.mention;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Tue Oct 02 12:01:59 CEST 2012
 * XML source: D:/ChemSpot/git/ChemSpot/desc/cr/CraftCR.xml
 * @generated */
public class CCPBooleanSlotMention extends CCPPrimitiveSlotMention {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(CCPBooleanSlotMention.class);
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
  protected CCPBooleanSlotMention() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public CCPBooleanSlotMention(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public CCPBooleanSlotMention(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: slotValue

  /** getter for slotValue - gets 
   * @generated */
  public boolean getSlotValue() {
    if (CCPBooleanSlotMention_Type.featOkTst && ((CCPBooleanSlotMention_Type)jcasType).casFeat_slotValue == null)
      jcasType.jcas.throwFeatMissing("slotValue", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPBooleanSlotMention");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((CCPBooleanSlotMention_Type)jcasType).casFeatCode_slotValue);}
    
  /** setter for slotValue - sets  
   * @generated */
  public void setSlotValue(boolean v) {
    if (CCPBooleanSlotMention_Type.featOkTst && ((CCPBooleanSlotMention_Type)jcasType).casFeat_slotValue == null)
      jcasType.jcas.throwFeatMissing("slotValue", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPBooleanSlotMention");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((CCPBooleanSlotMention_Type)jcasType).casFeatCode_slotValue, v);}    
  }

    