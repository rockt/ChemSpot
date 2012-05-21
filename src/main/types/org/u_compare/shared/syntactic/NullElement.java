

/* First created by JCasGen Wed Mar 16 10:14:08 CET 2011 */
package org.u_compare.shared.syntactic;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.u_compare.shared.label.penn.coindex.NullElementLabel;


/** 
 * Updated by JCasGen Tue Mar 06 16:28:20 CET 2012
 * XML source: /vol/home-vol3/wbi/wbi_stud/VLKB/Tim/vlkb/chemspotVSoscar3/desc/cr/iobCR.xml
 * @generated */
public class NullElement extends AbstractConstituent {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(NullElement.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected NullElement() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public NullElement(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public NullElement(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public NullElement(JCas jcas, int begin, int end) {
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
  //* Feature: label

  /** getter for label - gets 
   * @generated */
  public NullElementLabel getLabel() {
    if (NullElement_Type.featOkTst && ((NullElement_Type)jcasType).casFeat_label == null)
      jcasType.jcas.throwFeatMissing("label", "org.u_compare.shared.syntactic.NullElement");
    return (NullElementLabel)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((NullElement_Type)jcasType).casFeatCode_label)));}
    
  /** setter for label - sets  
   * @generated */
  public void setLabel(NullElementLabel v) {
    if (NullElement_Type.featOkTst && ((NullElement_Type)jcasType).casFeat_label == null)
      jcasType.jcas.throwFeatMissing("label", "org.u_compare.shared.syntactic.NullElement");
    jcasType.ll_cas.ll_setRefValue(addr, ((NullElement_Type)jcasType).casFeatCode_label, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: coindexed

  /** getter for coindexed - gets 
   * @generated */
  public Constituent getCoindexed() {
    if (NullElement_Type.featOkTst && ((NullElement_Type)jcasType).casFeat_coindexed == null)
      jcasType.jcas.throwFeatMissing("coindexed", "org.u_compare.shared.syntactic.NullElement");
    return (Constituent)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((NullElement_Type)jcasType).casFeatCode_coindexed)));}
    
  /** setter for coindexed - sets  
   * @generated */
  public void setCoindexed(Constituent v) {
    if (NullElement_Type.featOkTst && ((NullElement_Type)jcasType).casFeat_coindexed == null)
      jcasType.jcas.throwFeatMissing("coindexed", "org.u_compare.shared.syntactic.NullElement");
    jcasType.ll_cas.ll_setRefValue(addr, ((NullElement_Type)jcasType).casFeatCode_coindexed, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    