
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainRun {
    public static void main(String[] args) {
        MainRun.clearScreen();
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss - MMMM d, yyyy");
        String formattedTime = currentTime.format(formatter);
        System.out.println("----------New Run on: " + formattedTime + "----------");
        MarkovRunner p = new MarkovRunner();
        p.runMarkov();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}