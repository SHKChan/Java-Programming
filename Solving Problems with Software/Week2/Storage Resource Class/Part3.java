import edu.duke.FileResource;
import edu.duke.StorageResource;

public class Part3 {
    public int findStopCodonIndex(String dna, int start, String stopCodon) {
        int res = dna.length();
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
        return res;
    }

    public String findGene(String dna) {
        String tempDna = dna.toUpperCase();
        int start = tempDna.indexOf("ATG");
        if (-1 == start)
            return "";

        int tgaStop = findStopCodonIndex(tempDna, start, "TGA");
        int taaStop = findStopCodonIndex(tempDna, start, "TAA");
        int tagStop = findStopCodonIndex(tempDna, start, "TAG");
        int stop = Math.min(Math.min(tgaStop, taaStop), tagStop);
        if (dna.length() == stop)
            return "";

        return dna.substring(start, stop + 3);
    }

    public StorageResource getAllGenes(String dna) {
        StorageResource sr = new StorageResource();
        String curGene = findGene(dna);
        while (!curGene.isEmpty()) {
            // System.out.println("Found gene: " + curGene);
            sr.add(curGene);
            int start = dna.indexOf(curGene);
            dna = dna.substring(start + curGene.length());
            curGene = findGene(dna);
        }
        return sr;
    }

    public double cgRatio(String dna) {
        dna = dna.toUpperCase();
        int cgCount = 0;
        for (char c : dna.toCharArray()) {
            if (c == 'C' || c == 'G') {
                cgCount++;
            }
        }
        return (double) cgCount / dna.length();
    }

    public int countCTG(String dna) {
        dna = dna.toUpperCase();
        int count = 0;
        int fIndex = dna.indexOf("CTG");
        while (-1 != fIndex) {
            count++;
            fIndex = dna.indexOf("CTG", fIndex + 3);
        }
        return count;
    }

    public void processGenes(StorageResource sr) {
        int greaterThan9 = 0;
        int cgRation = 0;
        int longestLength = 0;
        for (String gene : sr.data()) {
            if (gene.length() > 60) {
                greaterThan9++;
                System.out.println("Found gene: " + gene + "longer than 60 characters");
                System.out.println("------------------------------");
            }
            if (cgRatio(gene) > 0.35) {
                cgRation++;
                System.out.println("Found gene: " + gene + "CG ratio > 0.35");
                System.out.println("------------------------------");
            }
            if (gene.length() > longestLength) {
                longestLength = gene.length();
            }
        }

        System.out.println("Found " + greaterThan9 + " genes longer than 60 characters");
        System.out.println("Found " + cgRation + " CG ratio > 0.35");
        System.out.println("Found longest gene with length: " + longestLength);
    }

    public void testProcessGenes() {
        FileResource fr = new FileResource("./dna/url.fa");
        String dna = fr.asString();
        processGenes(getAllGenes(dna));
        System.out.println("Codon CTG appears in dna: \"" + dna + "\" " + countCTG(dna) + " times");
    }
}
