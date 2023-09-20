import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(QuakeEntry qe : quakeData){
            if(qe.getMagnitude() > magMin){
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(QuakeEntry qe : quakeData){
            if(qe.getLocation().distanceTo(from) < distMax){
                answer.add(qe);
            }
        }

        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("Read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> filterList = this.filterByMagnitude(list, 5.0); 
        System.out.println("Filter each quake by magnitude: " + filterList);
        System.out.println("Found " + filterList.size() + " quakes that match that criteria");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        // Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);

        // TODO
        ArrayList<QuakeEntry> filterList  = this.filterByDistanceFrom(list, 1000000, city);
        System.out.println("Filter each quake by distance: " + filterList);
        System.out.println("Found " + filterList.size() + " quakes that match that criteria");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }

    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        double depth = 0.0;
        for (QuakeEntry qe : quakeData) {
            depth = qe.getDepth();
            if (minDepth < depth && maxDepth > depth) {
                answer.add(qe);
            }
        }

        return answer;
    }

    public void quakesOfDepth () {
        EarthQuakeParser parser = new EarthQuakeParser();

        // String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        // TODO
        ArrayList<QuakeEntry> filterList = this.filterByDepth(list, -4000.0, -2000.0);
        System.out.println("Filter each quake by depth: " + filterList);
        System.out.println("Found " + filterList.size() + " quakes that match that criteria");
    }

    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String phrase, String where) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        int index = -1;
        for (QuakeEntry qe : quakeData) {
            index = qe.getInfo().indexOf(phrase);
            if ("any".equals(where) && -1 != index) {
                answer.add(qe);
            }
            if("start".equals(where) && 0 == index){
                answer.add(qe);
            }
            if("end".equals(where) && qe.getInfo().length() == index + phrase.length()){
                answer.add(qe);
            }
        }

        return answer;
    }

    public void quakesByPhrase  () {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source =
        // "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        // TODO
        ArrayList<QuakeEntry> filterList = this.filterByPhrase(list ,"Creek" , "any");
        System.out.println("Filter each quake by phrase: " + filterList);
        System.out.println("Found " + filterList.size() + " quakes that match that criteria");
    }
    
}
