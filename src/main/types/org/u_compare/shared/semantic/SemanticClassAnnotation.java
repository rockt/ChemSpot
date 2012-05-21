

/* First created by JCasGen Wed Mar 16 10:14:08 CET 2011 */
package org.u_compare.shared.semantic;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.TOP_Type;


/** 
 * Updated by JCasGen Tue Mar 06 16:28:20 CET 2012
 * XML source: /vol/home-vol3/wbi/wbi_stud/VLKB/Tim/vlkb/chemspotVSoscar3/desc/cr/iobCR.xml
 * @generated */
public class SemanticClassAnnotation extends SemanticAnnotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SemanticClassAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SemanticClassAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SemanticClassAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SemanticClassAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SemanticClassAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: linkedAnnotationSets

  /** getter for linkedAnnotationSets - gets 
   * @generated */
  public FSArray getLinkedAnnotationSets() {
    if (SemanticClassAnnotation_Type.featOkTst && ((SemanticClassAnnotation_Type)jcasType).casFeat_linkedAnnotationSets == null)
      jcasType.jcas.throwFeatMissing("linkedAnnotationSets", "org.u_compare.shared.semantic.SemanticClassAnnotation");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SemanticClassAnnotation_Type)jcasType).casFeatCode_linkedAnnotationSets)));}
    
  /** setter for linkedAnnotationSets - sets  
   * @generated */
  public void setLinkedAnnotationSets(FSArray v) {
    if (SemanticClassAnnotation_Type.featOkTst && ((SemanticClassAnnotation_Type)jcasType).casFeat_linkedAnnotationSets == null)
      jcasType.jcas.throwFeatMissing("linkedAnnotationSets", "org.u_compare.shared.semantic.SemanticClassAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((SemanticClassAnnotation_Type)jcasType).casFeatCode_linkedAnnotationSets, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for linkedAnnotationSets - gets an indexed value - 
   * @generated */
  public LinkedAnnotationSet getLinkedAnnotationSets(int i) {
    if (SemanticClassAnnotation_Type.featOkTst && ((SemanticClassAnnotation_Type)jcasType).casFeat_linkedAnnotationSets == null)
      jcasType.jcas.throwFeatMissing("linkedAnnotationSets", "org.u_compare.shared.semantic.SemanticClassAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SemanticClassAnnotation_Type)jcasType).casFeatCode_linkedAnnotationSets), i);
    return (LinkedAnnotationSet)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SemanticClassAnnotation_Type)jcasType).casFeatCode_linkedAnnotationSets), i)));}

  /** indexed setter for linkedAnnotationSets - sets an indexed value - 
   * @generated */
  public void setLinkedAnnotationSets(int i, LinkedAnnotationSet v) { 
    if (SemanticClassAnnotation_Type.featOkTst && ((SemanticClassAnnotation_Type)jcasType).casFeat_linkedAnnotationSets == null)
      jcasType.jcas.throwFeatMissing("linkedAnnotationSets", "org.u_compare.shared.semantic.SemanticClassAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SemanticClassAnnotation_Type)jcasType).casFeatCode_linkedAnnotationSets), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SemanticClassAnnotation_Type)jcasType).casFeatCode_linkedAnnotationSets), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    