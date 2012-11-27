

/* First created by JCasGen Tue Oct 02 12:01:59 CEST 2012 */
package edu.ucdenver.ccp.nlp.core.uima.annotation.metadata;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** A subclass of the Gold Standard Property, the False Negative Property indicates a Gold Standard Annotation that was scored as a False Negative during an evaluation.
 * Updated by JCasGen Tue Oct 02 12:01:59 CEST 2012
 * XML source: D:/ChemSpot/git/ChemSpot/desc/cr/CraftCR.xml
 * @generated */
public class FalseNegativeProperty extends GoldStandardSetProperty {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(FalseNegativeProperty.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated  */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected FalseNegativeProperty() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public FalseNegativeProperty(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public FalseNegativeProperty(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
}

    