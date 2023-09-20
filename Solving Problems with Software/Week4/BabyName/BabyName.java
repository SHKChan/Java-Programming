import java.io.File;
import java.util.ArrayList;

import org.apache.commons.csv.*;
import edu.duke.*;

public class BabyName {
    public void totalBirths(CSVParser cr) {
        int totalBirths = 0;
        int totalGirls = 0;
        int totalBoys = 0;
        for (CSVRecord rec : cr) {
            int temp = Integer.parseInt(rec.get(2));
            if ("F".equals(rec.get(1))) {
                totalGirls += temp;
            } else {
                totalBoys += temp;
            }
            totalBirths += temp;
        }
        System.out.println("Total Births: " + totalBirths);
        System.out.println("Total Girls: " + totalGirls);
        System.out.println("Total Boys: " + totalBoys);
    }

    public void testTotalBirths() {
        FileResource fr = new FileResource();
        this.totalBirths(fr.getCSVParser(false));
    }

    public int getRank(int year, String name, String gender) {
        int rank = 0;
        String path = ".\\us_babynames_by_year\\yob" + year + ".csv";
        FileResource fr = new FileResource(path);
        CSVParser cr = fr.getCSVParser(false);
        for (CSVRecord rec : cr) {
            if (rec.get(1).equals(gender)) {
                rank++;
                if (rec.get(0).equals(name)) {
                    return rank;
                }
            }
        }
        return -1;
    }

    public void testGetRank() {
        int year = 1960;
        String name = "Emily";
        String gender = "F";
        int rank = this.getRank(year, name, gender);
        System.out.println("Rank for " + name + ", " + gender + " is " + rank +  " in " + year);
    }

    public String whatIsNameInYear(int year, String name, int newYear, String gender) {
        int rank = this.getRank(year, name, gender);

        String path = ".\\us_babynames_by_year\\yob" + newYear + ".csv";
        FileResource fr = new FileResource(path);
        CSVParser cr = fr.getCSVParser(false);
        for (CSVRecord rec : cr) {
            if (rec.get(1).equals(gender)) {
                rank--;
                if (rank < 1) {
                    return rec.get(0);
                }
            }
        }
        return "NOT FOUND";
    }

    public void testWhatIsNameInYear() {
        int year = 1974;
        String name = "Owen";
        int newYear = 2014;
        String gender = "M";
        String newName = this.whatIsNameInYear(year, name, newYear, gender);
        System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear);
    }

    public int yearOfHighestRank(String name, String gender) {
        int highestRank = -1;
        int highestRankYear = -1;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            String fName = f.getName();
            int index = fName.indexOf("yob");
            int year = Integer.parseInt(fName.substring(index + 3, index + 7));
            int rank = this.getRank(year, name, gender);
            if (highestRank == -1 || rank < highestRank && rank != -1) {
                highestRank = rank;
                highestRankYear = year;
            }
        }
        return highestRankYear;
    }

    public void testYearOfHighestRank() {
        String name = "Genevieve";
        String gender = "F";
        int year = this.yearOfHighestRank(name, gender);
        System.out.println("Highest rank for " + name + ", " + gender + " is on year " + year + " in selected files");
    }

    public double getAverageRank (String name, String gender) {
        ArrayList<Integer> lsRank = new ArrayList<Integer>(0);
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            String fName = f.getName();
            int index = fName.indexOf("yob");
            int year = Integer.parseInt(fName.substring(index + 3, index + 7));
            int rank = this.getRank(year, name, gender);
            if (rank != -1) {
                lsRank.add(rank);
            }
        }
        
        double sum = 0;
        for(int e : lsRank) {
            sum += e;
        }

        return sum /lsRank.size();
    }

    public void testGetAverageRank () {
        String name = "Robert";
        String gender = "M";
        double averageRank = this.getAverageRank(name, gender);
        System.out.println("Average rank for " + name + ", " + gender + " is " + averageRank + " in selected files");
    }

    public int getTotalBirthsRankedHigher (int year, String name, String gender) {
        int totalHigher = 0;
        int rank = this.getRank(year, name, gender);

        String path = ".\\us_babynames_by_year\\yob" + year + ".csv";
        FileResource fr = new FileResource(path);
        CSVParser cr = fr.getCSVParser(false);
        for (CSVRecord rec : cr) {
            if (rec.get(1).equals(gender) && rank > 1) {
                rank--;
                totalHigher += Integer.parseInt(rec.get(2));
            }
        }

        return totalHigher;
    }

    public void testGetTotalBirthsRankedHigher () {
        String name = "Drew";
        String gender = "M";
        int year = 1990;
        int totalHigher = this.getTotalBirthsRankedHigher(year, name, gender);
        System.out.println("Total births ranked higher than " + name + ", " + gender + " is " + totalHigher + " in selected file");
    }

    public String whatIsNameOfRank(int year, int rank, String gender) {
        String path = ".\\us_babynames_by_year\\yob" + year + ".csv";
        FileResource fr = new FileResource(path);
        CSVParser cr = fr.getCSVParser(false);
        for (CSVRecord rec : cr) {
            if (rec.get(1).equals(gender)) {
                if(rank > 1){
                    rank--;
                }
                else{
                    return rec.get(0);
                }
            }
        }
        return "NOT FOUND";
    }

    public void testWhatIsNameOfRank() {
        int year = 1982;
        int rank = 450;
        String gender = "M";
        String name = this.whatIsNameOfRank(year, rank, gender);
        System.out.println("Name for rank " + rank + " in " + year + " is " + name + ", " + gender);
    }

    public void totalNames(CSVParser cr) {
        int totalNames = 0;
        int totalGirls = 0;
        int totalBoys = 0;
        for (CSVRecord rec : cr) {
            if ("F".equals(rec.get(1))) {
                totalGirls ++;
            } else {
                totalBoys ++;
            }
            totalNames ++;
        }
        System.out.println("Total Births: " + totalNames);
        System.out.println("Total Girls: " + totalGirls);
        System.out.println("Total Boys: " + totalBoys);
    }

    public void testTotalNames() {
        FileResource fr = new FileResource();
        this.totalNames(fr.getCSVParser(false));
    }
}
