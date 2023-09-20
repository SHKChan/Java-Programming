public class MainRun {
    public static void main(String[] args) {
        clearScreen();
        Part3 p = new Part3();
        p.testProcessGenes();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}