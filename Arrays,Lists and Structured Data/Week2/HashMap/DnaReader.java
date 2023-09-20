import java.util.HashMap;

import edu.duke.FileResource;

public class DnaReader {
    private HashMap<String, Integer> codonMap;

    public DnaReader() {
        codonMap = new HashMap<String, Integer>();
    }

    public void buildCodonMap(int start, String dna) {
        this.codonMap.clear();
        String codon = "";
        for (int i = start; i < dna.length() - 2; i += 3) {
            codon = dna.substring(i, i + 3);
            if (!codonMap.containsKey(codon)) {
                codonMap.put(codon, 1);
            } else {
                codonMap.put(codon, codonMap.get(codon) + 1);
            }
        }
    }

    public String getMostCommonCodon() {
        int maxCount = -1;
        int curCount = -1;
        String codon = "";
        for (String key : this.codonMap.keySet()) {
            curCount = this.codonMap.get(key);
            if (curCount > maxCount) {
                maxCount = curCount;
                codon = key;
            }
        }
        return codon;
    }

    public void printCodonCounts(int start, int end) {
        System.out.println("Counts of codons between" + start + " and " + end + " inclusive are:");
        int curCount = -1;
        for (String key : this.codonMap.keySet()) {
            curCount = this.codonMap.get(key);
            if (start <= curCount && end >= curCount) {
                System.out.println(key + "\t" + curCount);
            }
        }
    }

    public void test() {
        String dna = new FileResource().asString().toUpperCase().trim();
        String mostCodon = "";
        for (int i = 0; i < 3; i++) {
            buildCodonMap(i, dna);
            System.out.println(
                    "Reading frame starting with " + i + " results in " + this.codonMap.size() + " unique codons");
            mostCodon = this.getMostCommonCodon();
            System.out.println("and most common codon is " + mostCodon + " with count " + this.codonMap.get(mostCodon));
            this.printCodonCounts(1, 10);
            System.out.println();
        }
    }
}