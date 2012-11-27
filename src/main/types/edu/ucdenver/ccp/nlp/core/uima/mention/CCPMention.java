

/* First created by JCasGen Tue Oct 02 12:01:59 CEST 2012 */
package edu.ucdenver.ccp.nlp.core.uima.mention;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.jcas.cas.StringArray;


/** The superclass for all CCP Mentions (class mention, complex slot mention, and non-complex slot mention)
 * Updated by JCasGen Tue Oct 02 12:01:59 CEST 2012
 * XML source: D:/ChemSpot/git/ChemSpot/desc/cr/CraftCR.xml
 * @generated */
public class CCPMention extends TOP {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(CCPMention.class);
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
  protected CCPMention() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public CCPMention(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public CCPMention(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: mentionName

  /** getter for mentionName - gets The name of this mention.
   * @generated */
  public String getMentionName() {
    if (CCPMention_Type.featOkTst && ((CCPMention_Type)jcasType).casFeat_mentionName == null)
      jcasType.jcas.throwFeatMissing("mentionName", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPMention");
    return jcasType.ll_cas.ll_getStringValue(addr, ((CCPMention_Type)jcasType).casFeatCode_mentionName);}
    
  /** setter for mentionName - sets The name of this mention. 
   * @generated */
  public void setMentionName(String v) {
    if (CCPMention_Type.featOkTst && ((CCPMention_Type)jcasType).casFeat_mentionName == null)
      jcasType.jcas.throwFeatMissing("mentionName", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPMention");
    jcasType.ll_cas.ll_setStringValue(addr, ((CCPMention_Type)jcasType).casFeatCode_mentionName, v);}    
   
    
  //*--------------*
  //* Feature: mentionID

  /** getter for mentionID - gets 
   * @generated */
  public long getMentionID() {
    if (CCPMention_Type.featOkTst && ((CCPMention_Type)jcasType).casFeat_mentionID == null)
      jcasType.jcas.throwFeatMissing("mentionID", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPMention");
    return jcasType.ll_cas.ll_getLongValue(addr, ((CCPMention_Type)jcasType).casFeatCode_mentionID);}
    
  /** setter for mentionID - sets  
   * @generated */
  public void setMentionID(long v) {
    if (CCPMention_Type.featOkTst && ((CCPMention_Type)jcasType).casFeat_mentionID == null)
      jcasType.jcas.throwFeatMissing("mentionID", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPMention");
    jcasType.ll_cas.ll_setLongValue(addr, ((CCPMention_Type)jcasType).casFeatCode_mentionID, v);}    
   
    
  //*--------------*
  //* Feature: traversalIDs

  /** getter for traversalIDs - gets 
   * @generated */
  public StringArray getTraversalIDs() {
    if (CCPMention_Type.featOkTst && ((CCPMention_Type)jcasType).casFeat_traversalIDs == null)
      jcasType.jcas.throwFeatMissing("traversalIDs", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPMention");
    return (StringArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((CCPMention_Type)jcasType).casFeatCode_traversalIDs)));}
    
  /** setter for traversalIDs - sets  
   * @generated */
  public void setTraversalIDs(StringArray v) {
    if (CCPMention_Type.featOkTst && ((CCPMention_Type)jcasType).casFeat_traversalIDs == null)
      jcasType.jcas.throwFeatMissing("traversalIDs", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPMention");
    jcasType.ll_cas.ll_setRefValue(addr, ((CCPMention_Type)jcasType).casFeatCode_traversalIDs, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for traversalIDs - gets an indexed value - 
   * @generated */
  public String getTraversalIDs(int i) {
    if (CCPMention_Type.featOkTst && ((CCPMention_Type)jcasType).casFeat_traversalIDs == null)
      jcasType.jcas.throwFeatMissing("traversalIDs", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPMention");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((CCPMention_Type)jcasType).casFeatCode_traversalIDs), i);
    return jcasType.ll_cas.ll_getStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((CCPMention_Type)jcasType).casFeatCode_traversalIDs), i);}

  /** indexed setter for traversalIDs - sets an indexed value - 
   * @generated */
  public void setTraversalIDs(int i, String v) { 
    if (CCPMention_Type.featOkTst && ((CCPMention_Type)jcasType).casFeat_traversalIDs == null)
      jcasType.jcas.throwFeatMissing("traversalIDs", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPMention");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((CCPMention_Type)jcasType).casFeatCode_traversalIDs), i);
    jcasType.ll_cas.ll_setStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((CCPMention_Type)jcasType).casFeatCode_traversalIDs), i, v);}
   
    
  //*--------------*
  //* Feature: traversalMentionIDs

  /** getter for traversalMentionIDs - gets 
   * @generated */
  public StringArray getTraversalMentionIDs() {
    if (CCPMention_Type.featOkTst && ((CCPMention_Type)jcasType).casFeat_traversalMentionIDs == null)
      jcasType.jcas.throwFeatMissing("traversalMentionIDs", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPMention");
    return (StringArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((CCPMention_Type)jcasType).casFeatCode_traversalMentionIDs)));}
    
  /** setter for traversalMentionIDs - sets  
   * @generated */
  public void setTraversalMentionIDs(StringArray v) {
    if (CCPMention_Type.featOkTst && ((CCPMention_Type)jcasType).casFeat_traversalMentionIDs == null)
      jcasType.jcas.throwFeatMissing("traversalMentionIDs", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPMention");
    jcasType.ll_cas.ll_setRefValue(addr, ((CCPMention_Type)jcasType).casFeatCode_traversalMentionIDs, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for traversalMentionIDs - gets an indexed value - 
   * @generated */
  public String getTraversalMentionIDs(int i) {
    if (CCPMention_Type.featOkTst && ((CCPMention_Type)jcasType).casFeat_traversalMentionIDs == null)
      jcasType.jcas.throwFeatMissing("traversalMentionIDs", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPMention");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((CCPMention_Type)jcasType).casFeatCode_traversalMentionIDs), i);
    return jcasType.ll_cas.ll_getStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((CCPMention_Type)jcasType).casFeatCode_traversalMentionIDs), i);}

  /** indexed setter for traversalMentionIDs - sets an indexed value - 
   * @generated */
  public void setTraversalMentionIDs(int i, String v) { 
    if (CCPMention_Type.featOkTst && ((CCPMention_Type)jcasType).casFeat_traversalMentionIDs == null)
      jcasType.jcas.throwFeatMissing("traversalMentionIDs", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPMention");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((CCPMention_Type)jcasType).casFeatCode_traversalMentionIDs), i);
    jcasType.ll_cas.ll_setStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((CCPMention_Type)jcasType).casFeatCode_traversalMentionIDs), i, v);}
  }

    