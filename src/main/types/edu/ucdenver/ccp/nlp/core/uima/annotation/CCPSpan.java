

/* First created by JCasGen Tue Oct 02 12:01:59 CEST 2012 */
package edu.ucdenver.ccp.nlp.core.uima.annotation;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.TOP;


/** The span object holds span information.This is a supplement to the default UIMA annotation which cannot handle multi-span annotations.
 * Updated by JCasGen Tue Oct 02 12:01:59 CEST 2012
 * XML source: D:/ChemSpot/git/ChemSpot/desc/cr/CraftCR.xml
 * @generated */
public class CCPSpan extends TOP {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(CCPSpan.class);
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
  protected CCPSpan() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public CCPSpan(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public CCPSpan(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: spanStart

  /** getter for spanStart - gets The character offset for the start of the span.
   * @generated */
  public int getSpanStart() {
    if (CCPSpan_Type.featOkTst && ((CCPSpan_Type)jcasType).casFeat_spanStart == null)
      jcasType.jcas.throwFeatMissing("spanStart", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPSpan");
    return jcasType.ll_cas.ll_getIntValue(addr, ((CCPSpan_Type)jcasType).casFeatCode_spanStart);}
    
  /** setter for spanStart - sets The character offset for the start of the span. 
   * @generated */
  public void setSpanStart(int v) {
    if (CCPSpan_Type.featOkTst && ((CCPSpan_Type)jcasType).casFeat_spanStart == null)
      jcasType.jcas.throwFeatMissing("spanStart", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPSpan");
    jcasType.ll_cas.ll_setIntValue(addr, ((CCPSpan_Type)jcasType).casFeatCode_spanStart, v);}    
   
    
  //*--------------*
  //* Feature: spanEnd

  /** getter for spanEnd - gets The character offset for the end of the span.
   * @generated */
  public int getSpanEnd() {
    if (CCPSpan_Type.featOkTst && ((CCPSpan_Type)jcasType).casFeat_spanEnd == null)
      jcasType.jcas.throwFeatMissing("spanEnd", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPSpan");
    return jcasType.ll_cas.ll_getIntValue(addr, ((CCPSpan_Type)jcasType).casFeatCode_spanEnd);}
    
  /** setter for spanEnd - sets The character offset for the end of the span. 
   * @generated */
  public void setSpanEnd(int v) {
    if (CCPSpan_Type.featOkTst && ((CCPSpan_Type)jcasType).casFeat_spanEnd == null)
      jcasType.jcas.throwFeatMissing("spanEnd", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPSpan");
    jcasType.ll_cas.ll_setIntValue(addr, ((CCPSpan_Type)jcasType).casFeatCode_spanEnd, v);}    
  }

    