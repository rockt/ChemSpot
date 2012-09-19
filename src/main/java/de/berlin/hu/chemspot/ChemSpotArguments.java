package de.berlin.hu.chemspot;

import uk.co.flamingpenguin.jewel.cli.Option;

public interface ChemSpotArguments {
	  @Option(shortName="m")
	  String getPathToCRFModelFile();

      @Option(shortName="s")
 	  String getPathToSentenceModelFile();

	  @Option(shortName="d")
	  String getPathToDictionary();
	  boolean isPathToDictionary();

	  @Option(shortName="c")
	  String getPathToIOBCorpora();
	  boolean isPathToIOBCorpora();

      @Option(shortName="i")
 	  String getPathToIDs();
      boolean isPathToIDs();
	  
	  @Option(shortName="C")
	  String getPathToGZCorpus();
	  boolean isPathToGZCorpus();
	  
	  @Option(shortName="t")
	  String getPathToTextFile();
	  boolean isPathToTextFile();
	  
	  @Option(shortName="e")
	  boolean isRunEvaluation();
	  
	  @Option(shortName="z")
	  boolean isZippedTextFile();

	  @Option(shortName="o")
	  String getPathToOutputFile();
	  boolean isPathToOutputFile();

      @Option(shortName="l")
      String getTagCommandLine();
 	  boolean isTagCommandLine();
}
