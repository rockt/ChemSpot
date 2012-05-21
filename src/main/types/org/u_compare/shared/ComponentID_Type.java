
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
public class ComponentID_Type extends TOP_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (ComponentID_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = ComponentID_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new ComponentID(addr, ComponentID_Type.this);
  			   ComponentID_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new ComponentID(addr, ComponentID_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = ComponentID.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.u_compare.shared.ComponentID");
 
  /** @generated */
  final Feature casFeat_componentID;
  /** @generated */
  final int     casFeatCode_componentID;
  /** @generated */ 
  public String getComponentID(int addr) {
        if (featOkTst && casFeat_componentID == null)
      jcas.throwFeatMissing("componentID", "org.u_compare.shared.ComponentID");
    return ll_cas.ll_getStringValue(addr, casFeatCode_componentID);
  }
  /** @generated */    
  public void setComponentID(int addr, String v) {
        if (featOkTst && casFeat_componentID == null)
      jcas.throwFeatMissing("componentID", "org.u_compare.shared.ComponentID");
    ll_cas.ll_setStringValue(addr, casFeatCode_componentID, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public ComponentID_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_componentID = jcas.getRequiredFeatureDE(casType, "componentID", "uima.cas.String", featOkTst);
    casFeatCode_componentID  = (null == casFeat_componentID) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_componentID).getCode();

  }
}



    