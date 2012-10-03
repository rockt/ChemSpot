

/* First created by JCasGen Tue Oct 02 12:01:59 CEST 2012 */
package edu.ucdenver.ccp.nlp.core.uima.annotation;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import edu.ucdenver.ccp.nlp.core.uima.mention.CCPClassMention;


/** The CCP TextAnnotation extends the base annotation class to include an annotation ID, the capability for multiple annotation spans, a link to the annotator responsible for generating the annotation, membership to annotation sets, and a link to a class mention which defines the class of this annotation.
 * Updated by JCasGen Tue Oct 02 12:01:59 CEST 2012
 * XML source: D:/ChemSpot/git/ChemSpot/desc/cr/CraftCR.xml
 * @generated */
public class CCPTextAnnotation extends CCPAnnotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(CCPTextAnnotation.class);
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
  protected CCPTextAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public CCPTextAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public CCPTextAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public CCPTextAnnotation(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: annotationID

  /** getter for annotationID - gets The annotation ID provides a means for identifying a particular annotation. Setting this ID is optional. The default value should be -1.
   * @generated */
  public int getAnnotationID() {
    if (CCPTextAnnotation_Type.featOkTst && ((CCPTextAnnotation_Type)jcasType).casFeat_annotationID == null)
      jcasType.jcas.throwFeatMissing("annotationID", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation");
    return jcasType.ll_cas.ll_getIntValue(addr, ((CCPTextAnnotation_Type)jcasType).casFeatCode_annotationID);}
    
  /** setter for annotationID - sets The annotation ID provides a means for identifying a particular annotation. Setting this ID is optional. The default value should be -1. 
   * @generated */
  public void setAnnotationID(int v) {
    if (CCPTextAnnotation_Type.featOkTst && ((CCPTextAnnotation_Type)jcasType).casFeat_annotationID == null)
      jcasType.jcas.throwFeatMissing("annotationID", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation");
    jcasType.ll_cas.ll_setIntValue(addr, ((CCPTextAnnotation_Type)jcasType).casFeatCode_annotationID, v);}    
   
    
  //*--------------*
  //* Feature: annotator

  /** getter for annotator - gets The annotator was responsible for generating this annotation.
   * @generated */
  public CCPAnnotator getAnnotator() {
    if (CCPTextAnnotation_Type.featOkTst && ((CCPTextAnnotation_Type)jcasType).casFeat_annotator == null)
      jcasType.jcas.throwFeatMissing("annotator", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation");
    return (CCPAnnotator)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((CCPTextAnnotation_Type)jcasType).casFeatCode_annotator)));}
    
  /** setter for annotator - sets The annotator was responsible for generating this annotation. 
   * @generated */
  public void setAnnotator(CCPAnnotator v) {
    if (CCPTextAnnotation_Type.featOkTst && ((CCPTextAnnotation_Type)jcasType).casFeat_annotator == null)
      jcasType.jcas.throwFeatMissing("annotator", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((CCPTextAnnotation_Type)jcasType).casFeatCode_annotator, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: documentSectionID

  /** getter for documentSectionID - gets The document section ID is optionally used to log what section of a document this annotation is from. Values can be specified by the user. See edu.uchsc.ccp.util.nlp.document.DocumentSectionTypes for a few common sections.
   * @generated */
  public int getDocumentSectionID() {
    if (CCPTextAnnotation_Type.featOkTst && ((CCPTextAnnotation_Type)jcasType).casFeat_documentSectionID == null)
      jcasType.jcas.throwFeatMissing("documentSectionID", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation");
    return jcasType.ll_cas.ll_getIntValue(addr, ((CCPTextAnnotation_Type)jcasType).casFeatCode_documentSectionID);}
    
  /** setter for documentSectionID - sets The document section ID is optionally used to log what section of a document this annotation is from. Values can be specified by the user. See edu.uchsc.ccp.util.nlp.document.DocumentSectionTypes for a few common sections. 
   * @generated */
  public void setDocumentSectionID(int v) {
    if (CCPTextAnnotation_Type.featOkTst && ((CCPTextAnnotation_Type)jcasType).casFeat_documentSectionID == null)
      jcasType.jcas.throwFeatMissing("documentSectionID", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation");
    jcasType.ll_cas.ll_setIntValue(addr, ((CCPTextAnnotation_Type)jcasType).casFeatCode_documentSectionID, v);}    
   
    
  //*--------------*
  //* Feature: annotationSets

  /** getter for annotationSets - gets Annotation Sets provide an arbitrary means of categorizing and clustering annotations into groups.
   * @generated */
  public FSArray getAnnotationSets() {
    if (CCPTextAnnotation_Type.featOkTst && ((CCPTextAnnotation_Type)jcasType).casFeat_annotationSets == null)
      jcasType.jcas.throwFeatMissing("annotationSets", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((CCPTextAnnotation_Type)jcasType).casFeatCode_annotationSets)));}
    
  /** setter for annotationSets - sets Annotation Sets provide an arbitrary means of categorizing and clustering annotations into groups. 
   * @generated */
  public void setAnnotationSets(FSArray v) {
    if (CCPTextAnnotation_Type.featOkTst && ((CCPTextAnnotation_Type)jcasType).casFeat_annotationSets == null)
      jcasType.jcas.throwFeatMissing("annotationSets", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((CCPTextAnnotation_Type)jcasType).casFeatCode_annotationSets, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for annotationSets - gets an indexed value - Annotation Sets provide an arbitrary means of categorizing and clustering annotations into groups.
   * @generated */
  public CCPAnnotationSet getAnnotationSets(int i) {
    if (CCPTextAnnotation_Type.featOkTst && ((CCPTextAnnotation_Type)jcasType).casFeat_annotationSets == null)
      jcasType.jcas.throwFeatMissing("annotationSets", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((CCPTextAnnotation_Type)jcasType).casFeatCode_annotationSets), i);
    return (CCPAnnotationSet)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((CCPTextAnnotation_Type)jcasType).casFeatCode_annotationSets), i)));}

  /** indexed setter for annotationSets - sets an indexed value - Annotation Sets provide an arbitrary means of categorizing and clustering annotations into groups.
   * @generated */
  public void setAnnotationSets(int i, CCPAnnotationSet v) { 
    if (CCPTextAnnotation_Type.featOkTst && ((CCPTextAnnotation_Type)jcasType).casFeat_annotationSets == null)
      jcasType.jcas.throwFeatMissing("annotationSets", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((CCPTextAnnotation_Type)jcasType).casFeatCode_annotationSets), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((CCPTextAnnotation_Type)jcasType).casFeatCode_annotationSets), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: numberOfSpans

  /** getter for numberOfSpans - gets The number of spans comprising this annotation. The CCP TextAnnotation allows the use of multiple spans for a single annotation.
   * @generated */
  public int getNumberOfSpans() {
    if (CCPTextAnnotation_Type.featOkTst && ((CCPTextAnnotation_Type)jcasType).casFeat_numberOfSpans == null)
      jcasType.jcas.throwFeatMissing("numberOfSpans", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation");
    return jcasType.ll_cas.ll_getIntValue(addr, ((CCPTextAnnotation_Type)jcasType).casFeatCode_numberOfSpans);}
    
  /** setter for numberOfSpans - sets The number of spans comprising this annotation. The CCP TextAnnotation allows the use of multiple spans for a single annotation. 
   * @generated */
  public void setNumberOfSpans(int v) {
    if (CCPTextAnnotation_Type.featOkTst && ((CCPTextAnnotation_Type)jcasType).casFeat_numberOfSpans == null)
      jcasType.jcas.throwFeatMissing("numberOfSpans", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation");
    jcasType.ll_cas.ll_setIntValue(addr, ((CCPTextAnnotation_Type)jcasType).casFeatCode_numberOfSpans, v);}    
   
    
  //*--------------*
  //* Feature: spans

  /** getter for spans - gets This FSArray stores the CCPSpans which comprise this annotation. It should be noted that for an annotation with multiple spans, the default begin and end fields are set to the beginning of the first span and the end of the final span, respectively.
   * @generated */
  public FSArray getSpans() {
    if (CCPTextAnnotation_Type.featOkTst && ((CCPTextAnnotation_Type)jcasType).casFeat_spans == null)
      jcasType.jcas.throwFeatMissing("spans", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((CCPTextAnnotation_Type)jcasType).casFeatCode_spans)));}
    
  /** setter for spans - sets This FSArray stores the CCPSpans which comprise this annotation. It should be noted that for an annotation with multiple spans, the default begin and end fields are set to the beginning of the first span and the end of the final span, respectively. 
   * @generated */
  public void setSpans(FSArray v) {
    if (CCPTextAnnotation_Type.featOkTst && ((CCPTextAnnotation_Type)jcasType).casFeat_spans == null)
      jcasType.jcas.throwFeatMissing("spans", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((CCPTextAnnotation_Type)jcasType).casFeatCode_spans, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for spans - gets an indexed value - This FSArray stores the CCPSpans which comprise this annotation. It should be noted that for an annotation with multiple spans, the default begin and end fields are set to the beginning of the first span and the end of the final span, respectively.
   * @generated */
  public CCPSpan getSpans(int i) {
    if (CCPTextAnnotation_Type.featOkTst && ((CCPTextAnnotation_Type)jcasType).casFeat_spans == null)
      jcasType.jcas.throwFeatMissing("spans", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((CCPTextAnnotation_Type)jcasType).casFeatCode_spans), i);
    return (CCPSpan)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((CCPTextAnnotation_Type)jcasType).casFeatCode_spans), i)));}

  /** indexed setter for spans - sets an indexed value - This FSArray stores the CCPSpans which comprise this annotation. It should be noted that for an annotation with multiple spans, the default begin and end fields are set to the beginning of the first span and the end of the final span, respectively.
   * @generated */
  public void setSpans(int i, CCPSpan v) { 
    if (CCPTextAnnotation_Type.featOkTst && ((CCPTextAnnotation_Type)jcasType).casFeat_spans == null)
      jcasType.jcas.throwFeatMissing("spans", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((CCPTextAnnotation_Type)jcasType).casFeatCode_spans), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((CCPTextAnnotation_Type)jcasType).casFeatCode_spans), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: classMention

  /** getter for classMention - gets The CCP ClassMention indicates the type (or class) for this annotation.
   * @generated */
  public CCPClassMention getClassMention() {
    if (CCPTextAnnotation_Type.featOkTst && ((CCPTextAnnotation_Type)jcasType).casFeat_classMention == null)
      jcasType.jcas.throwFeatMissing("classMention", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation");
    return (CCPClassMention)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((CCPTextAnnotation_Type)jcasType).casFeatCode_classMention)));}
    
  /** setter for classMention - sets The CCP ClassMention indicates the type (or class) for this annotation. 
   * @generated */
  public void setClassMention(CCPClassMention v) {
    if (CCPTextAnnotation_Type.featOkTst && ((CCPTextAnnotation_Type)jcasType).casFeat_classMention == null)
      jcasType.jcas.throwFeatMissing("classMention", "edu.ucdenver.ccp.nlp.core.uima.annotation.CCPTextAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((CCPTextAnnotation_Type)jcasType).casFeatCode_classMention, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    