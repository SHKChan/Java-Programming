public class MainRun{
    public static void main(String[] args){
        MainRun.clearScreen();
        
        // ExportDataParser scp = new ExportDataParser();
        // scp.tester();

        WeatherDataParser scp = new WeatherDataParser();
        scp.testFileWithColdestTemperature();
    }

    
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}