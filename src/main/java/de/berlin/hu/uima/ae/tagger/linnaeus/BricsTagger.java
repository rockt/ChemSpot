/*
 * Copyright (c) 2012. Humboldt-Universität zu Berlin, Dept. of Computer Science and Dept.
 * of Wissensmanagement in der Bioinformatik
 * -------------------------------
 *
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS COMMON PUBLIC
 * LICENSE ("AGREEMENT"). ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM
 * CONSTITUTES RECIPIENT'S ACCEPTANCE OF THIS AGREEMENT.
 *
 * http://www.opensource.org/licenses/cpl1.0
 */

package de.berlin.hu.uima.ae.tagger.linnaeus;

import de.berlin.hu.chemspot.Mention;
import de.berlin.hu.util.Constants;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.u_compare.shared.semantic.Chemical;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * User: Tim Rocktaeschel
 * Date: 7/2/12
 * Time: 2:11 PM
 */
public class BricsTagger extends JCasAnnotator_ImplBase {
	private static final String PATH_TO_DICTIONARY = "DrugBankMatcherDictionaryAutomat";
    //list of invalid suffixes taken from Hettne et al. (2009)
	private Set<String> suffixes; //FIXME: implement another AE for that
	private BricsMatcher matcher;

	@Override
	public void initialize(UimaContext aContext)
			throws ResourceInitializationException {
		super.initialize(aContext);
		suffixes = new HashSet<String>();

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("resources/suffixes.txt")));
			String line = reader.readLine();
			while (line != null) {
				suffixes.add(line);
				line = reader.readLine();
			}
			matcher = new BricsMatcher(aContext.getConfigParameterValue(PATH_TO_DICTIONARY).toString());
		} catch (FileNotFoundException e) {
			throw new ResourceInitializationException(e);
		} catch (IOException e) {
			throw new ResourceInitializationException(e);
		} catch (ClassNotFoundException e) {
            throw new ResourceInitializationException(e);
        }
    }

    @Override
    public void process(JCas jCas) throws AnalysisEngineProcessException {
        String docText = jCas.getDocumentText();
        Collection<Mention> matches = matcher.match(docText);
        for (Mention match : matches) {
            Chemical annotation = new Chemical(jCas);
            annotation.setBegin(match.getStart());
            annotation.setEnd(match.getEnd());
            annotation.setSource(Constants.DICTIONARY);
            annotation.addToIndexes();
        }
    }
}
