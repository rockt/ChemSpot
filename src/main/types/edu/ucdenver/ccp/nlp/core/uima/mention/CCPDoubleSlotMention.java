

/* First created by JCasGen Tue Oct 02 12:01:59 CEST 2012 */
package edu.ucdenver.ccp.nlp.core.uima.mention;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.DoubleArray;


/** 
 * Updated by JCasGen Tue Oct 02 12:01:59 CEST 2012
 * XML source: D:/ChemSpot/git/ChemSpot/desc/cr/CraftCR.xml
 * @generated */
public class CCPDoubleSlotMention extends CCPPrimitiveSlotMention {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(CCPDoubleSlotMention.class);
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
  protected CCPDoubleSlotMention() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public CCPDoubleSlotMention(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public CCPDoubleSlotMention(JCas jcas) {
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
  public DoubleArray getSlotValues() {
    if (CCPDoubleSlotMention_Type.featOkTst && ((CCPDoubleSlotMention_Type)jcasType).casFeat_slotValues == null)
      jcasType.jcas.throwFeatMissing("slotValues", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPDoubleSlotMention");
    return (DoubleArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((CCPDoubleSlotMention_Type)jcasType).casFeatCode_slotValues)));}
    
  /** setter for slotValues - sets  
   * @generated */
  public void setSlotValues(DoubleArray v) {
    if (CCPDoubleSlotMention_Type.featOkTst && ((CCPDoubleSlotMention_Type)jcasType).casFeat_slotValues == null)
      jcasType.jcas.throwFeatMissing("slotValues", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPDoubleSlotMention");
    jcasType.ll_cas.ll_setRefValue(addr, ((CCPDoubleSlotMention_Type)jcasType).casFeatCode_slotValues, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for slotValues - gets an indexed value - 
   * @generated */
  public double getSlotValues(int i) {
    if (CCPDoubleSlotMention_Type.featOkTst && ((CCPDoubleSlotMention_Type)jcasType).casFeat_slotValues == null)
      jcasType.jcas.throwFeatMissing("slotValues", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPDoubleSlotMention");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((CCPDoubleSlotMention_Type)jcasType).casFeatCode_slotValues), i);
    return jcasType.ll_cas.ll_getDoubleArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((CCPDoubleSlotMention_Type)jcasType).casFeatCode_slotValues), i);}

  /** indexed setter for slotValues - sets an indexed value - 
   * @generated */
  public void setSlotValues(int i, double v) { 
    if (CCPDoubleSlotMention_Type.featOkTst && ((CCPDoubleSlotMention_Type)jcasType).casFeat_slotValues == null)
      jcasType.jcas.throwFeatMissing("slotValues", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPDoubleSlotMention");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((CCPDoubleSlotMention_Type)jcasType).casFeatCode_slotValues), i);
    jcasType.ll_cas.ll_setDoubleArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((CCPDoubleSlotMention_Type)jcasType).casFeatCode_slotValues), i, v);}
  }

    