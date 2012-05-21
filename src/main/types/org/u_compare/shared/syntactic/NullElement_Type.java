
/* First created by JCasGen Wed Mar 16 10:14:08 CET 2011 */
package org.u_compare.shared.syntactic;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;

/** 
 * Updated by JCasGen Tue Mar 06 16:28:20 CET 2012
 * @generated */
public class NullElement_Type extends AbstractConstituent_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (NullElement_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = NullElement_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new NullElement(addr, NullElement_Type.this);
  			   NullElement_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new NullElement(addr, NullElement_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = NullElement.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.u_compare.shared.syntactic.NullElement");
 
  /** @generated */
  final Feature casFeat_label;
  /** @generated */
  final int     casFeatCode_label;
  /** @generated */ 
  public int getLabel(int addr) {
        if (featOkTst && casFeat_label == null)
      jcas.throwFeatMissing("label", "org.u_compare.shared.syntactic.NullElement");
    return ll_cas.ll_getRefValue(addr, casFeatCode_label);
  }
  /** @generated */    
  public void setLabel(int addr, int v) {
        if (featOkTst && casFeat_label == null)
      jcas.throwFeatMissing("label", "org.u_compare.shared.syntactic.NullElement");
    ll_cas.ll_setRefValue(addr, casFeatCode_label, v);}
    
  
 
  /** @generated */
  final Feature casFeat_coindexed;
  /** @generated */
  final int     casFeatCode_coindexed;
  /** @generated */ 
  public int getCoindexed(int addr) {
        if (featOkTst && casFeat_coindexed == null)
      jcas.throwFeatMissing("coindexed", "org.u_compare.shared.syntactic.NullElement");
    return ll_cas.ll_getRefValue(addr, casFeatCode_coindexed);
  }
  /** @generated */    
  public void setCoindexed(int addr, int v) {
        if (featOkTst && casFeat_coindexed == null)
      jcas.throwFeatMissing("coindexed", "org.u_compare.shared.syntactic.NullElement");
    ll_cas.ll_setRefValue(addr, casFeatCode_coindexed, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public NullElement_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_label = jcas.getRequiredFeatureDE(casType, "label", "org.u_compare.shared.label.penn.coindex.NullElementLabel", featOkTst);
    casFeatCode_label  = (null == casFeat_label) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_label).getCode();

 
    casFeat_coindexed = jcas.getRequiredFeatureDE(casType, "coindexed", "org.u_compare.shared.syntactic.Constituent", featOkTst);
    casFeatCode_coindexed  = (null == casFeat_coindexed) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_coindexed).getCode();

  }
}



    