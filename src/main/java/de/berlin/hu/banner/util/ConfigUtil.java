package de.berlin.hu.banner.util;

import banner.eval.BANNER;
import banner.eval.dataset.Dataset;
import banner.postprocessing.LocalAbbreviationPostProcessor;
import banner.postprocessing.ParenthesisPostProcessor;
import banner.postprocessing.PostProcessor;
import banner.postprocessing.SequentialPostProcessor;
import banner.tagging.TagFormat;
import banner.tagging.dictionary.DictionaryTagger;
import banner.tokenization.Tokenizer;
import banner.types.Mention.MentionType;
import banner.types.Sentence.OverlapOption;
import dragon.nlp.tool.HeppleTagger;
import dragon.nlp.tool.MedPostTagger;
import dragon.nlp.tool.lemmatiser.EngLemmatiser;
import org.apache.commons.configuration.HierarchicalConfiguration;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class ConfigUtil {
	public static Dataset getDataset(HierarchicalConfiguration config)
	{
		Tokenizer tokenizer = getTokenizer(config);
		HierarchicalConfiguration localConfig = config.configurationAt(BANNER.class.getPackage().getName());
		String datasetName = localConfig.getString("datasetName");
		Dataset dataset = null;
		try
		{
			dataset = (Dataset)Class.forName(datasetName).newInstance();
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
		dataset.setTokenizer(tokenizer);
		dataset.load(config);
		return dataset;
	}

	public static TagFormat getTagFormat(HierarchicalConfiguration config)
	{
		HierarchicalConfiguration localConfig = config.configurationAt(BANNER.class.getPackage().getName());
		return TagFormat.valueOf(localConfig.getString("tagFormat"));
	}

	public static Tokenizer getTokenizer(HierarchicalConfiguration config)
	{
		HierarchicalConfiguration localConfig = config.configurationAt(BANNER.class.getPackage().getName());
		try
		{
			String tokenizerName = localConfig.getString("tokenizer");
			Tokenizer tokenizer = (Tokenizer)Class.forName(tokenizerName).newInstance();
			return tokenizer;
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public static DictionaryTagger getDictionary(HierarchicalConfiguration config)
	{
		Tokenizer tokenizer = getTokenizer(config);
		HierarchicalConfiguration localConfig = config.configurationAt(BANNER.class.getPackage().getName());
		String dictionaryName = localConfig.getString("dictionaryTagger");
		if (dictionaryName == null)
			return null;
		DictionaryTagger dictionary = null;
		try
		{
			dictionary = (DictionaryTagger)Class.forName(dictionaryName).newInstance();
			dictionary.configure(config, tokenizer);
			dictionary.load(config);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
		return dictionary;
	}

	public static PostProcessor getPostProcessor(HierarchicalConfiguration config)
	{
		// Guaranteed not to be null
		HierarchicalConfiguration localConfig = config.configurationAt(BANNER.class.getPackage().getName());
		SequentialPostProcessor postProcessor = new SequentialPostProcessor();
		if (localConfig.containsKey("useParenthesisPostProcessing"))
			if (localConfig.getBoolean("useParenthesisPostProcessing"))
				postProcessor.addPostProcessor(new ParenthesisPostProcessor());
		if (localConfig.containsKey("useLocalAbbreviationPostProcessing"))
			if (localConfig.getBoolean("useLocalAbbreviationPostProcessing"))
				postProcessor.addPostProcessor(new LocalAbbreviationPostProcessor());
		return postProcessor;
	}

	public static int getCRFOrder(HierarchicalConfiguration config)
	{
		HierarchicalConfiguration localConfig = config.configurationAt(BANNER.class.getPackage().getName());
		return localConfig.getInt("crfOrder");
	}

	public static dragon.nlp.tool.Tagger getPosTagger(HierarchicalConfiguration config)
	{
		HierarchicalConfiguration localConfig = config.configurationAt(BANNER.class.getPackage().getName());

		String posTagger = localConfig.getString("posTagger");
		if (posTagger == null)
			return null;
		String posTaggerDataDirectory = localConfig.getString("posTaggerDataDirectory");
		if (posTaggerDataDirectory == null)
			throw new IllegalArgumentException("Must specify data directory for POS tagger");

		if (posTagger.equals(HeppleTagger.class.getName()))
			return new HeppleTagger(posTaggerDataDirectory);
		else if (posTagger.equals(MedPostTagger.class.getName()))
			return new MedPostTagger(posTaggerDataDirectory);
		else
			throw new IllegalArgumentException("Unknown POS tagger type: " + posTagger);
	}

	public static EngLemmatiser getLemmatiser(HierarchicalConfiguration config)
	{
		HierarchicalConfiguration localConfig = config.configurationAt(BANNER.class.getPackage().getName());
		String lemmatiserDataDirectory = localConfig.getString("lemmatiserDataDirectory");
		if (lemmatiserDataDirectory == null)
			return null;
		return new EngLemmatiser(lemmatiserDataDirectory, false, true);
	}

	public static Set<MentionType> getMentionTypes(HierarchicalConfiguration config)
	{
		HierarchicalConfiguration localConfig = config.configurationAt(BANNER.class.getPackage().getName());
		String mentionTypesStr = localConfig.getString("mentionTypes");
		if (mentionTypesStr == null)
			throw new RuntimeException("Configuration must contain parameter \"mentionTypes\"");
		Set<MentionType> mentionTypes = new HashSet<MentionType>();
		for (String mentionTypeName : mentionTypesStr.split("\\s+"))
			mentionTypes.add(MentionType.valueOf(mentionTypeName));
		return EnumSet.copyOf(mentionTypes);
	}

	public static OverlapOption getSameTypeOverlapOption(HierarchicalConfiguration config)
	{
		HierarchicalConfiguration localConfig = config.configurationAt(BANNER.class.getPackage().getName());
		String sameTypeOverlapOption = localConfig.getString("sameTypeOverlapOption");
		if (sameTypeOverlapOption == null)
			throw new RuntimeException("Configuration must contain parameter \"sameTypeOverlapOption\"");
		return OverlapOption.valueOf(sameTypeOverlapOption);
	}

	public static OverlapOption getDifferentTypeOverlapOption(HierarchicalConfiguration config)
	{
		HierarchicalConfiguration localConfig = config.configurationAt(BANNER.class.getPackage().getName());
		String differentTypeOverlapOption = localConfig.getString("differentTypeOverlapOption");
		if (differentTypeOverlapOption == null)
			throw new RuntimeException("Configuration must contain parameter \"differentTypeOverlapOption\"");
		return OverlapOption.valueOf(differentTypeOverlapOption);
	}
}
