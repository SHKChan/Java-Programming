public class Part1 {
    public int findStopCodonIndex(String dna, int start, String stopCodon) {
        int res = dna.length();
        // System.out.println("Test on gene: " + dna);
        // System.out.println("Stop codon: " + stopCodon);
        int stop = dna.indexOf(stopCodon, start + 3);
        while (-1 != stop) {
            // is a multiple of 3 away from startIndex
            // return stop index
            if ((stop - start) % 3 == 0) {
                res = stop;
                break;
            }
            // update stop index
            stop = dna.indexOf(stopCodon, stop + 3);
        }

        // if (dna.length() != res)
        // System.out.println("Found dna: " + dna.substring(start, stop + 3));
        // else
        // System.out.println("Not found");
        // System.out.println();

        // "dna.length()" means not found
        return res;
    }

    public void testfindStopCodonIndex() {
        String startCodon = "ATG";

        String dna = "ATGCGTAAGGUTATTTAA";
        String stopCodon = "TAA";
        int start = dna.indexOf(startCodon);
        int stop = findStopCodonIndex(dna, start, stopCodon);

        dna = "GCGTATATGGGUTATTTTTAGATTACGTAT";
        stopCodon = "TAG";
        start = dna.indexOf(startCodon);
        stop = findStopCodonIndex(dna, start, stopCodon);

        dna = "ATGTATGGGUTATTTTTAGATTACGTATGA";
        stopCodon = "TGA";
        start = dna.indexOf(startCodon);
        stop = findStopCodonIndex(dna, start, stopCodon);

        dna = "";
        stopCodon = "TGA";
        start = dna.indexOf(startCodon);
        stop = findStopCodonIndex(dna, start, stopCodon);
    }

    public String findGene(String dna) {
        System.out.println("Test on gene: " + dna);
        String ret = "";

        String tempDna = dna.toUpperCase();
        String startCodon = "ATG";
        int start = tempDna.indexOf(startCodon);
        if (-1 != start) {
            int tgaStop = findStopCodonIndex(tempDna, start, "TGA");
            int taaStop = findStopCodonIndex(tempDna, start, "TAA");
            int tagStop = findStopCodonIndex(tempDna, start, "TAG");
            int stop = Math.min(Math.min(tgaStop, taaStop), tagStop);
            if (dna.length() != stop)
                ret = dna.substring(start, stop + 3);
        }

        if (!ret.isEmpty())
            System.out.println("Found dna: " + ret);
        else
            System.out.println("Not found");
        System.out.println();
        return ret;
    }

    public void testfindGene() {
        // ^ ^ ^ ^ ^
        String dna0 = "CGTAAGGUTATTTAA";
        // ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^
        String dna1 = "TTAATGCGTAAGGUTATTTAAATGTAGTGTTAATAG";
        // ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^
        String dna2 = "TTAATGCGTAAGGUTATTTATATGTATTGTAAATGG";

        // ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^
        String dna3 = "AATGCTAACTAGCTGACTAAT";

        findGene(dna0);
        findGene(dna1);
        findGene(dna2);
        findGene(dna3);
    }
}
