
/* First created by JCasGen Wed Mar 16 10:14:08 CET 2011 */
package org.u_compare.shared.semantic;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;

/** 
 * Updated by JCasGen Tue Mar 06 16:28:19 CET 2012
 * @generated */
public class DictionaryNamedEntity_Type extends NamedEntity_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (DictionaryNamedEntity_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = DictionaryNamedEntity_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new DictionaryNamedEntity(addr, DictionaryNamedEntity_Type.this);
  			   DictionaryNamedEntity_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new DictionaryNamedEntity(addr, DictionaryNamedEntity_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = DictionaryNamedEntity.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.u_compare.shared.semantic.DictionaryNamedEntity");
 
  /** @generated */
  final Feature casFeat_surface;
  /** @generated */
  final int     casFeatCode_surface;
  /** @generated */ 
  public String getSurface(int addr) {
        if (featOkTst && casFeat_surface == null)
      jcas.throwFeatMissing("surface", "org.u_compare.shared.semantic.DictionaryNamedEntity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_surface);
  }
  /** @generated */    
  public void setSurface(int addr, String v) {
        if (featOkTst && casFeat_surface == null)
      jcas.throwFeatMissing("surface", "org.u_compare.shared.semantic.DictionaryNamedEntity");
    ll_cas.ll_setStringValue(addr, casFeatCode_surface, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public DictionaryNamedEntity_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_surface = jcas.getRequiredFeatureDE(casType, "surface", "uima.cas.String", featOkTst);
    casFeatCode_surface  = (null == casFeat_surface) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_surface).getCode();

  }
}



    