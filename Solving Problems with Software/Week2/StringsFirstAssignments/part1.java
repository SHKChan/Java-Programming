/**
 * Finds a protein within a strand of DNA represented as a string of c,g,t,a
 * letters.
 * A protein is a part of the DNA strand marked by start and stop codons (DNA
 * triples)
 * that is a multiple of 3 letters long.
 *
 * @author Duke Software Team
 */

public class part1 {
public String findSimpleGene(String dna) {
    dna = dna.toLowerCase();
    int startIdx = dna.indexOf("atg");
    int stopIdx = dna.indexOf("tag", startIdx + 3);
    
    if (startIdx != -1 && stopIdx != -1 && (stopIdx - startIdx) % 3 == 0) {
        return dna.substring(startIdx, stopIdx + 3);
    }
    
    return "";
}

	public void testing  () {
		// String a = "cccatggggtttaaataataataggagagagagagagagttt";
		// String ap = "atggggtttaaataataatag";
		// String a = "atgcctag";
		// String ap = "";
		String a = "ATGCCCTAG";
		String ap = "ATGCCCTAG";
		String result = findSimpleGene(a);
		if (ap.toLowerCase().equals(result)) {
			System.out.println("success for " + ap + " length " + ap.length());
		} else {
			System.out.println("mistake for input: " + a);
			System.out.println("got: " + result);
			System.out.println("not: " + ap);
		}
	}

}