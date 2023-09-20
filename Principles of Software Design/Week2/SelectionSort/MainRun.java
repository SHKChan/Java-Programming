public class MainRun{
    public static void main(String[] args){
        MainRun.clearScreen();
        QuakeSortInPlace qsp = new QuakeSortInPlace();
        qsp.testSort();
    }

    
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}