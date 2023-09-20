public class MainRun {
    public static void main(String[] args) {
        MainRun.clearScreen();
        WordInFiles p = new WordInFiles();
        p.test();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
