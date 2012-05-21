
/* First created by JCasGen Thu Jul 14 11:57:20 CEST 2011 */
package org.u_compare.shared.semantic;

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
 * Updated by JCasGen Tue Mar 06 16:28:19 CET 2012
 * @generated */
public class Drug_Type extends NamedEntity_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Drug_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Drug_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Drug(addr, Drug_Type.this);
  			   Drug_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Drug(addr, Drug_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = Drug.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.u_compare.shared.semantic.Drug");
 
  /** @generated */
  final Feature casFeat_dbID;
  /** @generated */
  final int     casFeatCode_dbID;
  /** @generated */ 
  public String getDbID(int addr) {
        if (featOkTst && casFeat_dbID == null)
      jcas.throwFeatMissing("dbID", "org.u_compare.shared.semantic.Drug");
    return ll_cas.ll_getStringValue(addr, casFeatCode_dbID);
  }
  /** @generated */    
  public void setDbID(int addr, String v) {
        if (featOkTst && casFeat_dbID == null)
      jcas.throwFeatMissing("dbID", "org.u_compare.shared.semantic.Drug");
    ll_cas.ll_setStringValue(addr, casFeatCode_dbID, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Drug_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_dbID = jcas.getRequiredFeatureDE(casType, "dbID", "uima.cas.String", featOkTst);
    casFeatCode_dbID  = (null == casFeat_dbID) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_dbID).getCode();

  }
}



    