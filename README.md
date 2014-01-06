# ChemSpot

ChemSpot 2.0 is a set of tools for named entity recognition and classification of chemicals in natural language texts, including trivial names, abbreviations, molecular formulas and IUPAC entities. Since the different classes of relevant entities have rather different naming characteristics, ChemSpot uses a combined approach of employing a Conditional Random Field and a dictionary, as well as pattern-based recognition, a classifier model and several methods for consolidating all annotations. ChemSpot also performs named entity normalization by assigning identifiers from several chemical databases. It achieves an F1 measure of 79.0% on the SCAI corpus.

ChemSpot is released under the Common Public License 1.0 (see LICENSE).

The warning message "Couldn't open cc.mallet.util.MalletLogger resources/logging.properties file." can be ignored.


## Running ChemSpot:
- Extract chemspot.zip into a directory
```
unzip chemspot.zip
```

- To tag a sample text file, run
```
java -Xmx16G -jar chemspot.jar -t sample.txt -o predict.txt
```

- To update the dictionary, run
```
java -Xmx5G -jar chemspot.jar -u
```

If you would like to reduce memory consumption and do not need ChemSpot to assign identifiers to chemicals, you can run it without the ids file. Note however that this will completely disable named entity normalization.
```
java -Xmx12G -jar chemspot.jar -t sample.txt -o predict.txt -i ""
```

If you would like to further reduce the memory footprint, you can run ChemSpot without the dictionary or multi-class model as well. Note however that this will result in worse NER performance.
```
java -Xmx7G -jar chemspot.jar -t sample.txt -o predict.txt -i "" -d ""
java -Xmx9G -jar chemspot.jar -t sample.txt -o predict.txt -i "" -M ""
```


## Parameters
- arguments:
    - -m path to a CRF model file (internal default model file will be used if not provided)
    - -s path to a OpenNLP sentence model file (internal default model file will be used if not provided)
    - -d path to a zipped set of brics dictionary automata (parameter defaults to 'dict.zip' if not provided)"
    - -i path to a zipped tab-separated text file representing a map of terms to ids (parameter defaults to 'ids.zip' if not provided)
    - -M path to a multi-class model file (parameter defaults to 'multiclass.bin' if not provided)
    
- flags:
    - -e if this flag is set, the performance of ChemSpot on an IOB gold-standard corpus (cf. -c) is evaluated"
    - -u if this flag is set, ChemSpot will update the dictionary and ids file
    - -T number of threads to create when processing a document collection

- input control:
    - -c path to a directory containing corpora in IOB format
    - -g path to a directory containing gzipped text files
    - -t path to a text file
    - -f path to a directory of text files

- output control:
    - -o path to output file
    - -I if this flag is set, the output will be converted into the IOB format


## Using ChemSpot in your Code
```java
ChemSpot tagger = ChemSpotFactory.createChemSpot("dict.zip", "ids.zip", "multiclass.bin");
String text = "The abilities of LHRH and a potent LHRH agonist ([D-Ser-(But),6, " +
  "des-Gly-NH210]LHRH ethylamide) inhibit FSH responses by rat " +
  "granulosa cells and Sertoli cells in vitro have been compared.";

for (Mention mention : tagger.tag(text)) {
  System.out.printf("%d\t%d\t%s\t%s\t%s,\t%s%n", 
    mention.getStart(), mention.getEnd(), mention.getText(), 
    mention.getCHID(), mention.getSource(), mention.getType().toString());
}
```

## Reproducing our Results
- Download the SCAI corpus (chemicals-test-corpus-27-04-2009-v3.iob.gz) and put it in the same directory
- To reproduce our results, run
```
java -Xmx16G -jar chemspot.jar -c chemicals-test-corpus-27-04-2009-v3.iob.gz -o predict.txt -e
```


## Acknowledgements
We would like to thank Daniel Lowe and Philippe Thomas for many valuable suggestions. 
