package de.berlin.hu.chemspot;

import uk.co.flamingpenguin.jewel.cli.Option;

public interface ChemSpotArguments {
	  @Option(shortName="m")
	  String getPathToModelFile();
	  
	  @Option(shortName="d")
	  String getPathToDictionary();
	  boolean isPathToDictionary();

	  @Option(shortName="c")
	  String getPathToIOBCorpora();
	  boolean isPathToIOBCorpora();
	  
	  @Option(shortName="t")
	  String getPathToTextFile();
	  boolean isPathToTextFile();
	  
	  @Option(shortName="e")
	  boolean getRunEvaluation();
	  boolean isRunEvaluation();
	  
	  @Option(shortName="z")
	  boolean getZippedTextFile();
	  boolean isZippedTextFile();
	    
	  @Option(shortName="x")
	  String getPathToXMIOutputDirectory();
	  boolean isPathToXMIOutputDirectory();
	  
	  @Option(shortName="o")
	  String getPathToOutputFile();
	  boolean isPathToOutputFile();
}
