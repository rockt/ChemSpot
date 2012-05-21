

/* First created by JCasGen Wed Mar 16 10:14:05 CET 2011 */
package org.u_compare.shared.label.penn.bracket;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Tue Mar 06 16:28:14 CET 2012
 * XML source: /vol/home-vol3/wbi/wbi_stud/VLKB/Tim/vlkb/chemspotVSoscar3/desc/cr/iobCR.xml
 * @generated */
public class UnknownNonterminalLabel extends NonterminalLabel {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(UnknownNonterminalLabel.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected UnknownNonterminalLabel() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public UnknownNonterminalLabel(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public UnknownNonterminalLabel(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
 
    
  //*--------------*
  //* Feature: name

  /** getter for name - gets 
   * @generated */
  public String getName() {
    if (UnknownNonterminalLabel_Type.featOkTst && ((UnknownNonterminalLabel_Type)jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "org.u_compare.shared.label.penn.bracket.UnknownNonterminalLabel");
    return jcasType.ll_cas.ll_getStringValue(addr, ((UnknownNonterminalLabel_Type)jcasType).casFeatCode_name);}
    
  /** setter for name - sets  
   * @generated */
  public void setName(String v) {
    if (UnknownNonterminalLabel_Type.featOkTst && ((UnknownNonterminalLabel_Type)jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "org.u_compare.shared.label.penn.bracket.UnknownNonterminalLabel");
    jcasType.ll_cas.ll_setStringValue(addr, ((UnknownNonterminalLabel_Type)jcasType).casFeatCode_name, v);}    
  }

    