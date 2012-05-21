
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
public class POSToken_Type extends Token_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (POSToken_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = POSToken_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new POSToken(addr, POSToken_Type.this);
  			   POSToken_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new POSToken(addr, POSToken_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = POSToken.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.u_compare.shared.syntactic.POSToken");
 
  /** @generated */
  final Feature casFeat_pos;
  /** @generated */
  final int     casFeatCode_pos;
  /** @generated */ 
  public int getPos(int addr) {
        if (featOkTst && casFeat_pos == null)
      jcas.throwFeatMissing("pos", "org.u_compare.shared.syntactic.POSToken");
    return ll_cas.ll_getRefValue(addr, casFeatCode_pos);
  }
  /** @generated */    
  public void setPos(int addr, int v) {
        if (featOkTst && casFeat_pos == null)
      jcas.throwFeatMissing("pos", "org.u_compare.shared.syntactic.POSToken");
    ll_cas.ll_setRefValue(addr, casFeatCode_pos, v);}
    
  
 
  /** @generated */
  final Feature casFeat_posString;
  /** @generated */
  final int     casFeatCode_posString;
  /** @generated */ 
  public String getPosString(int addr) {
        if (featOkTst && casFeat_posString == null)
      jcas.throwFeatMissing("posString", "org.u_compare.shared.syntactic.POSToken");
    return ll_cas.ll_getStringValue(addr, casFeatCode_posString);
  }
  /** @generated */    
  public void setPosString(int addr, String v) {
        if (featOkTst && casFeat_posString == null)
      jcas.throwFeatMissing("posString", "org.u_compare.shared.syntactic.POSToken");
    ll_cas.ll_setStringValue(addr, casFeatCode_posString, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public POSToken_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_pos = jcas.getRequiredFeatureDE(casType, "pos", "org.u_compare.shared.label.POS", featOkTst);
    casFeatCode_pos  = (null == casFeat_pos) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_pos).getCode();

 
    casFeat_posString = jcas.getRequiredFeatureDE(casType, "posString", "uima.cas.String", featOkTst);
    casFeatCode_posString  = (null == casFeat_posString) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_posString).getCode();

  }
}



    