public class Part2 {
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

    public int countCTG(String dna){
        dna = dna.toUpperCase();
        int count = 0;
        int fIndex = dna.indexOf("CTG");
        while( -1 != fIndex){
            count++;
            fIndex = dna.indexOf("CTG", fIndex + 3);
        }
        return count;
    }

    public void test(){
        String dna = "ATGCCATAG";
        System.out.println("CG Ration of dna: \"" + dna + "\" is " + cgRatio(dna));
        dna = "CTGATTATGTTACGTCTGATTTTG";
        System.out.println("Codon CTG appears in dna: \"" + dna + "\" " + countCTG(dna) + " times");
    }
}
