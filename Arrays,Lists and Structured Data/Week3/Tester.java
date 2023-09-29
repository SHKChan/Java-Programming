
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.File;
import java.util.*;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class Tester
{
    public static void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public static void testLogAnalyzer() {
        // complete method
        LogAnalyzer la = new LogAnalyzer();

        String fName = "";
        for(File f : new DirectoryResource().selectedFiles()){
            fName = f.getPath();
            break;
        }
        la.readFile(fName);
        // la.printAll();
        // la.countUniqueIPs();
        la.uniqueIPVisitsOnDay("Sep 27");
        // la.countUniqueIPsInRange(200,299);
        // la.printAllHigherThanNum(400);
        // String someday = "Mar 24";
        // la.uniqueIPVisitsOnDay(someday);

        // la.mostNumberVisitsByIP(la.countVisitsPerIP());
        // la.iPsMostVisits(la.countVisitsPerIP());
        // la.iPsForDays();
        // la.dayWithMostIPVisits(la.iPsForDays());
        // la.iPsWithMostVisitsOnDay(la.iPsForDays(), "Sep. 30");

    }
}
