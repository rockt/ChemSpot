

/* First created by JCasGen Wed Mar 16 10:14:06 CET 2011 */
package org.u_compare.shared.label.penn.pos;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.u_compare.shared.label.POS;


/** 
 * Updated by JCasGen Tue Mar 06 16:28:16 CET 2012
 * XML source: /vol/home-vol3/wbi/wbi_stud/VLKB/Tim/vlkb/chemspotVSoscar3/desc/cr/iobCR.xml
 * @generated */
public class UnknownPOS extends POS {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(UnknownPOS.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected UnknownPOS() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public UnknownPOS(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public UnknownPOS(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
 
    
  //*--------------*
  //* Feature: posType

  /** getter for posType - gets 
   * @generated */
  public String getPosType() {
    if (UnknownPOS_Type.featOkTst && ((UnknownPOS_Type)jcasType).casFeat_posType == null)
      jcasType.jcas.throwFeatMissing("posType", "org.u_compare.shared.label.penn.pos.UnknownPOS");
    return jcasType.ll_cas.ll_getStringValue(addr, ((UnknownPOS_Type)jcasType).casFeatCode_posType);}
    
  /** setter for posType - sets  
   * @generated */
  public void setPosType(String v) {
    if (UnknownPOS_Type.featOkTst && ((UnknownPOS_Type)jcasType).casFeat_posType == null)
      jcasType.jcas.throwFeatMissing("posType", "org.u_compare.shared.label.penn.pos.UnknownPOS");
    jcasType.ll_cas.ll_setStringValue(addr, ((UnknownPOS_Type)jcasType).casFeatCode_posType, v);}    
  }

    