import java.util.ArrayList;
import java.util.Collections;

public class LargestQuakes {
    public void test(){
        this.findLargestQuakes();
    }

    public void findLargestQuakes(){
        EarthQuakeParser xp = new EarthQuakeParser();

        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = xp.read(source);
        // for(QuakeEntry loc : list){
        //     System.out.println(loc);
        // }
        System.out.println("# quakes = "+list.size());
        /* int largestIndex = this.indexOfLargest(list);
        System.out.println("# Largest Quakes at index  = " + largestIndex +  " with magnitude = " + list.get(largestIndex).getMagnitude()); */
        int howMany = 50;
        ArrayList<QuakeEntry> largestQuake = this.getLargest(list, howMany);
        System.out.println("# Largest Quakes Top  " + howMany +  ": ");
        System.out.println(largestQuake);
    } 

    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
        ArrayList<QuakeEntry> largest = new ArrayList<QuakeEntry>();
        int index = -1;
        for(int i=0; i<howMany; i++){
            index = this.indexOfLargest(quakeData);
            largest.add(quakeData.get(index));
            quakeData.remove(index);
        }
        return largest;
    }

    public int indexOfLargest(ArrayList<QuakeEntry> quakeData){
        int fIndex = -1;
        double largest = Double.MIN_VALUE;
        for(int i=0; i<quakeData.size(); i++){
            if(quakeData.get(i).getMagnitude() > largest){
                largest = quakeData.get(i).getMagnitude();
                fIndex = i;
            }
        }

        return fIndex;
    }
}
