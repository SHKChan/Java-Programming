import java.util.Comparator;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    TitleAndDepthComparator(){
        
    }

    @Override
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        // TODO Auto-generated method stub
        if(q1.getInfo().equals(q2.getInfo())){
            return Double.compare(-q1.getDepth(), -q2.getDepth());
        }
        return q1.getInfo().compareTo(q2.getInfo());
    }
}