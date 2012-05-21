
/* First created by JCasGen Wed Mar 16 10:14:04 CET 2011 */
package org.u_compare.shared;

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
 * Updated by JCasGen Tue Mar 06 16:28:13 CET 2012
 * @generated */
public class ReferenceAnnotation_Type extends BaseAnnotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (ReferenceAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = ReferenceAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new ReferenceAnnotation(addr, ReferenceAnnotation_Type.this);
  			   ReferenceAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new ReferenceAnnotation(addr, ReferenceAnnotation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = ReferenceAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.u_compare.shared.ReferenceAnnotation");
 
  /** @generated */
  final Feature casFeat_externalReference;
  /** @generated */
  final int     casFeatCode_externalReference;
  /** @generated */ 
  public int getExternalReference(int addr) {
        if (featOkTst && casFeat_externalReference == null)
      jcas.throwFeatMissing("externalReference", "org.u_compare.shared.ReferenceAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_externalReference);
  }
  /** @generated */    
  public void setExternalReference(int addr, int v) {
        if (featOkTst && casFeat_externalReference == null)
      jcas.throwFeatMissing("externalReference", "org.u_compare.shared.ReferenceAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_externalReference, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public ReferenceAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_externalReference = jcas.getRequiredFeatureDE(casType, "externalReference", "org.u_compare.shared.ExternalReference", featOkTst);
    casFeatCode_externalReference  = (null == casFeat_externalReference) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_externalReference).getCode();

  }
}



    