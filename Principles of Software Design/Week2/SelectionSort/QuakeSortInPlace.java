
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }

    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i = from + 1; i < quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }

    public void sortByMagnitude(ArrayList<QuakeEntry> in) {

        for (int i = 0; i < in.size(); i++) {
            int minIdx = this.getSmallestMagnitude(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i, qmin);
            in.set(minIdx, qi);
        }

    }

    public int getLargestDepth(ArrayList<QuakeEntry> quakes, int from) {
        int maxIdx = from;
        for (int i = from + 1; i < quakes.size(); i++) {
            if (quakes.get(i).getDepth() > quakes.get(maxIdx).getDepth()) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
        int pass = 0;
        for (int i = 0; i < in.size(); i++) {
            int maxIdx = this.getLargestDepth(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(maxIdx);
            in.set(i, qmin);
            in.set(maxIdx, qi);
            pass++;
            if(70 == pass){
                break;
            }
        }

    }

    public void onePassBubbleSort(ArrayList<QuakeEntry> in, int numSorted) {
        for (int i = 0; i < in.size() - numSorted - 1; i++) {
            if (in.get(i).getMagnitude() > in.get(i + 1).getMagnitude()) {
                QuakeEntry cur = in.get(i);
                in.set(i, in.get(i + 1));
                in.set(i + 1, cur);
            }
        }

        /* System.out.println("Printing Quakes after pass " + numSorted + " : ");
        for (QuakeEntry qe : in) {
            System.out.println(qe);
        } */
    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        for (int i = 0; i < in.size() - 1; i++) {
            this.onePassBubbleSort(in, i);
        }
    }

    public boolean checkInSortedOrder(ArrayList<QuakeEntry> in) {
        for (int i = 0; i < in.size() - 1; i++) {
            if (in.get(i).getMagnitude() > in.get(i + 1).getMagnitude()) {
                return false;
            }
        }
        return true;
    }

    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        int numSorted = 0;
        while (!checkInSortedOrder(in)) {
            this.onePassBubbleSort(in, numSorted);
            numSorted++;
        }

        System.out.println("Number of passes: " + numSorted);
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser();

        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        // String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);

        System.out.println("read data for " + list.size() + " quakes");

        // this.sortByMagnitude(list);
        // this.sortByLargestDepth(list);
        // this.sortByMagnitudeWithBubbleSort(list);
        this.sortByMagnitudeWithBubbleSortWithCheck(list);
        // this.sortByMagnitudeWithCheck(list);
        System.out.println("EarthQuakes in sorted order: ");
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }

    }

    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {

        int numSorted = 0;
        while (!checkInSortedOrder(in)) {
            int minIdx = this.getSmallestMagnitude(in, numSorted);
            QuakeEntry qi = in.get(numSorted);
            QuakeEntry qmin = in.get(minIdx);
            in.set(numSorted, qmin);
            in.set(minIdx, qi);
            numSorted++;
        }
        System.out.println("Number of passes: " + numSorted);
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        // String source =
        // "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for (QuakeEntry qe : list) {
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                    qe.getLocation().getLatitude(),
                    qe.getLocation().getLongitude(),
                    qe.getMagnitude(),
                    qe.getInfo());
        }

    }
}
