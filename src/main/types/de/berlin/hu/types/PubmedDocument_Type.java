
/* First created by JCasGen Thu Sep 29 11:47:10 CEST 2011 */
package de.berlin.hu.types;

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
 * Updated by JCasGen Tue Mar 06 16:28:13 CET 2012
 * @generated */
public class PubmedDocument_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (PubmedDocument_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = PubmedDocument_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new PubmedDocument(addr, PubmedDocument_Type.this);
  			   PubmedDocument_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new PubmedDocument(addr, PubmedDocument_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = PubmedDocument.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("de.berlin.hu.types.PubmedDocument");
 
  /** @generated */
  final Feature casFeat_pmid;
  /** @generated */
  final int     casFeatCode_pmid;
  /** @generated */ 
  public String getPmid(int addr) {
        if (featOkTst && casFeat_pmid == null)
      jcas.throwFeatMissing("pmid", "de.berlin.hu.types.PubmedDocument");
    return ll_cas.ll_getStringValue(addr, casFeatCode_pmid);
  }
  /** @generated */    
  public void setPmid(int addr, String v) {
        if (featOkTst && casFeat_pmid == null)
      jcas.throwFeatMissing("pmid", "de.berlin.hu.types.PubmedDocument");
    ll_cas.ll_setStringValue(addr, casFeatCode_pmid, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public PubmedDocument_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_pmid = jcas.getRequiredFeatureDE(casType, "pmid", "uima.cas.String", featOkTst);
    casFeatCode_pmid  = (null == casFeat_pmid) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_pmid).getCode();

  }
}



    