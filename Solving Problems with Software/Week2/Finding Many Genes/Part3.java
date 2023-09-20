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
                ret = dna.substring(start, stop+3);
        }

        return ret;
    }

    public int countGenes(String dna){
        int count = 0;
        String curGene = findGene(dna);
        while(!curGene.isEmpty()){
            count++;
            int start = dna.indexOf(curGene);
            dna = dna.substring(start + curGene.length()); 
            curGene = findGene(dna);
        }
        return count;
    }

    public void testCountGenes(){
        String dna = "ATGTAAGATGCCCTAGT";
        System.out.println(String.format("Found %d genes in dna: \"%s\"", countGenes(dna), dna));
    }
}
