public class MainRun {
    public static void main(String[] args) {
        MainRun.clearScreen();
        CharactersInPlay p = new CharactersInPlay();
        p.test();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
