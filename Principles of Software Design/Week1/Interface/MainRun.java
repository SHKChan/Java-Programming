public class MainRun{
    public static void main(String[] args){
        MainRun.clearScreen();
        EarthQuakeClient2 eq2 = new EarthQuakeClient2();
        eq2.test();
    }
    
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}