

/* First created by JCasGen Wed Mar 16 10:14:04 CET 2011 */
package org.u_compare.shared;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.jcas.cas.TOP_Type;


/** 
 * Updated by JCasGen Tue Mar 06 16:28:13 CET 2012
 * XML source: /vol/home-vol3/wbi/wbi_stud/VLKB/Tim/vlkb/chemspotVSoscar3/desc/cr/iobCR.xml
 * @generated */
public class ExternalReference extends TOP {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(ExternalReference.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected ExternalReference() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public ExternalReference(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public ExternalReference(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
 
    
  //*--------------*
  //* Feature: namespace

  /** getter for namespace - gets External resource namespace
   * @generated */
  public String getNamespace() {
    if (ExternalReference_Type.featOkTst && ((ExternalReference_Type)jcasType).casFeat_namespace == null)
      jcasType.jcas.throwFeatMissing("namespace", "org.u_compare.shared.ExternalReference");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ExternalReference_Type)jcasType).casFeatCode_namespace);}
    
  /** setter for namespace - sets External resource namespace 
   * @generated */
  public void setNamespace(String v) {
    if (ExternalReference_Type.featOkTst && ((ExternalReference_Type)jcasType).casFeat_namespace == null)
      jcasType.jcas.throwFeatMissing("namespace", "org.u_compare.shared.ExternalReference");
    jcasType.ll_cas.ll_setStringValue(addr, ((ExternalReference_Type)jcasType).casFeatCode_namespace, v);}    
   
    
  //*--------------*
  //* Feature: ID

  /** getter for ID - gets ID in the external resource namespace
   * @generated */
  public String getID() {
    if (ExternalReference_Type.featOkTst && ((ExternalReference_Type)jcasType).casFeat_ID == null)
      jcasType.jcas.throwFeatMissing("ID", "org.u_compare.shared.ExternalReference");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ExternalReference_Type)jcasType).casFeatCode_ID);}
    
  /** setter for ID - sets ID in the external resource namespace 
   * @generated */
  public void setID(String v) {
    if (ExternalReference_Type.featOkTst && ((ExternalReference_Type)jcasType).casFeat_ID == null)
      jcasType.jcas.throwFeatMissing("ID", "org.u_compare.shared.ExternalReference");
    jcasType.ll_cas.ll_setStringValue(addr, ((ExternalReference_Type)jcasType).casFeatCode_ID, v);}    
  }

    