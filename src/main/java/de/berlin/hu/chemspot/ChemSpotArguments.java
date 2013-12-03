package de.berlin.hu.chemspot;

import uk.co.flamingpenguin.jewel.cli.Option;

public interface ChemSpotArguments {
	  @Option(shortName="m")
	  String getPathToCRFModelFile();
	  boolean isPathToCRFModelFile();

      @Option(shortName="s")
 	  String getPathToSentenceModelFile();
      boolean isPathToSentenceModelFile();

	  @Option(shortName="d")
	  String getPathToDictionary();
	  boolean isPathToDictionary();
	  
      @Option(shortName="i")
 	  String getPathToIDs();
      boolean isPathToIDs();
	  
	  @Option(shortName="M")
	  String getPathToDrugModelFile();
	  boolean isPathToDrugModelFile();

	  @Option(shortName="c")
	  String getPathToIOBCorpora();
	  boolean isPathToIOBCorpora();
	  
	  @Option(shortName="C")
	  String getPathToCRAFTCorpus();
	  boolean isPathToCRAFTCorpus();
      
      @Option(shortName="I")
      boolean isConvertToIOB();
	  
	  @Option(shortName="g")
	  String getPathToGZCorpus();
	  boolean isPathToGZCorpus();
	  
	  @Option(shortName="t")
	  String getPathToTextFile();
	  boolean isPathToTextFile();
	  
	  @Option(shortName="T")
	  int getThreadNr();
	  boolean isThreadNr();
	  
	  @Option(shortName="e")
	  boolean isRunEvaluation();
	  
	  @Option(shortName="E")
	  boolean isDetailedEvaluation();
	  
	  @Option(shortName="z")
	  boolean isZippedTextFile();

	  @Option(shortName="o")
	  String getPathToOutputFile();
	  boolean isPathToOutputFile();

      @Option(shortName="l")
      String getTagCommandLine();
 	  boolean isTagCommandLine();
 	  
 	  @Option(shortName="n")
      String getPathToNaCTeMCorpus();
	  boolean isPathToNaCTeMCorpus();
	  
 	  @Option(shortName="P")
      String getPathToPatentCorpus();
	  boolean isPathToPatentCorpus();
	  
	  @Option(shortName="p")
      String getPathToPropertiesFile();
	  boolean isPathToPropertiesFile();
 	  
 	  @Option(shortName="x")
      String getPathToXMICorpus();
	  boolean isPathToXMICorpus();
 	  
 	  @Option(shortName="X")
      String getPathToXMIOutput();
	  boolean isPathToXMIOutput();
	  
	  @Option(shortName="D")
	  String getPathToDDICorpus();
	  boolean isPathToDDICorpus();
	  
	  @Option(shortName="f")
	  String getPathToTextCorpus();
	  boolean isPathToTextCorpus();
	  
	  @Option(shortName="B")
	  String getPathToCHEMDNERCorpus();
	  boolean isPathToCHEMDNERCorpus();
	  
	  @Option(shortName="u")
	  boolean isUpdate();
}
