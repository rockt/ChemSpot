
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
 * Updated by JCasGen Thu Jul 14 14:25:40 CEST 2011
 * @generated */
public class Pair_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Pair_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Pair_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Pair(addr, Pair_Type.this);
  			   Pair_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Pair(addr, Pair_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = Pair.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("sprint.uima.types.Pair");
 
  /** @generated */
  final Feature casFeat_ID;
  /** @generated */
  final int     casFeatCode_ID;
  /** @generated */ 
  public String getID(int addr) {
        if (featOkTst && casFeat_ID == null)
      jcas.throwFeatMissing("ID", "sprint.uima.types.Pair");
    return ll_cas.ll_getStringValue(addr, casFeatCode_ID);
  }
  /** @generated */    
  public void setID(int addr, String v) {
        if (featOkTst && casFeat_ID == null)
      jcas.throwFeatMissing("ID", "sprint.uima.types.Pair");
    ll_cas.ll_setStringValue(addr, casFeatCode_ID, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Interaction;
  /** @generated */
  final int     casFeatCode_Interaction;
  /** @generated */ 
  public boolean getInteraction(int addr) {
        if (featOkTst && casFeat_Interaction == null)
      jcas.throwFeatMissing("Interaction", "sprint.uima.types.Pair");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_Interaction);
  }
  /** @generated */    
  public void setInteraction(int addr, boolean v) {
        if (featOkTst && casFeat_Interaction == null)
      jcas.throwFeatMissing("Interaction", "sprint.uima.types.Pair");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_Interaction, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Entity1;
  /** @generated */
  final int     casFeatCode_Entity1;
  /** @generated */ 
  public int getEntity1(int addr) {
        if (featOkTst && casFeat_Entity1 == null)
      jcas.throwFeatMissing("Entity1", "sprint.uima.types.Pair");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Entity1);
  }
  /** @generated */    
  public void setEntity1(int addr, int v) {
        if (featOkTst && casFeat_Entity1 == null)
      jcas.throwFeatMissing("Entity1", "sprint.uima.types.Pair");
    ll_cas.ll_setRefValue(addr, casFeatCode_Entity1, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Entity2;
  /** @generated */
  final int     casFeatCode_Entity2;
  /** @generated */ 
  public int getEntity2(int addr) {
        if (featOkTst && casFeat_Entity2 == null)
      jcas.throwFeatMissing("Entity2", "sprint.uima.types.Pair");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Entity2);
  }
  /** @generated */    
  public void setEntity2(int addr, int v) {
        if (featOkTst && casFeat_Entity2 == null)
      jcas.throwFeatMissing("Entity2", "sprint.uima.types.Pair");
    ll_cas.ll_setRefValue(addr, casFeatCode_Entity2, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Pair_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_ID = jcas.getRequiredFeatureDE(casType, "ID", "uima.cas.String", featOkTst);
    casFeatCode_ID  = (null == casFeat_ID) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_ID).getCode();

 
    casFeat_Interaction = jcas.getRequiredFeatureDE(casType, "Interaction", "uima.cas.Boolean", featOkTst);
    casFeatCode_Interaction  = (null == casFeat_Interaction) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Interaction).getCode();

 
    casFeat_Entity1 = jcas.getRequiredFeatureDE(casType, "Entity1", "sprint.uima.types.Entity", featOkTst);
    casFeatCode_Entity1  = (null == casFeat_Entity1) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Entity1).getCode();

 
    casFeat_Entity2 = jcas.getRequiredFeatureDE(casType, "Entity2", "sprint.uima.types.Entity", featOkTst);
    casFeatCode_Entity2  = (null == casFeat_Entity2) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Entity2).getCode();

  }
}



    