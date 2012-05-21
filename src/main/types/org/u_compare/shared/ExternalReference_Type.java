
/* First created by JCasGen Wed Mar 16 10:14:04 CET 2011 */
package org.u_compare.shared;

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
public class ExternalReference_Type extends TOP_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (ExternalReference_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = ExternalReference_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new ExternalReference(addr, ExternalReference_Type.this);
  			   ExternalReference_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new ExternalReference(addr, ExternalReference_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = ExternalReference.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.u_compare.shared.ExternalReference");
 
  /** @generated */
  final Feature casFeat_namespace;
  /** @generated */
  final int     casFeatCode_namespace;
  /** @generated */ 
  public String getNamespace(int addr) {
        if (featOkTst && casFeat_namespace == null)
      jcas.throwFeatMissing("namespace", "org.u_compare.shared.ExternalReference");
    return ll_cas.ll_getStringValue(addr, casFeatCode_namespace);
  }
  /** @generated */    
  public void setNamespace(int addr, String v) {
        if (featOkTst && casFeat_namespace == null)
      jcas.throwFeatMissing("namespace", "org.u_compare.shared.ExternalReference");
    ll_cas.ll_setStringValue(addr, casFeatCode_namespace, v);}
    
  
 
  /** @generated */
  final Feature casFeat_ID;
  /** @generated */
  final int     casFeatCode_ID;
  /** @generated */ 
  public String getID(int addr) {
        if (featOkTst && casFeat_ID == null)
      jcas.throwFeatMissing("ID", "org.u_compare.shared.ExternalReference");
    return ll_cas.ll_getStringValue(addr, casFeatCode_ID);
  }
  /** @generated */    
  public void setID(int addr, String v) {
        if (featOkTst && casFeat_ID == null)
      jcas.throwFeatMissing("ID", "org.u_compare.shared.ExternalReference");
    ll_cas.ll_setStringValue(addr, casFeatCode_ID, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public ExternalReference_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_namespace = jcas.getRequiredFeatureDE(casType, "namespace", "uima.cas.String", featOkTst);
    casFeatCode_namespace  = (null == casFeat_namespace) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_namespace).getCode();

 
    casFeat_ID = jcas.getRequiredFeatureDE(casType, "ID", "uima.cas.String", featOkTst);
    casFeatCode_ID  = (null == casFeat_ID) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_ID).getCode();

  }
}



    