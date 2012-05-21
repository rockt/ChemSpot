

/* First created by JCasGen Wed Mar 16 10:14:04 CET 2011 */
package org.u_compare.shared;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;


/** Base abstract type for annotations with discontinuous regions. <br></br>
 * Updated by JCasGen Tue Mar 06 16:28:13 CET 2012
 * XML source: /vol/home-vol3/wbi/wbi_stud/VLKB/Tim/vlkb/chemspotVSoscar3/desc/cr/iobCR.xml
 * @generated */
public class DiscontinuousAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(DiscontinuousAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected DiscontinuousAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public DiscontinuousAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public DiscontinuousAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public DiscontinuousAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: fragments

  /** getter for fragments - gets fragments of discontinous region
   * @generated */
  public FSArray getFragments() {
    if (DiscontinuousAnnotation_Type.featOkTst && ((DiscontinuousAnnotation_Type)jcasType).casFeat_fragments == null)
      jcasType.jcas.throwFeatMissing("fragments", "org.u_compare.shared.DiscontinuousAnnotation");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DiscontinuousAnnotation_Type)jcasType).casFeatCode_fragments)));}
    
  /** setter for fragments - sets fragments of discontinous region 
   * @generated */
  public void setFragments(FSArray v) {
    if (DiscontinuousAnnotation_Type.featOkTst && ((DiscontinuousAnnotation_Type)jcasType).casFeat_fragments == null)
      jcasType.jcas.throwFeatMissing("fragments", "org.u_compare.shared.DiscontinuousAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((DiscontinuousAnnotation_Type)jcasType).casFeatCode_fragments, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for fragments - gets an indexed value - fragments of discontinous region
   * @generated */
  public Annotation getFragments(int i) {
    if (DiscontinuousAnnotation_Type.featOkTst && ((DiscontinuousAnnotation_Type)jcasType).casFeat_fragments == null)
      jcasType.jcas.throwFeatMissing("fragments", "org.u_compare.shared.DiscontinuousAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DiscontinuousAnnotation_Type)jcasType).casFeatCode_fragments), i);
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DiscontinuousAnnotation_Type)jcasType).casFeatCode_fragments), i)));}

  /** indexed setter for fragments - sets an indexed value - fragments of discontinous region
   * @generated */
  public void setFragments(int i, Annotation v) { 
    if (DiscontinuousAnnotation_Type.featOkTst && ((DiscontinuousAnnotation_Type)jcasType).casFeat_fragments == null)
      jcasType.jcas.throwFeatMissing("fragments", "org.u_compare.shared.DiscontinuousAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((DiscontinuousAnnotation_Type)jcasType).casFeatCode_fragments), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((DiscontinuousAnnotation_Type)jcasType).casFeatCode_fragments), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    