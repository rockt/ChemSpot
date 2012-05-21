

/* First created by JCasGen Thu Jul 14 11:57:20 CEST 2011 */
package org.u_compare.shared.semantic;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Tue Mar 06 16:28:19 CET 2012
 * XML source: /vol/home-vol3/wbi/wbi_stud/VLKB/Tim/vlkb/chemspotVSoscar3/desc/cr/iobCR.xml
 * @generated */
public class Drug extends NamedEntity {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(Drug.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Drug() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Drug(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Drug(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Drug(JCas jcas, int begin, int end) {
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
  //* Feature: dbID

  /** getter for dbID - gets 
   * @generated */
  public String getDbID() {
    if (Drug_Type.featOkTst && ((Drug_Type)jcasType).casFeat_dbID == null)
      jcasType.jcas.throwFeatMissing("dbID", "org.u_compare.shared.semantic.Drug");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Drug_Type)jcasType).casFeatCode_dbID);}
    
  /** setter for dbID - sets  
   * @generated */
  public void setDbID(String v) {
    if (Drug_Type.featOkTst && ((Drug_Type)jcasType).casFeat_dbID == null)
      jcasType.jcas.throwFeatMissing("dbID", "org.u_compare.shared.semantic.Drug");
    jcasType.ll_cas.ll_setStringValue(addr, ((Drug_Type)jcasType).casFeatCode_dbID, v);}    
  }

    