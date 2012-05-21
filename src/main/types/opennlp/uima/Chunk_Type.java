
/* First created by JCasGen Wed Mar 16 10:14:24 CET 2011 */
package opennlp.uima;

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
 * Updated by JCasGen Tue Mar 06 16:28:13 CET 2012
 * @generated */
public class Chunk_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Chunk_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Chunk_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Chunk(addr, Chunk_Type.this);
  			   Chunk_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Chunk(addr, Chunk_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = Chunk.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("opennlp.uima.Chunk");
 
  /** @generated */
  final Feature casFeat_TODO_type;
  /** @generated */
  final int     casFeatCode_TODO_type;
  /** @generated */ 
  public String getTODO_type(int addr) {
        if (featOkTst && casFeat_TODO_type == null)
      jcas.throwFeatMissing("TODO_type", "opennlp.uima.Chunk");
    return ll_cas.ll_getStringValue(addr, casFeatCode_TODO_type);
  }
  /** @generated */    
  public void setTODO_type(int addr, String v) {
        if (featOkTst && casFeat_TODO_type == null)
      jcas.throwFeatMissing("TODO_type", "opennlp.uima.Chunk");
    ll_cas.ll_setStringValue(addr, casFeatCode_TODO_type, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Chunk_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_TODO_type = jcas.getRequiredFeatureDE(casType, "TODO_type", "uima.cas.String", featOkTst);
    casFeatCode_TODO_type  = (null == casFeat_TODO_type) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_TODO_type).getCode();

  }
}



    