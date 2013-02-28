package de.berlin.hu.uima.ae.tagger.banner;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import cc.mallet.fst.CRF;
import dragon.nlp.tool.Lemmatiser;
import banner.tagging.CRFTagger;
import banner.tagging.FeatureSet;
import banner.tagging.Tagger;

public class CRFWrapper extends CRFTagger {

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
	public static CRFTagger load(URL f, Lemmatiser lemmatiser, dragon.nlp.tool.Tagger posTagger, Tagger preTagger) throws IOException
	{
		try
		{
			ObjectInputStream ois = new ObjectInputStream(new GZIPInputStream (f.openStream()));
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
			return new CRFWrapper(model, featureSet, order);
		}
		catch (ClassNotFoundException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	protected CRFWrapper(CRF model, FeatureSet featureSet, int order) {
		super(model, featureSet, order);
		model.getInputPipe().getDataAlphabet().stopGrowth();
	}

}
