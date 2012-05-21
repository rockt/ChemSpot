

/* First created by JCasGen Wed Mar 16 10:14:08 CET 2011 */
package org.u_compare.shared.syntactic;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.u_compare.shared.label.ConstituentLabel;


/** 
 * Updated by JCasGen Tue Mar 06 16:28:20 CET 2012
 * XML source: /vol/home-vol3/wbi/wbi_stud/VLKB/Tim/vlkb/chemspotVSoscar3/desc/cr/iobCR.xml
 * @generated */
public class Constituent extends AbstractConstituent {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(Constituent.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Constituent() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Constituent(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Constituent(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Constituent(JCas jcas, int begin, int end) {
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
  public ConstituentLabel getLabel() {
    if (Constituent_Type.featOkTst && ((Constituent_Type)jcasType).casFeat_label == null)
      jcasType.jcas.throwFeatMissing("label", "org.u_compare.shared.syntactic.Constituent");
    return (ConstituentLabel)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Constituent_Type)jcasType).casFeatCode_label)));}
    
  /** setter for label - sets  
   * @generated */
  public void setLabel(ConstituentLabel v) {
    if (Constituent_Type.featOkTst && ((Constituent_Type)jcasType).casFeat_label == null)
      jcasType.jcas.throwFeatMissing("label", "org.u_compare.shared.syntactic.Constituent");
    jcasType.ll_cas.ll_setRefValue(addr, ((Constituent_Type)jcasType).casFeatCode_label, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    