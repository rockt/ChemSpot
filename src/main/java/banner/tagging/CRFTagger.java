/* 
 Copyright (c) 2007 Arizona State University, Dept. of Computer Science and Dept. of Biomedical Informatics.
 This file is part of the BANNER Named Entity Recognition System, http://banner.sourceforge.net
 This software is provided under the terms of the Common Public License, version 1.0, as published by http://www.opensource.org.  For further information, see the file 'LICENSE.txt' included with this distribution.
 */

package banner.tagging;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import cc.mallet.fst.CRF;
import cc.mallet.fst.CRFTrainerByLabelLikelihood;
import cc.mallet.fst.Transducer.State;
import cc.mallet.types.Alphabet;
import cc.mallet.types.FeatureVector;
import cc.mallet.types.Instance;
import cc.mallet.types.InstanceList;
import cc.mallet.types.Sequence;
import cc.mallet.types.SparseVector;

import dragon.nlp.tool.Lemmatiser;

import banner.types.Sentence;

public class CRFTagger implements Tagger
{

	// TODO Add support for text directions
	// TODO Add support for feature induction

	protected CRF model;
	private FeatureSet featureSet;
	private int order;

	protected CRFTagger(CRF model, FeatureSet featureSet, int order)
	{
		this.model = model;
		this.featureSet = featureSet;
		this.order = order;
		
		model.getInputPipe().getDataAlphabet().stopGrowth();
	}

	/**
	 * Loads a {@link CRFTagger} from the specified file. As the lemmatiser and part-of-speech tagger both require data,
	 * these cannot be written to disk and must be passed in new.
	 * 
	 * @param f
	 *            The file to load the CRFTagger from, as written by the {@link} write() method.
	 * @param lemmatiser
	 *            The {@link Lemmatiser} to use
	 * @param posTagger
	 *            The part-of-speech {@link dragon.nlp.tool.Tagger} to use
	 * @throws IOException
	 * @return A new instance of the CRFTagger contained in the specified file
	 */
	public static CRFTagger load(File f, Lemmatiser lemmatiser, dragon.nlp.tool.Tagger posTagger, Tagger preTagger) throws IOException
	{
		try
		{
			ObjectInputStream ois = new ObjectInputStream(new GZIPInputStream(new FileInputStream(f)));
			CRF model = (CRF) ois.readObject();
			// TODO Test this
			FeatureSet featureSet = (FeatureSet) ois.readObject();
			if (lemmatiser != null)
				featureSet.setLemmatiser(lemmatiser);
			if (posTagger != null)
				featureSet.setPosTagger(posTagger);
			if (preTagger != null)
				featureSet.setPreTagger(preTagger);
			int order = ois.readInt();
			ois.close();
			return new CRFTagger(model, featureSet, order);
		}
		catch (ClassNotFoundException e)
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * Trains and returns a {@link CRFTagger} on the specified {@link Sentence} s. This method may take hours or even
	 * days to complete. When training, you will likely need to increase the amount of memory used by the Java virtual
	 * machine (try adding "-Xms1024m" to the command line).
	 * 
	 * @param sentences
	 *            The {@link Sentence}s to train the tagger on
	 * @param order
	 *            The CRF order to use
	 * @param format
	 *            The {@link TagFormat} to use
	 * @return A trained CRFTagger; ready to tag unseen sentences or be output to disk
	 */
	public static CRFTagger train(Set<Sentence> sentences, int order, TagFormat format, FeatureSet featureSet)
	{
		if (sentences.size() == 0)
			throw new RuntimeException("Number of sentences must be greater than zero");
		InstanceList instances = new InstanceList(featureSet.getPipe());
		for (Sentence sentence : sentences)
		{
			Instance instance = new Instance(sentence, null, sentence.getSentenceId(), sentence);
			instances.addThruPipe(instance);
		}
		CRF model = new CRF(featureSet.getPipe(), null);
		if (order == 1)
			model.addStatesForLabelsConnectedAsIn(instances);
		else if (order == 2)
			model.addStatesForBiLabelsConnectedAsIn(instances);
		else
			throw new IllegalArgumentException("Order must be equal to 1 or 2");
		CRFTrainerByLabelLikelihood crfTrainer = new CRFTrainerByLabelLikelihood(model);
		// TODO Test CRFTrainerByL1LabelLiklihood & CRFTrainerByStochasticGradient; make configurable
		// Train on data subsets
		// TODO Make this configurable
		// crfTrainer.train(instances, 10, new double[] {0.2, 0.4, 0.6, 0.8});
		crfTrainer.train(instances, 10, new double[] { 0.2, 0.5, 0.8 });
		// Train to convergence
		// crfTrainer.train(instances, 70);
		crfTrainer.train(instances);
		return new CRFTagger(model, featureSet, order);
	}

	/**
	 * Serializes and writes this CRFTagger to the specified file
	 * 
	 * @param f
	 *            The file to write this CRFTagger to
	 */
	public void write(File f)
	{
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(f)));
			oos.writeObject(model);
			oos.writeObject(featureSet);
			oos.writeInt(order);
			oos.close();
		}
		catch (IOException e)
		{
			System.err.println("Exception writing file " + f + ": " + e);
		}
	}

	@SuppressWarnings("unchecked")
	public void tag(Sentence sentence)
	{
		Instance instance = getInstance(sentence);
		Sequence<Object> tags = model.transduce((Sequence) instance.getData());
		sentence.addMentions(getTagList(tags), 1.0);
	}

	protected Instance getInstance(Sentence sentence)
	{
		InstanceList iList = new InstanceList(model.getInputPipe());
		iList.addThruPipe(new Instance(sentence, null, sentence.getSentenceId(), sentence));
		return iList.get(0);
	}

	protected static List<String> getTagList(Sequence<Object> tags)
	{
		int size = tags.size();
		List<String> tags2 = new ArrayList<String>();
		for (int i = 0; i < size; i++)
			tags2.add(tags.get(i).toString());
		return tags2;
	}

	/**
	 * @return The CRF order used by this tagger. Order 1 means that the last state is used and order 2 means that the
	 *         last 2 states are used.
	 */
	public int getOrder()
	{
		return order;
	}

	public Set<String> getFeatureNames()
	{
		Set<String> featureNames = new HashSet<String>();
		Alphabet inputAlphabet = model.getInputAlphabet();
		int size = inputAlphabet.size();
		for (int i = 0; i < size; i++)
		{
			String featureName = inputAlphabet.lookupObject(i).toString();
			featureNames.add(featureName);
		}
		return Collections.unmodifiableSet(featureNames);
	}

	@SuppressWarnings("unchecked")
	public List<List<String>> getFeatureRepresentation(Sentence sentence)
	{
		// TODO Add the ability to suppress some features
		Instance instance = getInstance(sentence);
		Sequence<FeatureVector> sentenceSequence = (Sequence<FeatureVector>) instance.getData();
		Alphabet alphabet = model.getInputAlphabet();
		List<List<String>> sentenceFeatureRepresentation = new ArrayList<List<String>>();
		for (int i = 0; i < sentenceSequence.size(); i++)
		{
			List<String> tokenFeatureRepresentation = new ArrayList<String>();
			FeatureVector tokenFeatures = sentenceSequence.get(i);
			int[] featureIndicies = tokenFeatures.getIndices();
			double[] featureValues = tokenFeatures.getValues();
			for (int j = 0; j < featureIndicies.length; j++)
			{
				StringBuilder tokenFeature = new StringBuilder();
				tokenFeature.append(alphabet.lookupObject(featureIndicies[j]).toString());
				if (featureValues != null)
				{
					tokenFeature.append("=");
					tokenFeature.append(featureValues[j]);
				}
				tokenFeatureRepresentation.add(tokenFeature.toString());
			}
			Collections.sort(tokenFeatureRepresentation);
			sentenceFeatureRepresentation.add(tokenFeatureRepresentation);
		}
		return sentenceFeatureRepresentation;
	}

	public void describe(String fileName) throws IOException
	{
		System.out.println("Number of default weights = " + model.getDefaultWeights().length);
		System.out.println("Number of states = " + model.numStates());
		for (int i = 0; i < model.numStates(); i++)
		{
			State state = model.getState(i);
			System.out.println("State " + i + " is " + state.getName());
		}
		SparseVector[] weights = model.getWeights();

		System.out.println("Size of weights vector = " + weights.length);
		for (int i = 0; i < weights.length; i++)
		{
			System.out.print("Number of non-zero values for weight vector " + i);
			System.out.println(" (" + model.getWeightsName(i) + ") is " + weights[i].numLocations());
		}
		int size = model.getInputAlphabet().size();
		System.out.println("Size of input alphabet: " + size);
		PrintWriter output = new PrintWriter(fileName);
		for (int i = 0; i < size; i++)
		{
			String featureName = model.getInputAlphabet().lookupObject(i).toString();
			// System.out.println("featureName=" + featureName);
			int equalsIndex = featureName.indexOf("=");
			// System.out.println("equalsIndex=" + equalsIndex);
			int atIndex = featureName.indexOf("@");
			// System.out.println("atIndex=" + atIndex);
			int featureTypeEnd = featureName.length();
			if (equalsIndex != -1 && equalsIndex < featureTypeEnd)
				featureTypeEnd = equalsIndex;
			if (atIndex != -1 && atIndex < featureTypeEnd)
				featureTypeEnd = atIndex;
			String featureType = featureName.substring(0, featureTypeEnd);
			// System.out.println("featureType=" + featureType);
			String featureOffset = "0";
			int featureDataEnd = featureName.length();
			if (atIndex != -1)
			{
				featureDataEnd = atIndex;
				featureOffset = featureName.substring(atIndex + 1, featureName.length());
			}
			// System.out.println("featureOffset=" + featureOffset);
			String featureData = "";
			if (featureDataEnd > featureTypeEnd)
				featureData = featureName.substring(featureTypeEnd + 1, featureDataEnd);
			// TODO Fix so easily importable
			featureData = featureData.replaceAll("^\"", "\\\"");
			// System.out.println("featureData=" + featureData);

			// Print information
			// if (featureType.equals("W") && featureData.matches("[a-z]+"))
			// {
			// Weight will range from Double.NEGATIVE_INFINITY to Double.POSITIVE_INFINITY
			// Higher values make the transition more probable
			// See http://www.pubmedcentral.nih.gov/articlerender.fcgi?artid=2386138
			double maxWeight = Double.NEGATIVE_INFINITY;
			for (int j = 0; j < weights.length; j++)
				if (!model.getWeightsName(j).endsWith("O:O"))
					if (maxWeight < weights[j].value(i))
						maxWeight = weights[j].value(i);
			// if (maxWeight > 0.0)
			// {
			output.print(i + "\t");
			output.print(featureName + "\t");
			output.print(featureType + "\t");
			output.print(featureOffset + "\t");
			output.print(featureData + "\t");
			output.print(maxWeight + "\t");
			output.println();
			// }
			// }
		}
		output.close();
	}

	public Map<String, Double> getMaxWeights()
	{
		Map<String, Double> weightMap = new HashMap<String, Double>();
		SparseVector[] weights = model.getWeights();
		Alphabet inputAlphabet = model.getInputAlphabet();
		int size = inputAlphabet.size();
		for (int i = 0; i < size; i++)
		{
			double max = Double.MIN_VALUE;
			for (int j = 0; j < weights.length; j++)
			{
				double weight = weights[j].value(i);
				if (max < weight)
					max = weight;
			}
			String featureName = inputAlphabet.lookupObject(i).toString();
			weightMap.put(featureName, max);
		}
		return weightMap;
	}

	public Map<String, Double> getMinWeights()
	{
		Map<String, Double> weightMap = new HashMap<String, Double>();
		SparseVector[] weights = model.getWeights();
		Alphabet inputAlphabet = model.getInputAlphabet();
		int size = inputAlphabet.size();
		for (int i = 0; i < size; i++)
		{
			double min = Double.MAX_VALUE;
			for (int j = 0; j < weights.length; j++)
			{
				double weight = weights[j].value(i);
				if (min > weight)
					min = weight;
			}
			String featureName = inputAlphabet.lookupObject(i).toString();
			weightMap.put(featureName, min);
		}
		return weightMap;
	}
}
