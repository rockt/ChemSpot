
/* First created by JCasGen Wed Mar 16 10:14:06 CET 2011 */
package org.u_compare.shared.label.penn.function.grammatical;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.u_compare.shared.label.penn.function.FunctionLabel_Type;

/** Abstract function tag type for grammatical functions.
 * Updated by JCasGen Tue Mar 06 16:28:16 CET 2012
 * @generated */
public class GrammaticalFunctionLabel_Type extends FunctionLabel_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (GrammaticalFunctionLabel_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = GrammaticalFunctionLabel_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new GrammaticalFunctionLabel(addr, GrammaticalFunctionLabel_Type.this);
  			   GrammaticalFunctionLabel_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new GrammaticalFunctionLabel(addr, GrammaticalFunctionLabel_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = GrammaticalFunctionLabel.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.u_compare.shared.label.penn.function.grammatical.GrammaticalFunctionLabel");



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public GrammaticalFunctionLabel_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

  }
}



    