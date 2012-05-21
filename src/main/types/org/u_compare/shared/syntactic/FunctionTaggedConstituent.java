

/* First created by JCasGen Wed Mar 16 10:14:08 CET 2011 */
package org.u_compare.shared.syntactic;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.u_compare.shared.label.penn.function.FunctionLabel;


/** 
 * Updated by JCasGen Tue Mar 06 16:28:20 CET 2012
 * XML source: /vol/home-vol3/wbi/wbi_stud/VLKB/Tim/vlkb/chemspotVSoscar3/desc/cr/iobCR.xml
 * @generated */
public class FunctionTaggedConstituent extends Constituent {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(FunctionTaggedConstituent.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected FunctionTaggedConstituent() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public FunctionTaggedConstituent(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public FunctionTaggedConstituent(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public FunctionTaggedConstituent(JCas jcas, int begin, int end) {
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
  //* Feature: function

  /** getter for function - gets 
   * @generated */
  public FunctionLabel getFunction() {
    if (FunctionTaggedConstituent_Type.featOkTst && ((FunctionTaggedConstituent_Type)jcasType).casFeat_function == null)
      jcasType.jcas.throwFeatMissing("function", "org.u_compare.shared.syntactic.FunctionTaggedConstituent");
    return (FunctionLabel)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((FunctionTaggedConstituent_Type)jcasType).casFeatCode_function)));}
    
  /** setter for function - sets  
   * @generated */
  public void setFunction(FunctionLabel v) {
    if (FunctionTaggedConstituent_Type.featOkTst && ((FunctionTaggedConstituent_Type)jcasType).casFeat_function == null)
      jcasType.jcas.throwFeatMissing("function", "org.u_compare.shared.syntactic.FunctionTaggedConstituent");
    jcasType.ll_cas.ll_setRefValue(addr, ((FunctionTaggedConstituent_Type)jcasType).casFeatCode_function, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    