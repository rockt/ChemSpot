
/* First created by JCasGen Wed Mar 16 10:14:08 CET 2011 */
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
public class RichToken_Type extends POSToken_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (RichToken_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = RichToken_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new RichToken(addr, RichToken_Type.this);
  			   RichToken_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new RichToken(addr, RichToken_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = RichToken.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.u_compare.shared.syntactic.RichToken");
 
  /** @generated */
  final Feature casFeat_base;
  /** @generated */
  final int     casFeatCode_base;
  /** @generated */ 
  public String getBase(int addr) {
        if (featOkTst && casFeat_base == null)
      jcas.throwFeatMissing("base", "org.u_compare.shared.syntactic.RichToken");
    return ll_cas.ll_getStringValue(addr, casFeatCode_base);
  }
  /** @generated */    
  public void setBase(int addr, String v) {
        if (featOkTst && casFeat_base == null)
      jcas.throwFeatMissing("base", "org.u_compare.shared.syntactic.RichToken");
    ll_cas.ll_setStringValue(addr, casFeatCode_base, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public RichToken_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_base = jcas.getRequiredFeatureDE(casType, "base", "uima.cas.String", featOkTst);
    casFeatCode_base  = (null == casFeat_base) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_base).getCode();

  }
}



    