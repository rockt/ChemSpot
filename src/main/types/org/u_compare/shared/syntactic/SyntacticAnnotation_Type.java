
/* First created by JCasGen Wed Mar 16 10:14:09 CET 2011 */
package org.u_compare.shared.syntactic;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.u_compare.shared.BaseAnnotation_Type;

/** Base abstract type for syntactic annotations. <br></br>
 * Updated by JCasGen Tue Mar 06 16:28:20 CET 2012
 * @generated */
public class SyntacticAnnotation_Type extends BaseAnnotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SyntacticAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SyntacticAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SyntacticAnnotation(addr, SyntacticAnnotation_Type.this);
  			   SyntacticAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SyntacticAnnotation(addr, SyntacticAnnotation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = SyntacticAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.u_compare.shared.syntactic.SyntacticAnnotation");



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public SyntacticAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

  }
}



    