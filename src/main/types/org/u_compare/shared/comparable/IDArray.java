

/* First created by JCasGen Wed Mar 16 10:14:04 CET 2011 */
package org.u_compare.shared.comparable;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.TOP;


/** 
 * Updated by JCasGen Tue Mar 06 16:28:13 CET 2012
 * XML source: /vol/home-vol3/wbi/wbi_stud/VLKB/Tim/vlkb/chemspotVSoscar3/desc/cr/iobCR.xml
 * @generated */
public class IDArray extends TOP {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(IDArray.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected IDArray() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public IDArray(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public IDArray(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
 
    
  //*--------------*
  //* Feature: array

  /** getter for array - gets 
   * @generated */
  public FSArray getArray() {
    if (IDArray_Type.featOkTst && ((IDArray_Type)jcasType).casFeat_array == null)
      jcasType.jcas.throwFeatMissing("array", "org.u_compare.shared.comparable.IDArray");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((IDArray_Type)jcasType).casFeatCode_array)));}
    
  /** setter for array - sets  
   * @generated */
  public void setArray(FSArray v) {
    if (IDArray_Type.featOkTst && ((IDArray_Type)jcasType).casFeat_array == null)
      jcasType.jcas.throwFeatMissing("array", "org.u_compare.shared.comparable.IDArray");
    jcasType.ll_cas.ll_setRefValue(addr, ((IDArray_Type)jcasType).casFeatCode_array, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for array - gets an indexed value - 
   * @generated */
  public TOP getArray(int i) {
    if (IDArray_Type.featOkTst && ((IDArray_Type)jcasType).casFeat_array == null)
      jcasType.jcas.throwFeatMissing("array", "org.u_compare.shared.comparable.IDArray");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((IDArray_Type)jcasType).casFeatCode_array), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((IDArray_Type)jcasType).casFeatCode_array), i)));}

  /** indexed setter for array - sets an indexed value - 
   * @generated */
  public void setArray(int i, TOP v) { 
    if (IDArray_Type.featOkTst && ((IDArray_Type)jcasType).casFeat_array == null)
      jcasType.jcas.throwFeatMissing("array", "org.u_compare.shared.comparable.IDArray");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((IDArray_Type)jcasType).casFeatCode_array), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((IDArray_Type)jcasType).casFeatCode_array), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: idType

  /** getter for idType - gets 
   * @generated */
  public String getIdType() {
    if (IDArray_Type.featOkTst && ((IDArray_Type)jcasType).casFeat_idType == null)
      jcasType.jcas.throwFeatMissing("idType", "org.u_compare.shared.comparable.IDArray");
    return jcasType.ll_cas.ll_getStringValue(addr, ((IDArray_Type)jcasType).casFeatCode_idType);}
    
  /** setter for idType - sets  
   * @generated */
  public void setIdType(String v) {
    if (IDArray_Type.featOkTst && ((IDArray_Type)jcasType).casFeat_idType == null)
      jcasType.jcas.throwFeatMissing("idType", "org.u_compare.shared.comparable.IDArray");
    jcasType.ll_cas.ll_setStringValue(addr, ((IDArray_Type)jcasType).casFeatCode_idType, v);}    
  }

    