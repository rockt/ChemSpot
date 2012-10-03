

/* First created by JCasGen Tue Oct 02 12:01:59 CEST 2012 */
package edu.ucdenver.ccp.nlp.core.uima.mention;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;


/** A slot mention is deemed "complex" when its slot filler is a class mention as opposed to a String (See non-complex slot mention for String fillers). An example of a complex slot mention is the "transported entity" slot for the protein-transport class which would be filled with a protein class mention.
 * Updated by JCasGen Tue Oct 02 12:01:59 CEST 2012
 * XML source: D:/ChemSpot/git/ChemSpot/desc/cr/CraftCR.xml
 * @generated */
public class CCPComplexSlotMention extends CCPSlotMention {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(CCPComplexSlotMention.class);
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
  protected CCPComplexSlotMention() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public CCPComplexSlotMention(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public CCPComplexSlotMention(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: classMentions

  /** getter for classMentions - gets The class mentions which are the slot fillers for this complex slot.
   * @generated */
  public FSArray getClassMentions() {
    if (CCPComplexSlotMention_Type.featOkTst && ((CCPComplexSlotMention_Type)jcasType).casFeat_classMentions == null)
      jcasType.jcas.throwFeatMissing("classMentions", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPComplexSlotMention");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((CCPComplexSlotMention_Type)jcasType).casFeatCode_classMentions)));}
    
  /** setter for classMentions - sets The class mentions which are the slot fillers for this complex slot. 
   * @generated */
  public void setClassMentions(FSArray v) {
    if (CCPComplexSlotMention_Type.featOkTst && ((CCPComplexSlotMention_Type)jcasType).casFeat_classMentions == null)
      jcasType.jcas.throwFeatMissing("classMentions", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPComplexSlotMention");
    jcasType.ll_cas.ll_setRefValue(addr, ((CCPComplexSlotMention_Type)jcasType).casFeatCode_classMentions, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for classMentions - gets an indexed value - The class mentions which are the slot fillers for this complex slot.
   * @generated */
  public CCPClassMention getClassMentions(int i) {
    if (CCPComplexSlotMention_Type.featOkTst && ((CCPComplexSlotMention_Type)jcasType).casFeat_classMentions == null)
      jcasType.jcas.throwFeatMissing("classMentions", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPComplexSlotMention");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((CCPComplexSlotMention_Type)jcasType).casFeatCode_classMentions), i);
    return (CCPClassMention)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((CCPComplexSlotMention_Type)jcasType).casFeatCode_classMentions), i)));}

  /** indexed setter for classMentions - sets an indexed value - The class mentions which are the slot fillers for this complex slot.
   * @generated */
  public void setClassMentions(int i, CCPClassMention v) { 
    if (CCPComplexSlotMention_Type.featOkTst && ((CCPComplexSlotMention_Type)jcasType).casFeat_classMentions == null)
      jcasType.jcas.throwFeatMissing("classMentions", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPComplexSlotMention");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((CCPComplexSlotMention_Type)jcasType).casFeatCode_classMentions), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((CCPComplexSlotMention_Type)jcasType).casFeatCode_classMentions), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    