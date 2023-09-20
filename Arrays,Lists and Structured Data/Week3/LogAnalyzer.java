
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import edu.duke.*;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        // complete constructor
        this.records = new ArrayList<LogEntry>();
    }

    public void readFile(String filename) {
        // complete method
        FileResource fr = new FileResource(filename);
        for (String line : fr.lines()) {
            LogEntry le = WebLogParser.parseEntry(line);
            this.records.add(le);
        }
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

    public int countUniqueIPs() {
        /*
         * ArrayList<String> uniqueIPs = new ArrayList<String>();
         * for (LogEntry le : this.records) {
         * if (!uniqueIPs.contains(le.getIpAddress())) {
         * uniqueIPs.add(le.getIpAddress());
         * }
         * }
         * return uniqueIPs.size();
         */

        int sz = this.countVisitsPerIP().size();
        System.out.println("unique IPs: " + sz);
        return sz;
    }

    public void printAllHigherThanNum(int num) {
        System.out.println("Status code greater than " + num + " : ");
        for (LogEntry le : records) {
            if (le.getStatusCode() > num) {
                System.out.println(le);
            }
        }
    }

    public static Date parseString2Date(String dateStr) {
        final SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy - HH:mm:ss", Locale.US);
        ParsePosition pp = new ParsePosition(0);
        return sdf.parse(dateStr, pp);
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIPsOnDay = new ArrayList<String>();
        someday += " 2015 - 00:00:00";
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(LogAnalyzer.parseString2Date(someday));

        Calendar cal2 = Calendar.getInstance();

        String ip = "";
        for (LogEntry le : this.records) {
            cal2.setTime(le.getAccessTime());
            if (cal2.get(Calendar.MONTH) == cal1.get(Calendar.MONTH)
                    && cal2.get(Calendar.DAY_OF_MONTH) == cal1.get(Calendar.DAY_OF_MONTH)) {
                ip = le.getIpAddress();
                if (!uniqueIPsOnDay.contains(ip)) {
                    uniqueIPsOnDay.add(ip);
                }
            }
        }
        System.out.println("unique IPs on day " + someday + ": " + uniqueIPsOnDay);
        return uniqueIPsOnDay;
    }

    public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> uniqueIPsInRange = new ArrayList<String>();
        int status = 0;
        for (LogEntry le : this.records) {
            status = le.getStatusCode();
            if (low <= status && high >= status && !uniqueIPsInRange.contains(le.getIpAddress())) {
                uniqueIPsInRange.add(le.getIpAddress());
            }
        }
        System.out.println("unique IPs in Range(" + low + ", " + high + "): " + uniqueIPsInRange);
        return uniqueIPsInRange.size();
    }

    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> ipVisitsMap = new HashMap<String, Integer>();
        String ip = "";
        for (LogEntry le : this.records) {
            ip = le.getIpAddress();
            if (!ipVisitsMap.containsKey(ip))
                ipVisitsMap.put(ip, 1);
            else
                ipVisitsMap.put(ip, ipVisitsMap.get(ip) + 1);
        }

        System.out.println("unique IPs: " + ipVisitsMap);
        return ipVisitsMap;
    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> ipVisitsMap) {
        int max = 0;
        String ip = "";
        for (String key : ipVisitsMap.keySet()) {
            if (ipVisitsMap.get(key) > max) {
                max = ipVisitsMap.get(key);
                ip = key;
            }
        }
        System.out.println("IP with most visits is: " + ip + " " + max);
        return max;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> ipVisitsMap) {
        ArrayList<String> ret = new ArrayList<String>();
        int max = this.mostNumberVisitsByIP(ipVisitsMap);
        for (String key : ipVisitsMap.keySet()) {
            if (max == ipVisitsMap.get(key)) {
                ret.add(key);
            }
        }
        System.out.println("IP with most visits: " + ret);
        return ret;
    }

    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> dayIpsMap = new HashMap<String, ArrayList<String>>();

        String day = "";
        String ip = "";

        for (LogEntry le : this.records) {
            day = LogAnalyzer.parseDate2String(le.getAccessTime());
            ip = le.getIpAddress();
            if (!dayIpsMap.containsKey(day)) {
                ArrayList<String> ls = new ArrayList<String>();
                ls.add(ip);
                dayIpsMap.put(day, ls);
            } else {
                dayIpsMap.get(day).add(ip);
            }
        }

        System.out.println("IPs for days: " + dayIpsMap);
        return dayIpsMap;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> dayIpsMap) {
        int max = 0;
        String day = "";
        for (String key : dayIpsMap.keySet()) {
            if (dayIpsMap.get(key).size() > max) {
                max = dayIpsMap.get(key).size();
                day = key;
            }
        }
        System.out.println("Day with most visits: " + day + " " + max);
        return day;
    }

    public static String parseDate2String(Date date) {
        final SimpleDateFormat sdf = new SimpleDateFormat("MMM dd");
        return sdf.format(date);
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> dayIpsMap, String someday) {
        ArrayList<String> ls = new ArrayList<String>();
        HashMap<String, Integer> ipsVisitMap = new HashMap<String, Integer>();
        int max = 0;
        for (String ip : dayIpsMap.get(someday)) {
            if(!ipsVisitMap.containsKey(ip)) {
                ipsVisitMap.put(ip, 1);
            }
            else{
                ipsVisitMap.put(ip, ipsVisitMap.get(ip) + 1);
            }
            if (ipsVisitMap.get(ip) > max) {
                max = ipsVisitMap.get(ip);
            }
        }
        
        /* // convery HashMap into ArrayList
        ArrayList<Map.Entry<String, Integer>> entryList = new ArrayList<>(ipsVisitMap.entrySet());
        // sort the list using a custom comparator
        Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                return entry1.getKey().compareTo(entry2.getKey()); // Sort by keys in ascending order
            }
        });
        for (Map.Entry<String, Integer> entry : entryList) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        } */
        System.out.println("IP with most visits on " + someday + ": ");
        for (String key : ipsVisitMap.keySet()) {
            if(max == ipsVisitMap.get(key)) {
                System.out.println(key + " ");
            }
        }

        return ls;
    }
}
