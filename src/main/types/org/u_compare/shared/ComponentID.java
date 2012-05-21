

/* First created by JCasGen Wed Mar 16 10:14:04 CET 2011 */
package org.u_compare.shared;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.TOP;


/** 
 * Updated by JCasGen Tue Mar 06 16:28:13 CET 2012
 * XML source: /vol/home-vol3/wbi/wbi_stud/VLKB/Tim/vlkb/chemspotVSoscar3/desc/cr/iobCR.xml
 * @generated */
public class ComponentID extends TOP {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(ComponentID.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected ComponentID() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public ComponentID(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public ComponentID(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
 
    
  //*--------------*
  //* Feature: componentID

  /** getter for componentID - gets 
   * @generated */
  public String getComponentID() {
    if (ComponentID_Type.featOkTst && ((ComponentID_Type)jcasType).casFeat_componentID == null)
      jcasType.jcas.throwFeatMissing("componentID", "org.u_compare.shared.ComponentID");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ComponentID_Type)jcasType).casFeatCode_componentID);}
    
  /** setter for componentID - sets  
   * @generated */
  public void setComponentID(String v) {
    if (ComponentID_Type.featOkTst && ((ComponentID_Type)jcasType).casFeat_componentID == null)
      jcasType.jcas.throwFeatMissing("componentID", "org.u_compare.shared.ComponentID");
    jcasType.ll_cas.ll_setStringValue(addr, ((ComponentID_Type)jcasType).casFeatCode_componentID, v);}    
  }

    