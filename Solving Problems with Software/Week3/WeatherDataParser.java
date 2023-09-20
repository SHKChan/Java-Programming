import org.apache.commons.csv.*;
import edu.duke.*;

import java.io.File;
import java.util.ArrayList;

public class WeatherDataParser {
    public CSVRecord coldestHourInFile(CSVParser pr) {
        CSVRecord coldestRec = null;

        for (CSVRecord curRec : pr) {
            coldestRec = this.getColdestOfTwRecord(coldestRec, curRec);
        }
        return coldestRec;
    }

    public CSVRecord lowestHumidityInFile (CSVParser pr) {
        CSVRecord lowestRec = null;

        for (CSVRecord curRec : pr) {
            lowestRec = this.getlowestOfTwRecord(lowestRec, curRec);
        }
        return lowestRec;
    }

    public void testColdestHourInFile() {
        FileResource fr = new FileResource("./nc_weather/2014/weather-2014-01-08.csv");
        CSVRecord coldestRec = this.coldestHourInFile(fr.getCSVParser());
        System.out.println(
                "Coldest Temperature in file was " + coldestRec.get("TemperatureF") + " at " + coldestRec.get("TimeEST"));
    }

    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource("./nc_weather/2014/weather-2014-01-20.csv");
        CSVRecord lowestRec = this.lowestHumidityInFile(fr.getCSVParser());
        System.out.println(
                "Lowest Humidity in file was " + lowestRec.get("Humidity") + " at " + lowestRec.get("DateUTC"));
    }

    public CSVRecord getColdestOfTwRecord(CSVRecord coldestRec, CSVRecord curRec) {
        if (coldestRec == null) {
                return curRec;
        }

        if (Double.parseDouble(coldestRec.get("TemperatureF")) > 
            Double.parseDouble(curRec.get("TemperatureF")) &&
            Double.parseDouble(curRec.get("TemperatureF")) > 0) {
                return curRec;
            }
        else{
            return coldestRec;
        }
    }

     public CSVRecord getlowestOfTwRecord(CSVRecord lowestRec, CSVRecord curRec) {
        if (lowestRec == null) {
                return curRec;
        }

        if("N/A".equals(curRec.get("Humidity"))){
            return lowestRec;
        }

        if (Integer.parseInt(lowestRec.get("Humidity")) > 
            Integer.parseInt(curRec.get("Humidity"))) {
                return curRec;
            }
        else{
            return lowestRec;
        }
    }

    public String fileWithColdestTemperature() {
        String retFileName = "";
        CSVRecord coldestRec = null;
        CSVRecord curRec = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            curRec = this.coldestHourInFile(fr.getCSVParser());
            coldestRec = this.getColdestOfTwRecord(coldestRec, curRec);
            if(curRec ==  coldestRec){
                retFileName = f.getPath();
            }
        }
        return retFileName;
    }

    public String fileWithLowestHumidity() {
        String retFileName = "";
        CSVRecord lowestRec = null;
        CSVRecord curRec = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            curRec = this.lowestHumidityInFile(fr.getCSVParser());
            lowestRec = this.getlowestOfTwRecord(lowestRec, curRec);
            if(curRec ==  lowestRec){
                retFileName = f.getPath();
            }
        }
        return retFileName;
    }

    /* public String formatDate(String strDate) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = "";
        try {
            Date date = inputFormat.parse(strDate);
            formattedDate = outputFormat.format(date); // Output: 2014-01-03 05:51:00
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDate;
    } */

    public void dateHourTempInFile(CSVParser pr) {
        String formattedDate = "";
        System.out.println("All the Temperatures on the coldest day were:");
        for (CSVRecord rec : pr) {
            formattedDate = rec.get("DateUTC");
            System.out.println(formattedDate + ": " + rec.get("TemperatureF"));
        }
    }

    public void testFileWithColdestTemperature() {
        String filePath = this.fileWithColdestTemperature();
        String[] arrayFilePath = filePath.split("\\\\");
        String fileName = arrayFilePath[arrayFilePath.length - 1];
        System.out.println("Coldest day was " + fileName);

        FileResource fr = new FileResource(filePath);
        System.out.println("Coldest temperature on that day was " + this.coldestHourInFile(fr.getCSVParser()).get("TemperatureF"));
        this.dateHourTempInFile(fr.getCSVParser());
    }

    public void testFileWithLowestHumidity() {
        String filePath = this.fileWithLowestHumidity();
        FileResource fr = new FileResource(filePath);
        CSVRecord lowestRec = this.lowestHumidityInFile(fr.getCSVParser());
        System.out.println("Lowest Humidity was " + lowestRec.get("Humidity") + " at " + lowestRec.get("DateUTC"));
    }

    public double averageTemperatureInFile(CSVParser pr) {
        ArrayList<Double> listTemp = new ArrayList<Double>();

        for (CSVRecord curRec : pr) {
            listTemp.add(Double.parseDouble(curRec.get("TemperatureF")));
        }

        double sum = 0;
        for(double e : listTemp){
            sum += e;
        }

        return sum/listTemp.size();
    }

    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource("./nc_weather/2013/weather-2013-08-10.csv");
        double averageTemp = this.averageTemperatureInFile(fr.getCSVParser());
        System.out.printf(
                "Average temperature in file is %f%n", averageTemp);
    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser pr, int value) {
        ArrayList<Double> listTemp = new ArrayList<Double>();

        for (CSVRecord curRec : pr) {
            if("N/A".equals(curRec.get("Humidity")) || Integer.parseInt(curRec.get("Humidity")) < value){
                continue;
            }
            listTemp.add(Double.parseDouble(curRec.get("TemperatureF")));
        }

        if(listTemp.size() == 0){
            return 0.0;
        }

        double sum = 0;
        for(double e : listTemp){
            sum += e;
        }

        return sum/listTemp.size();
    }

    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource("./nc_weather/2013/weather-2013-09-02.csv");
        double averageTemp = this.averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        if (averageTemp == 0.0) {
            System.out.println("No temperatures with that humidity");
        } else {
            System.out.printf(
                    "Average temperature in file is %f%n", averageTemp);
        }
    }
}