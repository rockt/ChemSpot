
/* First created by JCasGen Thu Jul 14 13:32:47 CEST 2011 */
package sprint.uima.types;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Thu Jul 14 14:25:40 CEST 2011
 * @generated */
public class Entity_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Entity_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Entity_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Entity(addr, Entity_Type.this);
  			   Entity_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Entity(addr, Entity_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = Entity.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("sprint.uima.types.Entity");
 
  /** @generated */
  final Feature casFeat_ID;
  /** @generated */
  final int     casFeatCode_ID;
  /** @generated */ 
  public String getID(int addr) {
        if (featOkTst && casFeat_ID == null)
      jcas.throwFeatMissing("ID", "sprint.uima.types.Entity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_ID);
  }
  /** @generated */    
  public void setID(int addr, String v) {
        if (featOkTst && casFeat_ID == null)
      jcas.throwFeatMissing("ID", "sprint.uima.types.Entity");
    ll_cas.ll_setStringValue(addr, casFeatCode_ID, v);}
    
  
 
  /** @generated */
  final Feature casFeat_EntityType;
  /** @generated */
  final int     casFeatCode_EntityType;
  /** @generated */ 
  public String getEntityType(int addr) {
        if (featOkTst && casFeat_EntityType == null)
      jcas.throwFeatMissing("EntityType", "sprint.uima.types.Entity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_EntityType);
  }
  /** @generated */    
  public void setEntityType(int addr, String v) {
        if (featOkTst && casFeat_EntityType == null)
      jcas.throwFeatMissing("EntityType", "sprint.uima.types.Entity");
    ll_cas.ll_setStringValue(addr, casFeatCode_EntityType, v);}
    
  
 
  /** @generated */
  final Feature casFeat_CharOffset;
  /** @generated */
  final int     casFeatCode_CharOffset;
  /** @generated */ 
  public String getCharOffset(int addr) {
        if (featOkTst && casFeat_CharOffset == null)
      jcas.throwFeatMissing("CharOffset", "sprint.uima.types.Entity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_CharOffset);
  }
  /** @generated */    
  public void setCharOffset(int addr, String v) {
        if (featOkTst && casFeat_CharOffset == null)
      jcas.throwFeatMissing("CharOffset", "sprint.uima.types.Entity");
    ll_cas.ll_setStringValue(addr, casFeatCode_CharOffset, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Entity_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_ID = jcas.getRequiredFeatureDE(casType, "ID", "uima.cas.String", featOkTst);
    casFeatCode_ID  = (null == casFeat_ID) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_ID).getCode();

 
    casFeat_EntityType = jcas.getRequiredFeatureDE(casType, "EntityType", "uima.cas.String", featOkTst);
    casFeatCode_EntityType  = (null == casFeat_EntityType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_EntityType).getCode();

 
    casFeat_CharOffset = jcas.getRequiredFeatureDE(casType, "CharOffset", "uima.cas.String", featOkTst);
    casFeatCode_CharOffset  = (null == casFeat_CharOffset) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_CharOffset).getCode();

  }
}



    