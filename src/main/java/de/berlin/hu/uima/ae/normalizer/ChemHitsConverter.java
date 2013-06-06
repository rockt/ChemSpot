package de.berlin.hu.uima.ae.normalizer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import groovyNormalizerBeans.NameNormalizer;

public class ChemHitsConverter {
	private static Map<String, String[]> ids = new HashMap<String, String[]>();
	private static int total = 0;
	private static int count = 0;
	private static Object lock = new Object();
	
	public static Map<String, String[]> convertIDs(Map<String, String[]> ids) {
		Map<String, String[]> result = new HashMap<String, String[]>();
		NameNormalizer nameNormalizer = new NameNormalizer();
		
		//System.out.println("Converting...");
		
		for (String chem : ids.keySet()) {
			nameNormalizer.setName(chem);
			String normalized = nameNormalizer.getNormName();
			result.put(normalized, ids.get(chem));
			
			synchronized(lock) {			
				if (++count % (total / 100) == 0) {
					System.out.printf("\r%d%% done", Math.round((double)count / (double)total * 100.0));
				}
			}
		}
		
		return ids;
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		String idsPath = args[0];
		String outputPath = args[1];
		int threads = args.length >= 3 ? Integer.parseInt(args[2]) : 1;
		
		ExecutorService threadPool = Executors.newFixedThreadPool(threads);
		
		System.out.print("Loading ids... ");
		Map<String, String[]> ids = Normalizer.loadIdsFromFile(idsPath);
		System.out.println("Done.");
		
		total = ids.size();

		Map<String, String[]> batch = new HashMap<String, String[]>();
		int count = 0;
		int thread = 0;
		for (String chem : ids.keySet()) {
			batch.put(chem, ids.get(chem));
			
			if (++count >= Math.ceil((double)ids.size() / (double)(threads))) {
				threadPool.submit(new ChemHitsRun(++thread, batch));
				count = 0;
				batch = new HashMap<String, String[]>();
			}
		}
		
		if (!batch.isEmpty()) {
			threadPool.submit(new ChemHitsRun(++thread, batch));
			count = 0;
			batch = new HashMap<String, String[]>();
		}
		
		threadPool.shutdown();
		try {
			threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.print("\nWriting ids... ");
		Normalizer.writeIDs(outputPath, ChemHitsConverter.ids);
		System.out.println("Done.");
	}
	
	private static class ChemHitsRun implements Runnable {
    	private int runNr = -1;
    	private Map<String, String[]> idsBatch = null;
    	
    	public ChemHitsRun (int runNr, Map<String, String[]> ids) {
    		this.runNr = runNr;
    		this.idsBatch = ids;
    	}
    	
		public void run() {
			System.out.println("Starting run " + runNr);
			Map<String, String[]> convertedIds = convertIDs(idsBatch);
			System.out.println("Run " + runNr + " finished");
			
			synchronized(ids) {
				ids.putAll(convertedIds);
			}
		}
    }
}