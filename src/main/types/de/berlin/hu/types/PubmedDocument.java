

/* First created by JCasGen Thu Sep 29 11:47:10 CEST 2011 */
package de.berlin.hu.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Tue Mar 06 16:28:13 CET 2012
 * XML source: /vol/home-vol3/wbi/wbi_stud/VLKB/Tim/vlkb/chemspotVSoscar3/desc/cr/iobCR.xml
 * @generated */
public class PubmedDocument extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(PubmedDocument.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected PubmedDocument() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public PubmedDocument(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public PubmedDocument(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public PubmedDocument(JCas jcas, int begin, int end) {
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
  //* Feature: pmid

  /** getter for pmid - gets 
   * @generated */
  public String getPmid() {
    if (PubmedDocument_Type.featOkTst && ((PubmedDocument_Type)jcasType).casFeat_pmid == null)
      jcasType.jcas.throwFeatMissing("pmid", "de.berlin.hu.types.PubmedDocument");
    return jcasType.ll_cas.ll_getStringValue(addr, ((PubmedDocument_Type)jcasType).casFeatCode_pmid);}
    
  /** setter for pmid - sets  
   * @generated */
  public void setPmid(String v) {
    if (PubmedDocument_Type.featOkTst && ((PubmedDocument_Type)jcasType).casFeat_pmid == null)
      jcasType.jcas.throwFeatMissing("pmid", "de.berlin.hu.types.PubmedDocument");
    jcasType.ll_cas.ll_setStringValue(addr, ((PubmedDocument_Type)jcasType).casFeatCode_pmid, v);}    
  }

    