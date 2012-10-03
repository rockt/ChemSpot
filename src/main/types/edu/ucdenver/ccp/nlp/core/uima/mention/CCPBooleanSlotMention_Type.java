
/* First created by JCasGen Tue Oct 02 12:01:59 CEST 2012 */
package edu.ucdenver.ccp.nlp.core.uima.mention;

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
 * Updated by JCasGen Tue Oct 02 12:01:59 CEST 2012
 * @generated */
public class CCPBooleanSlotMention_Type extends CCPPrimitiveSlotMention_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (CCPBooleanSlotMention_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = CCPBooleanSlotMention_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new CCPBooleanSlotMention(addr, CCPBooleanSlotMention_Type.this);
  			   CCPBooleanSlotMention_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new CCPBooleanSlotMention(addr, CCPBooleanSlotMention_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = CCPBooleanSlotMention.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.ucdenver.ccp.nlp.core.uima.mention.CCPBooleanSlotMention");
 
  /** @generated */
  final Feature casFeat_slotValue;
  /** @generated */
  final int     casFeatCode_slotValue;
  /** @generated */ 
  public boolean getSlotValue(int addr) {
        if (featOkTst && casFeat_slotValue == null)
      jcas.throwFeatMissing("slotValue", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPBooleanSlotMention");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_slotValue);
  }
  /** @generated */    
  public void setSlotValue(int addr, boolean v) {
        if (featOkTst && casFeat_slotValue == null)
      jcas.throwFeatMissing("slotValue", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPBooleanSlotMention");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_slotValue, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public CCPBooleanSlotMention_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_slotValue = jcas.getRequiredFeatureDE(casType, "slotValue", "uima.cas.Boolean", featOkTst);
    casFeatCode_slotValue  = (null == casFeat_slotValue) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_slotValue).getCode();

  }
}



    