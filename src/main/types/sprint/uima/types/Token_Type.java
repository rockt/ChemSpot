
/* First created by JCasGen Thu Jul 14 13:32:47 CEST 2011 */
package sprint.uima.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Thu Jul 14 14:25:41 CEST 2011
 * @generated */
public class Token_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Token_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Token_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Token(addr, Token_Type.this);
  			   Token_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Token(addr, Token_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = Token.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("sprint.uima.types.Token");
 
  /** @generated */
  final Feature casFeat_ID;
  /** @generated */
  final int     casFeatCode_ID;
  /** @generated */ 
  public String getID(int addr) {
        if (featOkTst && casFeat_ID == null)
      jcas.throwFeatMissing("ID", "sprint.uima.types.Token");
    return ll_cas.ll_getStringValue(addr, casFeatCode_ID);
  }
  /** @generated */    
  public void setID(int addr, String v) {
        if (featOkTst && casFeat_ID == null)
      jcas.throwFeatMissing("ID", "sprint.uima.types.Token");
    ll_cas.ll_setStringValue(addr, casFeatCode_ID, v);}
    
  
 
  /** @generated */
  final Feature casFeat_POS;
  /** @generated */
  final int     casFeatCode_POS;
  /** @generated */ 
  public String getPOS(int addr) {
        if (featOkTst && casFeat_POS == null)
      jcas.throwFeatMissing("POS", "sprint.uima.types.Token");
    return ll_cas.ll_getStringValue(addr, casFeatCode_POS);
  }
  /** @generated */    
  public void setPOS(int addr, String v) {
        if (featOkTst && casFeat_POS == null)
      jcas.throwFeatMissing("POS", "sprint.uima.types.Token");
    ll_cas.ll_setStringValue(addr, casFeatCode_POS, v);}
    
  
 
  /** @generated */
  final Feature casFeat_IsEntity;
  /** @generated */
  final int     casFeatCode_IsEntity;
  /** @generated */ 
  public boolean getIsEntity(int addr) {
        if (featOkTst && casFeat_IsEntity == null)
      jcas.throwFeatMissing("IsEntity", "sprint.uima.types.Token");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_IsEntity);
  }
  /** @generated */    
  public void setIsEntity(int addr, boolean v) {
        if (featOkTst && casFeat_IsEntity == null)
      jcas.throwFeatMissing("IsEntity", "sprint.uima.types.Token");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_IsEntity, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Token_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_ID = jcas.getRequiredFeatureDE(casType, "ID", "uima.cas.String", featOkTst);
    casFeatCode_ID  = (null == casFeat_ID) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_ID).getCode();

 
    casFeat_POS = jcas.getRequiredFeatureDE(casType, "POS", "uima.cas.String", featOkTst);
    casFeatCode_POS  = (null == casFeat_POS) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_POS).getCode();

 
    casFeat_IsEntity = jcas.getRequiredFeatureDE(casType, "IsEntity", "uima.cas.Boolean", featOkTst);
    casFeatCode_IsEntity  = (null == casFeat_IsEntity) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_IsEntity).getCode();

  }
}



    