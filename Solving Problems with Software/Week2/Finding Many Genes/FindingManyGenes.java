public class FindingManyGenes{
    public static void main(String[] args){
        clearScreen();
        Part1 p = new Part1();
        p.testfindGene();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
}