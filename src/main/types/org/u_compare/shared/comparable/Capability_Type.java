
/* First created by JCasGen Wed Mar 16 10:14:04 CET 2011 */
package org.u_compare.shared.comparable;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.cas.TOP_Type;

/** 
 * Updated by JCasGen Tue Mar 06 16:28:13 CET 2012
 * @generated */
public class Capability_Type extends TOP_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Capability_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Capability_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Capability(addr, Capability_Type.this);
  			   Capability_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Capability(addr, Capability_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = Capability.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.u_compare.shared.comparable.Capability");
 
  /** @generated */
  final Feature casFeat_types;
  /** @generated */
  final int     casFeatCode_types;
  /** @generated */ 
  public int getTypes(int addr) {
        if (featOkTst && casFeat_types == null)
      jcas.throwFeatMissing("types", "org.u_compare.shared.comparable.Capability");
    return ll_cas.ll_getRefValue(addr, casFeatCode_types);
  }
  /** @generated */    
  public void setTypes(int addr, int v) {
        if (featOkTst && casFeat_types == null)
      jcas.throwFeatMissing("types", "org.u_compare.shared.comparable.Capability");
    ll_cas.ll_setRefValue(addr, casFeatCode_types, v);}
    
   /** @generated */
  public String getTypes(int addr, int i) {
        if (featOkTst && casFeat_types == null)
      jcas.throwFeatMissing("types", "org.u_compare.shared.comparable.Capability");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_types), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_types), i);
  return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_types), i);
  }
   
  /** @generated */ 
  public void setTypes(int addr, int i, String v) {
        if (featOkTst && casFeat_types == null)
      jcas.throwFeatMissing("types", "org.u_compare.shared.comparable.Capability");
    if (lowLevelTypeChecks)
      ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_types), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_types), i);
    ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_types), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Capability_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_types = jcas.getRequiredFeatureDE(casType, "types", "uima.cas.StringArray", featOkTst);
    casFeatCode_types  = (null == casFeat_types) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_types).getCode();

  }
}



    