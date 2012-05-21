

/* First created by JCasGen Wed Mar 16 10:14:24 CET 2011 */
package opennlp.uima;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Tue Mar 06 16:28:13 CET 2012
 * XML source: /vol/home-vol3/wbi/wbi_stud/VLKB/Tim/vlkb/chemspotVSoscar3/desc/cr/iobCR.xml
 * @generated */
public class Chunk extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(Chunk.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Chunk() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Chunk(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Chunk(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Chunk(JCas jcas, int begin, int end) {
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
  //* Feature: TODO_type

  /** getter for TODO_type - gets 
   * @generated */
  public String getTODO_type() {
    if (Chunk_Type.featOkTst && ((Chunk_Type)jcasType).casFeat_TODO_type == null)
      jcasType.jcas.throwFeatMissing("TODO_type", "opennlp.uima.Chunk");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Chunk_Type)jcasType).casFeatCode_TODO_type);}
    
  /** setter for TODO_type - sets  
   * @generated */
  public void setTODO_type(String v) {
    if (Chunk_Type.featOkTst && ((Chunk_Type)jcasType).casFeat_TODO_type == null)
      jcasType.jcas.throwFeatMissing("TODO_type", "opennlp.uima.Chunk");
    jcasType.ll_cas.ll_setStringValue(addr, ((Chunk_Type)jcasType).casFeatCode_TODO_type, v);}    
  }

    