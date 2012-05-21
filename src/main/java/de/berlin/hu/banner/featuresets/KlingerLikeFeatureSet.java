/**
 * 
 */
package de.berlin.hu.banner.featuresets;

import java.util.ArrayList;
import java.util.Set;
import java.util.regex.Pattern;

import banner.tagging.FeatureSet;
import banner.tagging.TagFormat;
import banner.tagging.pipe.LChar;
import banner.tagging.pipe.LemmaPOS;
import banner.tagging.pipe.LowerCaseTokenText;
import banner.tagging.pipe.Pretagger;
import banner.tagging.pipe.RChar;
import banner.tagging.pipe.Sentence2TokenSequence;
import banner.types.Mention.MentionType;
import banner.types.Sentence.OverlapOption;
import cc.mallet.pipe.Noop;
import cc.mallet.pipe.Pipe;
import cc.mallet.pipe.SerialPipes;
import cc.mallet.pipe.TokenSequence2FeatureVectorSequence;
import cc.mallet.pipe.tsf.OffsetConjunctions;
import cc.mallet.pipe.tsf.RegexMatches;
import cc.mallet.pipe.tsf.TokenTextCharPrefix;
import cc.mallet.pipe.tsf.TokenTextCharSuffix;
import dragon.nlp.tool.Lemmatiser;
import dragon.nlp.tool.Tagger;

/**
 * @author trocktae
 *
 */
public class KlingerLikeFeatureSet extends FeatureSet {

	public KlingerLikeFeatureSet(TagFormat format, Lemmatiser lemmatiser,
			Tagger posTagger, banner.tagging.Tagger preTagger,
			Set<MentionType> mentionTypes, OverlapOption sameType,
			OverlapOption differentType) {
		super(format, lemmatiser, posTagger, preTagger, mentionTypes, sameType, differentType);
		this.pipe = createPipe(format, lemmatiser, posTagger, preTagger, mentionTypes, sameType, differentType);
	}

	private static final long serialVersionUID = 3850553083981024255L;
	private SerialPipes pipe;
	
	/**
	 * hardcoded switch to turn POS and LEMMA feature on/off
	 */
	private static final boolean USE_POS_AND_LEMMA = false;
	
	@Override
	public void setLemmatiser(Lemmatiser lemmatiser)
	{
		if (USE_POS_AND_LEMMA) {
			((LemmaPOS) pipe.getPipe(1)).setLemmatiser(lemmatiser);
		}
	}

	@Override
	public void setPosTagger(dragon.nlp.tool.Tagger posTagger)
	{
		if (USE_POS_AND_LEMMA) {
			((LemmaPOS) pipe.getPipe(1)).setPosTagger(posTagger);
		}
	}

	@Override
	public void setPreTagger(banner.tagging.Tagger preTagger)
	{
		if (USE_POS_AND_LEMMA) {
			((Pretagger) pipe.getPipe(2)).setPreTagger(preTagger);
		}
	}
	
	@Override
	public Pipe getPipe() {
		return this.pipe;
	}
	
	private SerialPipes createPipe(TagFormat format, Lemmatiser lemmatiser, dragon.nlp.tool.Tagger posTagger, banner.tagging.Tagger preTagger, Set<MentionType> mentionTypes,
			OverlapOption sameType, OverlapOption differentType) {
		ArrayList<Pipe> pipes = new ArrayList<Pipe>();
		pipes.add(new Sentence2TokenSequence(format, mentionTypes, sameType, differentType));
//		pipes.add((lemmatiser == null && posTagger == null) ? new Noop() : new LemmaPOS(lemmatiser, posTagger));
//		pipes.add((preTagger == null) ? new Noop() : new Pretagger("PRETAG=", preTagger));
		
		//Whitespace
		pipes.add(new LChar("LCHAR="));
		pipes.add(new RChar("RCHAR="));
		
		//Bag of words TODO: really bag of words?
		pipes.add(new LowerCaseTokenText("W="));
		
		//All Caps
		pipes.add(new RegexMatches("ALLCAPS", Pattern.compile("[A-Z]+")));
		
		//Real Number
		pipes.add(new RegexMatches("REALNUMBER", Pattern.compile("[-0-9]+[.,]+[0-9.,]+")));

		//Is Dash
		pipes.add(new RegexMatches("ISDASH", Pattern.compile("[-–—−]")));
		
		//Is Quote
		pipes.add(new RegexMatches("ISQUOTE", Pattern.compile("[„“””‘’\"']")));
		
		//Is Slash
		pipes.add(new RegexMatches("ISSLASH", Pattern.compile("[/\\\\]")));
		
		//Prefixes and Suffixes
		pipes.add(new TokenTextCharPrefix("2PREFIX=", 2));
		pipes.add(new TokenTextCharSuffix("2SUFFIX=", 2));

		//Offset Conjunction of 2
		pipes.add(new OffsetConjunctions(new int[][] { { -2 }, { -1 }, { 1 }, { 2 } }));
		
		pipes.add(new TokenSequence2FeatureVectorSequence(true, true));
		return new SerialPipes(pipes);
	}

}
