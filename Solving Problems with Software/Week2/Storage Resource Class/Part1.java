import edu.duke.*;

public class Part1 {
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
        String startCodon = "ATG";
        int start = tempDna.indexOf(startCodon);
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

    public StorageResource getAllGenes(String dna){
        StorageResource sr = new StorageResource();
        String curGene = findGene(dna);
        while(!curGene.isEmpty()){
            // System.out.println("Found gene: " + curGene);
            sr.add(curGene);
            int start = dna.indexOf(curGene);
            dna = dna.substring(start + curGene.length()); 
            curGene = findGene(dna);
        }
        return sr;
    }

    public void testGetAllGenes(){
        String dna = "ATGTAAGATGCCCTAGT";
        StorageResource sr = getAllGenes(dna);
        for(String gene : sr.data()){
            System.out.println("Found gene: " + gene);
        }
    }
}
