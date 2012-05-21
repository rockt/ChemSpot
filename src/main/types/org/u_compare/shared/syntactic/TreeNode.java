

/* First created by JCasGen Wed Mar 16 10:14:09 CET 2011 */
package org.u_compare.shared.syntactic;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.TOP;


/** 
 * Updated by JCasGen Tue Mar 06 16:28:20 CET 2012
 * XML source: /vol/home-vol3/wbi/wbi_stud/VLKB/Tim/vlkb/chemspotVSoscar3/desc/cr/iobCR.xml
 * @generated */
public class TreeNode extends SyntacticAnnotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(TreeNode.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected TreeNode() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public TreeNode(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public TreeNode(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public TreeNode(JCas jcas, int begin, int end) {
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
  //* Feature: parent

  /** getter for parent - gets 
   * @generated */
  public TOP getParent() {
    if (TreeNode_Type.featOkTst && ((TreeNode_Type)jcasType).casFeat_parent == null)
      jcasType.jcas.throwFeatMissing("parent", "org.u_compare.shared.syntactic.TreeNode");
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((TreeNode_Type)jcasType).casFeatCode_parent)));}
    
  /** setter for parent - sets  
   * @generated */
  public void setParent(TOP v) {
    if (TreeNode_Type.featOkTst && ((TreeNode_Type)jcasType).casFeat_parent == null)
      jcasType.jcas.throwFeatMissing("parent", "org.u_compare.shared.syntactic.TreeNode");
    jcasType.ll_cas.ll_setRefValue(addr, ((TreeNode_Type)jcasType).casFeatCode_parent, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: children

  /** getter for children - gets 
   * @generated */
  public FSArray getChildren() {
    if (TreeNode_Type.featOkTst && ((TreeNode_Type)jcasType).casFeat_children == null)
      jcasType.jcas.throwFeatMissing("children", "org.u_compare.shared.syntactic.TreeNode");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((TreeNode_Type)jcasType).casFeatCode_children)));}
    
  /** setter for children - sets  
   * @generated */
  public void setChildren(FSArray v) {
    if (TreeNode_Type.featOkTst && ((TreeNode_Type)jcasType).casFeat_children == null)
      jcasType.jcas.throwFeatMissing("children", "org.u_compare.shared.syntactic.TreeNode");
    jcasType.ll_cas.ll_setRefValue(addr, ((TreeNode_Type)jcasType).casFeatCode_children, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for children - gets an indexed value - 
   * @generated */
  public TOP getChildren(int i) {
    if (TreeNode_Type.featOkTst && ((TreeNode_Type)jcasType).casFeat_children == null)
      jcasType.jcas.throwFeatMissing("children", "org.u_compare.shared.syntactic.TreeNode");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((TreeNode_Type)jcasType).casFeatCode_children), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((TreeNode_Type)jcasType).casFeatCode_children), i)));}

  /** indexed setter for children - sets an indexed value - 
   * @generated */
  public void setChildren(int i, TOP v) { 
    if (TreeNode_Type.featOkTst && ((TreeNode_Type)jcasType).casFeat_children == null)
      jcasType.jcas.throwFeatMissing("children", "org.u_compare.shared.syntactic.TreeNode");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((TreeNode_Type)jcasType).casFeatCode_children), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((TreeNode_Type)jcasType).casFeatCode_children), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    