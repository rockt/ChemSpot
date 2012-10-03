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
	  
	  @Option(shortName="C")
	  String getPathToCRAFTCorpus();
	  boolean isPathToCRAFTCorpus();

      @Option(shortName="i")
 	  String getPathToIDs();
      boolean isPathToIDs();
	  
	  @Option(shortName="g")
	  String getPathToGZCorpus();
	  boolean isPathToGZCorpus();
	  
	  @Option(shortName="S")
	  String getSerializeOutputPath();
	  boolean isSerializeOutputPath();
	  
	  @Option(shortName="t")
	  String getPathToTextFile();
	  boolean isPathToTextFile();
	  
	  @Option(shortName="T")
	  int getThreadNr();
	  boolean isThreadNr();
	  
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
