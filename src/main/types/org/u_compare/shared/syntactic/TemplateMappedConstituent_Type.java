
/* First created by JCasGen Wed Mar 16 10:14:09 CET 2011 */
package org.u_compare.shared.syntactic;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** 
 * Updated by JCasGen Tue Mar 06 16:28:20 CET 2012
 * @generated */
public class TemplateMappedConstituent_Type extends FunctionTaggedConstituent_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (TemplateMappedConstituent_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = TemplateMappedConstituent_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new TemplateMappedConstituent(addr, TemplateMappedConstituent_Type.this);
  			   TemplateMappedConstituent_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new TemplateMappedConstituent(addr, TemplateMappedConstituent_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = TemplateMappedConstituent.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.u_compare.shared.syntactic.TemplateMappedConstituent");
 
  /** @generated */
  final Feature casFeat_mappedConstituent;
  /** @generated */
  final int     casFeatCode_mappedConstituent;
  /** @generated */ 
  public int getMappedConstituent(int addr) {
        if (featOkTst && casFeat_mappedConstituent == null)
      jcas.throwFeatMissing("mappedConstituent", "org.u_compare.shared.syntactic.TemplateMappedConstituent");
    return ll_cas.ll_getRefValue(addr, casFeatCode_mappedConstituent);
  }
  /** @generated */    
  public void setMappedConstituent(int addr, int v) {
        if (featOkTst && casFeat_mappedConstituent == null)
      jcas.throwFeatMissing("mappedConstituent", "org.u_compare.shared.syntactic.TemplateMappedConstituent");
    ll_cas.ll_setRefValue(addr, casFeatCode_mappedConstituent, v);}
    
  
 
  /** @generated */
  final Feature casFeat_not;
  /** @generated */
  final int     casFeatCode_not;
  /** @generated */ 
  public boolean getNot(int addr) {
        if (featOkTst && casFeat_not == null)
      jcas.throwFeatMissing("not", "org.u_compare.shared.syntactic.TemplateMappedConstituent");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_not);
  }
  /** @generated */    
  public void setNot(int addr, boolean v) {
        if (featOkTst && casFeat_not == null)
      jcas.throwFeatMissing("not", "org.u_compare.shared.syntactic.TemplateMappedConstituent");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_not, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public TemplateMappedConstituent_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_mappedConstituent = jcas.getRequiredFeatureDE(casType, "mappedConstituent", "org.u_compare.shared.syntactic.Constituent", featOkTst);
    casFeatCode_mappedConstituent  = (null == casFeat_mappedConstituent) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_mappedConstituent).getCode();

 
    casFeat_not = jcas.getRequiredFeatureDE(casType, "not", "uima.cas.Boolean", featOkTst);
    casFeatCode_not  = (null == casFeat_not) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_not).getCode();

  }
}



    