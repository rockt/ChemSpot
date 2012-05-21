
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
public class Sentence_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Sentence_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Sentence_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Sentence(addr, Sentence_Type.this);
  			   Sentence_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Sentence(addr, Sentence_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = Sentence.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("sprint.uima.types.Sentence");
 
  /** @generated */
  final Feature casFeat_ID;
  /** @generated */
  final int     casFeatCode_ID;
  /** @generated */ 
  public String getID(int addr) {
        if (featOkTst && casFeat_ID == null)
      jcas.throwFeatMissing("ID", "sprint.uima.types.Sentence");
    return ll_cas.ll_getStringValue(addr, casFeatCode_ID);
  }
  /** @generated */    
  public void setID(int addr, String v) {
        if (featOkTst && casFeat_ID == null)
      jcas.throwFeatMissing("ID", "sprint.uima.types.Sentence");
    ll_cas.ll_setStringValue(addr, casFeatCode_ID, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Pairs;
  /** @generated */
  final int     casFeatCode_Pairs;
  /** @generated */ 
  public int getPairs(int addr) {
        if (featOkTst && casFeat_Pairs == null)
      jcas.throwFeatMissing("Pairs", "sprint.uima.types.Sentence");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Pairs);
  }
  /** @generated */    
  public void setPairs(int addr, int v) {
        if (featOkTst && casFeat_Pairs == null)
      jcas.throwFeatMissing("Pairs", "sprint.uima.types.Sentence");
    ll_cas.ll_setRefValue(addr, casFeatCode_Pairs, v);}
    
   /** @generated */
  public int getPairs(int addr, int i) {
        if (featOkTst && casFeat_Pairs == null)
      jcas.throwFeatMissing("Pairs", "sprint.uima.types.Sentence");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Pairs), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Pairs), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Pairs), i);
  }
   
  /** @generated */ 
  public void setPairs(int addr, int i, int v) {
        if (featOkTst && casFeat_Pairs == null)
      jcas.throwFeatMissing("Pairs", "sprint.uima.types.Sentence");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Pairs), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Pairs), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Pairs), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Sentence_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_ID = jcas.getRequiredFeatureDE(casType, "ID", "uima.cas.String", featOkTst);
    casFeatCode_ID  = (null == casFeat_ID) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_ID).getCode();

 
    casFeat_Pairs = jcas.getRequiredFeatureDE(casType, "Pairs", "uima.cas.FSArray", featOkTst);
    casFeatCode_Pairs  = (null == casFeat_Pairs) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Pairs).getCode();

  }
}



    