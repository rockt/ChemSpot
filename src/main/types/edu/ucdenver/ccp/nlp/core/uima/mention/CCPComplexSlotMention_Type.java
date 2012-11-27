
/* First created by JCasGen Tue Oct 02 12:01:59 CEST 2012 */
package edu.ucdenver.ccp.nlp.core.uima.mention;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** A slot mention is deemed "complex" when its slot filler is a class mention as opposed to a String (See non-complex slot mention for String fillers). An example of a complex slot mention is the "transported entity" slot for the protein-transport class which would be filled with a protein class mention.
 * Updated by JCasGen Tue Oct 02 12:01:59 CEST 2012
 * @generated */
public class CCPComplexSlotMention_Type extends CCPSlotMention_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (CCPComplexSlotMention_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = CCPComplexSlotMention_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new CCPComplexSlotMention(addr, CCPComplexSlotMention_Type.this);
  			   CCPComplexSlotMention_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new CCPComplexSlotMention(addr, CCPComplexSlotMention_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = CCPComplexSlotMention.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.ucdenver.ccp.nlp.core.uima.mention.CCPComplexSlotMention");
 
  /** @generated */
  final Feature casFeat_classMentions;
  /** @generated */
  final int     casFeatCode_classMentions;
  /** @generated */ 
  public int getClassMentions(int addr) {
        if (featOkTst && casFeat_classMentions == null)
      jcas.throwFeatMissing("classMentions", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPComplexSlotMention");
    return ll_cas.ll_getRefValue(addr, casFeatCode_classMentions);
  }
  /** @generated */    
  public void setClassMentions(int addr, int v) {
        if (featOkTst && casFeat_classMentions == null)
      jcas.throwFeatMissing("classMentions", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPComplexSlotMention");
    ll_cas.ll_setRefValue(addr, casFeatCode_classMentions, v);}
    
   /** @generated */
  public int getClassMentions(int addr, int i) {
        if (featOkTst && casFeat_classMentions == null)
      jcas.throwFeatMissing("classMentions", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPComplexSlotMention");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_classMentions), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_classMentions), i);
	return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_classMentions), i);
  }
   
  /** @generated */ 
  public void setClassMentions(int addr, int i, int v) {
        if (featOkTst && casFeat_classMentions == null)
      jcas.throwFeatMissing("classMentions", "edu.ucdenver.ccp.nlp.core.uima.mention.CCPComplexSlotMention");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_classMentions), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_classMentions), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_classMentions), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public CCPComplexSlotMention_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_classMentions = jcas.getRequiredFeatureDE(casType, "classMentions", "uima.cas.FSArray", featOkTst);
    casFeatCode_classMentions  = (null == casFeat_classMentions) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_classMentions).getCode();

  }
}



    