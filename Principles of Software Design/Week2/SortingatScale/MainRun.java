public class MainRun{
    public static void main(String[] args){
        MainRun.clearScreen();
        DifferentSorters ds = new DifferentSorters();
        ds.sortByLastWordInTitleThenByMagnitude();
    }

    
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}