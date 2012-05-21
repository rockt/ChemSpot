
/* First created by JCasGen Wed Mar 16 10:14:04 CET 2011 */
package org.u_compare.shared.document;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

/** Abstract type for document attributes, which appear in the document tags. <br></br>
 * Updated by JCasGen Tue Mar 06 16:28:14 CET 2012
 * @generated */
public class DocumentAttribute_Type extends TOP_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (DocumentAttribute_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = DocumentAttribute_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new DocumentAttribute(addr, DocumentAttribute_Type.this);
  			   DocumentAttribute_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new DocumentAttribute(addr, DocumentAttribute_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = DocumentAttribute.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.u_compare.shared.document.DocumentAttribute");
 
  /** @generated */
  final Feature casFeat_attributeName;
  /** @generated */
  final int     casFeatCode_attributeName;
  /** @generated */ 
  public int getAttributeName(int addr) {
        if (featOkTst && casFeat_attributeName == null)
      jcas.throwFeatMissing("attributeName", "org.u_compare.shared.document.DocumentAttribute");
    return ll_cas.ll_getRefValue(addr, casFeatCode_attributeName);
  }
  /** @generated */    
  public void setAttributeName(int addr, int v) {
        if (featOkTst && casFeat_attributeName == null)
      jcas.throwFeatMissing("attributeName", "org.u_compare.shared.document.DocumentAttribute");
    ll_cas.ll_setRefValue(addr, casFeatCode_attributeName, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public DocumentAttribute_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_attributeName = jcas.getRequiredFeatureDE(casType, "attributeName", "org.u_compare.shared.ExternalReference", featOkTst);
    casFeatCode_attributeName  = (null == casFeat_attributeName) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_attributeName).getCode();

  }
}



    