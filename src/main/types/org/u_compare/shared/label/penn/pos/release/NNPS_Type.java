
/* First created by JCasGen Wed Mar 16 10:14:07 CET 2011 */
package org.u_compare.shared.label.penn.pos.release;

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.u_compare.shared.label.penn.pos.general.ProperNoun_Type;

/** Proper Noun, plural
 * Updated by JCasGen Tue Mar 06 16:28:17 CET 2012
 * @generated */
public class NNPS_Type extends ProperNoun_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (NNPS_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = NNPS_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new NNPS(addr, NNPS_Type.this);
  			   NNPS_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new NNPS(addr, NNPS_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = NNPS.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.u_compare.shared.label.penn.pos.release.NNPS");



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public NNPS_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

  }
}



    