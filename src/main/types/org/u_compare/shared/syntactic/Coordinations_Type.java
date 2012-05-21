
/* First created by JCasGen Wed Mar 16 10:14:08 CET 2011 */
package org.u_compare.shared.syntactic;

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

/** Contains an array of constituents which are marked as Coordinations. Corresponds to syn="COOD" in the Genia Treebank.
 * Updated by JCasGen Tue Mar 06 16:28:20 CET 2012
 * @generated */
public class Coordinations_Type extends TOP_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Coordinations_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Coordinations_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Coordinations(addr, Coordinations_Type.this);
  			   Coordinations_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Coordinations(addr, Coordinations_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = Coordinations.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.u_compare.shared.syntactic.Coordinations");
 
  /** @generated */
  final Feature casFeat_coordinationMarkedConstituents;
  /** @generated */
  final int     casFeatCode_coordinationMarkedConstituents;
  /** @generated */ 
  public int getCoordinationMarkedConstituents(int addr) {
        if (featOkTst && casFeat_coordinationMarkedConstituents == null)
      jcas.throwFeatMissing("coordinationMarkedConstituents", "org.u_compare.shared.syntactic.Coordinations");
    return ll_cas.ll_getRefValue(addr, casFeatCode_coordinationMarkedConstituents);
  }
  /** @generated */    
  public void setCoordinationMarkedConstituents(int addr, int v) {
        if (featOkTst && casFeat_coordinationMarkedConstituents == null)
      jcas.throwFeatMissing("coordinationMarkedConstituents", "org.u_compare.shared.syntactic.Coordinations");
    ll_cas.ll_setRefValue(addr, casFeatCode_coordinationMarkedConstituents, v);}
    
   /** @generated */
  public int getCoordinationMarkedConstituents(int addr, int i) {
        if (featOkTst && casFeat_coordinationMarkedConstituents == null)
      jcas.throwFeatMissing("coordinationMarkedConstituents", "org.u_compare.shared.syntactic.Coordinations");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_coordinationMarkedConstituents), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_coordinationMarkedConstituents), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_coordinationMarkedConstituents), i);
  }
   
  /** @generated */ 
  public void setCoordinationMarkedConstituents(int addr, int i, int v) {
        if (featOkTst && casFeat_coordinationMarkedConstituents == null)
      jcas.throwFeatMissing("coordinationMarkedConstituents", "org.u_compare.shared.syntactic.Coordinations");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_coordinationMarkedConstituents), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_coordinationMarkedConstituents), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_coordinationMarkedConstituents), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Coordinations_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_coordinationMarkedConstituents = jcas.getRequiredFeatureDE(casType, "coordinationMarkedConstituents", "uima.cas.FSArray", featOkTst);
    casFeatCode_coordinationMarkedConstituents  = (null == casFeat_coordinationMarkedConstituents) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_coordinationMarkedConstituents).getCode();

  }
}



    