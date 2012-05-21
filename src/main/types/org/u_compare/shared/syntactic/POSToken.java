

/* First created by JCasGen Wed Mar 16 10:14:08 CET 2011 */
package org.u_compare.shared.syntactic;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.u_compare.shared.label.POS;


/** 
 * Updated by JCasGen Tue Mar 06 16:28:20 CET 2012
 * XML source: /vol/home-vol3/wbi/wbi_stud/VLKB/Tim/vlkb/chemspotVSoscar3/desc/cr/iobCR.xml
 * @generated */
public class POSToken extends Token {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(POSToken.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected POSToken() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public POSToken(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public POSToken(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public POSToken(JCas jcas, int begin, int end) {
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
  //* Feature: pos

  /** getter for pos - gets 
   * @generated */
  public POS getPos() {
    if (POSToken_Type.featOkTst && ((POSToken_Type)jcasType).casFeat_pos == null)
      jcasType.jcas.throwFeatMissing("pos", "org.u_compare.shared.syntactic.POSToken");
    return (POS)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((POSToken_Type)jcasType).casFeatCode_pos)));}
    
  /** setter for pos - sets  
   * @generated */
  public void setPos(POS v) {
    if (POSToken_Type.featOkTst && ((POSToken_Type)jcasType).casFeat_pos == null)
      jcasType.jcas.throwFeatMissing("pos", "org.u_compare.shared.syntactic.POSToken");
    jcasType.ll_cas.ll_setRefValue(addr, ((POSToken_Type)jcasType).casFeatCode_pos, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: posString

  /** getter for posString - gets A special field which internally converts object of pos field into String value.
   * @generated */
  public String getPosString() {
    if (POSToken_Type.featOkTst && ((POSToken_Type)jcasType).casFeat_posString == null)
      jcasType.jcas.throwFeatMissing("posString", "org.u_compare.shared.syntactic.POSToken");
    return jcasType.ll_cas.ll_getStringValue(addr, ((POSToken_Type)jcasType).casFeatCode_posString);}
    
  /** setter for posString - sets A special field which internally converts object of pos field into String value. 
   * @generated */
  public void setPosString(String v) {
    if (POSToken_Type.featOkTst && ((POSToken_Type)jcasType).casFeat_posString == null)
      jcasType.jcas.throwFeatMissing("posString", "org.u_compare.shared.syntactic.POSToken");
    jcasType.ll_cas.ll_setStringValue(addr, ((POSToken_Type)jcasType).casFeatCode_posString, v);}    
  }

    