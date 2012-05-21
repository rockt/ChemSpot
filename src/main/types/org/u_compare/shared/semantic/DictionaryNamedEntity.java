

/* First created by JCasGen Wed Mar 16 10:14:08 CET 2011 */
package org.u_compare.shared.semantic;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Tue Mar 06 16:28:19 CET 2012
 * XML source: /vol/home-vol3/wbi/wbi_stud/VLKB/Tim/vlkb/chemspotVSoscar3/desc/cr/iobCR.xml
 * @generated */
public class DictionaryNamedEntity extends NamedEntity {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(DictionaryNamedEntity.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected DictionaryNamedEntity() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public DictionaryNamedEntity(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public DictionaryNamedEntity(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public DictionaryNamedEntity(JCas jcas, int begin, int end) {
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
  //* Feature: surface

  /** getter for surface - gets Surface string in the dictionary which is used to match the string.
   * @generated */
  public String getSurface() {
    if (DictionaryNamedEntity_Type.featOkTst && ((DictionaryNamedEntity_Type)jcasType).casFeat_surface == null)
      jcasType.jcas.throwFeatMissing("surface", "org.u_compare.shared.semantic.DictionaryNamedEntity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((DictionaryNamedEntity_Type)jcasType).casFeatCode_surface);}
    
  /** setter for surface - sets Surface string in the dictionary which is used to match the string. 
   * @generated */
  public void setSurface(String v) {
    if (DictionaryNamedEntity_Type.featOkTst && ((DictionaryNamedEntity_Type)jcasType).casFeat_surface == null)
      jcasType.jcas.throwFeatMissing("surface", "org.u_compare.shared.semantic.DictionaryNamedEntity");
    jcasType.ll_cas.ll_setStringValue(addr, ((DictionaryNamedEntity_Type)jcasType).casFeatCode_surface, v);}    
  }

    