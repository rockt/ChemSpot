

/* First created by JCasGen Tue Oct 02 12:01:59 CEST 2012 */
package edu.ucdenver.ccp.nlp.core.uima.mention;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation;


/** The CCP ClassMention is the root of a flexible class structure that can be used to store virtually any frame-based representation of a particular class. Common class mention types include, but are not limited to, such things as entities (protein, cell type, cell line, disease, tissue, etc.) and frames (interaction, transport, regulation, etc.).
 * Updated by JCasGen Tue Oct 02 12:01:59 CEST 2012
 * XML source: D:/ChemSpot/git/ChemSpot/desc/cr/CraftCR.xml
 * @generated */
public class CCPClassMention extends CCPMention {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(CCPClassMention.class);
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
  protected CCPClassMention() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public CCPClassMention(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public CCPClassMention(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: slotMentions

  /** getter for slotMentions - gets A class mention optionally has slot mentions which represent attributes of that class. These slot mentions are stored in the slotMentions FSArray. There are two types of slot mentions, complex and non-complex.  The difference between complex and non-complex slot mentions is simply the type of filler (or slot value) for each. Complex slot mentions are filled with a class mention, whereas non-complex slot mentions are filled by simple Strings.
   * @generated */
  public FSArray getSlotMentions() {
    if (CCPClassMention_Type.featOkTst && ((CCPClassMention_Type)jcasType).casFeat_slotMentions == null)
      jcasType.jcas.throwFeatMissing("slotMentions", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPClassMention");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((CCPClassMention_Type)jcasType).casFeatCode_slotMentions)));}
    
  /** setter for slotMentions - sets A class mention optionally has slot mentions which represent attributes of that class. These slot mentions are stored in the slotMentions FSArray. There are two types of slot mentions, complex and non-complex.  The difference between complex and non-complex slot mentions is simply the type of filler (or slot value) for each. Complex slot mentions are filled with a class mention, whereas non-complex slot mentions are filled by simple Strings. 
   * @generated */
  public void setSlotMentions(FSArray v) {
    if (CCPClassMention_Type.featOkTst && ((CCPClassMention_Type)jcasType).casFeat_slotMentions == null)
      jcasType.jcas.throwFeatMissing("slotMentions", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPClassMention");
    jcasType.ll_cas.ll_setRefValue(addr, ((CCPClassMention_Type)jcasType).casFeatCode_slotMentions, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for slotMentions - gets an indexed value - A class mention optionally has slot mentions which represent attributes of that class. These slot mentions are stored in the slotMentions FSArray. There are two types of slot mentions, complex and non-complex.  The difference between complex and non-complex slot mentions is simply the type of filler (or slot value) for each. Complex slot mentions are filled with a class mention, whereas non-complex slot mentions are filled by simple Strings.
   * @generated */
  public CCPSlotMention getSlotMentions(int i) {
    if (CCPClassMention_Type.featOkTst && ((CCPClassMention_Type)jcasType).casFeat_slotMentions == null)
      jcasType.jcas.throwFeatMissing("slotMentions", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPClassMention");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((CCPClassMention_Type)jcasType).casFeatCode_slotMentions), i);
    return (CCPSlotMention)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((CCPClassMention_Type)jcasType).casFeatCode_slotMentions), i)));}

  /** indexed setter for slotMentions - sets an indexed value - A class mention optionally has slot mentions which represent attributes of that class. These slot mentions are stored in the slotMentions FSArray. There are two types of slot mentions, complex and non-complex.  The difference between complex and non-complex slot mentions is simply the type of filler (or slot value) for each. Complex slot mentions are filled with a class mention, whereas non-complex slot mentions are filled by simple Strings.
   * @generated */
  public void setSlotMentions(int i, CCPSlotMention v) { 
    if (CCPClassMention_Type.featOkTst && ((CCPClassMention_Type)jcasType).casFeat_slotMentions == null)
      jcasType.jcas.throwFeatMissing("slotMentions", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPClassMention");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((CCPClassMention_Type)jcasType).casFeatCode_slotMentions), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((CCPClassMention_Type)jcasType).casFeatCode_slotMentions), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: ccpTextAnnotation

  /** getter for ccpTextAnnotation - gets Just as CCPTextAnnotations are linked to a CCPClassMention, it is sometimes useful to be able to follow a CCPClassMention back to its corresponding CCPTextAnnotation.
   * @generated */
  public CCPTextAnnotation getCcpTextAnnotation() {
    if (CCPClassMention_Type.featOkTst && ((CCPClassMention_Type)jcasType).casFeat_ccpTextAnnotation == null)
      jcasType.jcas.throwFeatMissing("ccpTextAnnotation", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPClassMention");
    return (CCPTextAnnotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((CCPClassMention_Type)jcasType).casFeatCode_ccpTextAnnotation)));}
    
  /** setter for ccpTextAnnotation - sets Just as CCPTextAnnotations are linked to a CCPClassMention, it is sometimes useful to be able to follow a CCPClassMention back to its corresponding CCPTextAnnotation. 
   * @generated */
  public void setCcpTextAnnotation(CCPTextAnnotation v) {
    if (CCPClassMention_Type.featOkTst && ((CCPClassMention_Type)jcasType).casFeat_ccpTextAnnotation == null)
      jcasType.jcas.throwFeatMissing("ccpTextAnnotation", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPClassMention");
    jcasType.ll_cas.ll_setRefValue(addr, ((CCPClassMention_Type)jcasType).casFeatCode_ccpTextAnnotation, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    