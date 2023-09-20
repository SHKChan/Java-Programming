import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    }

    public void test(){
        EarthQuakeParser xp = new EarthQuakeParser();

        String source = "data/nov20quakedata.atom";
        // String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = xp.read(source);

        /* Collections.sort(list);
         for(QuakeEntry loc : list){
            System.out.println(loc);
        } */

        System.out.println("read data for "+list.size()+" quakes");

        // list = filter(list, new MagnitudeFilter(0.0, 5.0));
        // list = filter(list, new DepthFilter(-180000.0, -30000.0));

        list = filter(list, new DistanceFilter(new Location(39.7392, -104.9903), 1000000));
        list = filter(list, new PhraseFilter(SearchPos.END, "a"));

        // System.out.println("# quakes after filter: " + list);
        System.out.println("# quake size after filter = " + list.size());

        // this.testMatchAllFilter2();
    }

    public void testMatchAllFilter(){
        EarthQuakeParser xp = new EarthQuakeParser();

        // String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = xp.read(source);

        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 2.0));
        maf.addFilter(new DepthFilter(-100000.0, -10000.0));
        maf.addFilter(new PhraseFilter(SearchPos.ANY, "a"));
        list = filter(list, maf);

        System.out.println("# quakes after filter: " + list);
        System.out.println("# quake size after filter = " + list.size());
    }

    public void testMatchAllFilter2(){
        EarthQuakeParser xp = new EarthQuakeParser();

        String source = "data/nov20quakedata.atom";
        // String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = xp.read(source);

        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 3.0));
        maf.addFilter(new DistanceFilter(new Location(36.1314, -95.9372), 10000000));
        maf.addFilter(new PhraseFilter(SearchPos.ANY, "Ca"));
        list = filter(list, maf);

        // System.out.println("# quakes after filter: " + list);
        System.out.println("# quake size after filter = " + list.size());
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

}
