
/* First created by JCasGen Wed Mar 16 10:14:04 CET 2011 */
package org.u_compare.shared.comparable;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

/** 
 * Updated by JCasGen Tue Mar 06 16:28:14 CET 2012
 * @generated */
public class IDArray_Type extends TOP_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (IDArray_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = IDArray_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new IDArray(addr, IDArray_Type.this);
  			   IDArray_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new IDArray(addr, IDArray_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = IDArray.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.u_compare.shared.comparable.IDArray");
 
  /** @generated */
  final Feature casFeat_array;
  /** @generated */
  final int     casFeatCode_array;
  /** @generated */ 
  public int getArray(int addr) {
        if (featOkTst && casFeat_array == null)
      jcas.throwFeatMissing("array", "org.u_compare.shared.comparable.IDArray");
    return ll_cas.ll_getRefValue(addr, casFeatCode_array);
  }
  /** @generated */    
  public void setArray(int addr, int v) {
        if (featOkTst && casFeat_array == null)
      jcas.throwFeatMissing("array", "org.u_compare.shared.comparable.IDArray");
    ll_cas.ll_setRefValue(addr, casFeatCode_array, v);}
    
   /** @generated */
  public int getArray(int addr, int i) {
        if (featOkTst && casFeat_array == null)
      jcas.throwFeatMissing("array", "org.u_compare.shared.comparable.IDArray");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_array), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_array), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_array), i);
  }
   
  /** @generated */ 
  public void setArray(int addr, int i, int v) {
        if (featOkTst && casFeat_array == null)
      jcas.throwFeatMissing("array", "org.u_compare.shared.comparable.IDArray");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_array), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_array), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_array), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_idType;
  /** @generated */
  final int     casFeatCode_idType;
  /** @generated */ 
  public String getIdType(int addr) {
        if (featOkTst && casFeat_idType == null)
      jcas.throwFeatMissing("idType", "org.u_compare.shared.comparable.IDArray");
    return ll_cas.ll_getStringValue(addr, casFeatCode_idType);
  }
  /** @generated */    
  public void setIdType(int addr, String v) {
        if (featOkTst && casFeat_idType == null)
      jcas.throwFeatMissing("idType", "org.u_compare.shared.comparable.IDArray");
    ll_cas.ll_setStringValue(addr, casFeatCode_idType, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public IDArray_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_array = jcas.getRequiredFeatureDE(casType, "array", "uima.cas.FSArray", featOkTst);
    casFeatCode_array  = (null == casFeat_array) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_array).getCode();

 
    casFeat_idType = jcas.getRequiredFeatureDE(casType, "idType", "uima.cas.String", featOkTst);
    casFeatCode_idType  = (null == casFeat_idType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_idType).getCode();

  }
}



    