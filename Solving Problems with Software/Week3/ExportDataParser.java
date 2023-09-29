import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.FileResource;

public class ExportDataParser {
    public String GetCountryInfo(CSVParser pr, String country){
        String ret = "NOT FOUND";
        for(CSVRecord rec : pr){
            if(rec.get("Country").equals(country)){
                ret = rec.get("Country") + ": " + rec.get("Exports") + ": " + rec.get("Value (dollars)");
            }
        }
        return ret;
    }

    public void listExportersTwoProducts(CSVParser pr, String exportItem1, String exportItem2){
        for(CSVRecord rec : pr){
            if(rec.get("Exports").contains(exportItem1) && rec.get("Exports").contains(exportItem2)){
                System.out.println(rec.get("Country"));
            }
        }
    }

    public int numberOfExporters(CSVParser pr, String exportItem){
        int count = 0;
        for(CSVRecord rec : pr){
            if(rec.get("Exports").contains(exportItem)){
                count++;
            }
        }
        return count;
    }

    public void bigExporters(CSVParser pr, String amount){
        int length = amount.length();
        for(CSVRecord rec : pr){
            if(rec.get("Value (dollars)").length() > length){
                System.out.println(rec.get("Country") + " " + rec.get("Value (dollars)"));
            }
        }
    }

    public void tester() {
        FileResource fr = new FileResource();
        CSVParser pr;

        // System.out.println("__________Testing on GetCountryInfo__________");
        // pr = fr.getCSVParser();
        // System.out.println(this.GetCountryInfo(pr, "Nauru"));
        
        System.out.println("__________Testing on listExportersTwoProducts__________");
        pr = fr.getCSVParser();
        this.listExportersTwoProducts(pr, "cotton", "flowers");
        
        // System.out.println("__________Testing on numberofExporters__________");
        // pr = fr.getCSVParser();
        // System.out.println(this.numberOfExporters(pr, "cocoa"));

        System.out.println("__________Testing on bigExporters__________");
        pr = fr.getCSVParser();
        this.bigExporters(pr, "$999,999,999,999");
    }
}