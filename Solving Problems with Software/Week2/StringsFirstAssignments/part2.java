/**
 * Finds a protein within a strand of DNA represented as a string of c,g,t,a
 * letters.
 * A protein is a part of the DNA strand marked by start and stop codons (DNA
 * triples)
 * that is a multiple of 3 letters long.
 *
 * @author Duke Software Team
 */

public class part2 {
public String findSimpleGene(String dna, String startCodon, String stopCodon) {
    String tempDna = dna.toLowerCase();
    startCodon = startCodon.toLowerCase();
    stopCodon = stopCodon.toLowerCase();

    int startIdx = tempDna.indexOf(startCodon);
    int stopIdx = tempDna.indexOf(stopCodon, startIdx + 3);

    if (startIdx == -1 || stopIdx == -1 || (stopIdx - startIdx) % 3 != 0) {
        return "";
    }

    return dna.substring(startIdx, stopIdx + 3);
}

	public void testing  () {
		// String a = "cccatggggtttaaataataataggagagagagagagagttt";
		// String ap = "atggggtttaaataataatag";
		// String a = "atgcctag";
		// String ap = "";
		// String a = "ATGCCCTAG";
		// String ap = "ATGCCCTAG";
        String a = "AAATGCCCTAACTAGATTAAGAAACC";
        String ap = "";
		String result = findSimpleGene(a, "ATG", "TAA");
		if (ap.toLowerCase().equals(result)) {
			System.out.println("success for " + ap + " length " + ap.length());
		} else {
			System.out.println("mistake for input: " + a);
			System.out.println("got: " + result);
			System.out.println("not: " + ap);
		}
	}

}