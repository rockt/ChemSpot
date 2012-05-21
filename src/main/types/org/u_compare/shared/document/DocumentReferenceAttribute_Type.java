
/* First created by JCasGen Wed Mar 16 10:14:04 CET 2011 */
package org.u_compare.shared.document;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** Assumed to be used to correspond to an attribute name-id pair (IDREF in case of DTD). <br></br>
 * Updated by JCasGen Tue Mar 06 16:28:14 CET 2012
 * @generated */
public class DocumentReferenceAttribute_Type extends DocumentAttribute_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (DocumentReferenceAttribute_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = DocumentReferenceAttribute_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new DocumentReferenceAttribute(addr, DocumentReferenceAttribute_Type.this);
  			   DocumentReferenceAttribute_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new DocumentReferenceAttribute(addr, DocumentReferenceAttribute_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = DocumentReferenceAttribute.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.u_compare.shared.document.DocumentReferenceAttribute");
 
  /** @generated */
  final Feature casFeat_referredAnnotation;
  /** @generated */
  final int     casFeatCode_referredAnnotation;
  /** @generated */ 
  public int getReferredAnnotation(int addr) {
        if (featOkTst && casFeat_referredAnnotation == null)
      jcas.throwFeatMissing("referredAnnotation", "org.u_compare.shared.document.DocumentReferenceAttribute");
    return ll_cas.ll_getRefValue(addr, casFeatCode_referredAnnotation);
  }
  /** @generated */    
  public void setReferredAnnotation(int addr, int v) {
        if (featOkTst && casFeat_referredAnnotation == null)
      jcas.throwFeatMissing("referredAnnotation", "org.u_compare.shared.document.DocumentReferenceAttribute");
    ll_cas.ll_setRefValue(addr, casFeatCode_referredAnnotation, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public DocumentReferenceAttribute_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_referredAnnotation = jcas.getRequiredFeatureDE(casType, "referredAnnotation", "org.u_compare.shared.ReferenceAnnotation", featOkTst);
    casFeatCode_referredAnnotation  = (null == casFeat_referredAnnotation) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_referredAnnotation).getCode();

  }
}



    