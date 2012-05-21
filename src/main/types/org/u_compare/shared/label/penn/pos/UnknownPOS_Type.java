
/* First created by JCasGen Wed Mar 16 10:14:06 CET 2011 */
package org.u_compare.shared.label.penn.pos;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.u_compare.shared.label.POS_Type;

/** 
 * Updated by JCasGen Tue Mar 06 16:28:16 CET 2012
 * @generated */
public class UnknownPOS_Type extends POS_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (UnknownPOS_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = UnknownPOS_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new UnknownPOS(addr, UnknownPOS_Type.this);
  			   UnknownPOS_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new UnknownPOS(addr, UnknownPOS_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = UnknownPOS.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.u_compare.shared.label.penn.pos.UnknownPOS");
 
  /** @generated */
  final Feature casFeat_posType;
  /** @generated */
  final int     casFeatCode_posType;
  /** @generated */ 
  public String getPosType(int addr) {
        if (featOkTst && casFeat_posType == null)
      jcas.throwFeatMissing("posType", "org.u_compare.shared.label.penn.pos.UnknownPOS");
    return ll_cas.ll_getStringValue(addr, casFeatCode_posType);
  }
  /** @generated */    
  public void setPosType(int addr, String v) {
        if (featOkTst && casFeat_posType == null)
      jcas.throwFeatMissing("posType", "org.u_compare.shared.label.penn.pos.UnknownPOS");
    ll_cas.ll_setStringValue(addr, casFeatCode_posType, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public UnknownPOS_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_posType = jcas.getRequiredFeatureDE(casType, "posType", "uima.cas.String", featOkTst);
    casFeatCode_posType  = (null == casFeat_posType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_posType).getCode();

  }
}



    