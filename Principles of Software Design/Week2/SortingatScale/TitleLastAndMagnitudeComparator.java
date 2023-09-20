import java.util.Comparator;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{
    TitleLastAndMagnitudeComparator(){
        
    }

    @Override
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        // TODO Auto-generated method stub
        String lastWord1 = getLastWord(q1.getInfo());
        String lastWord2 = getLastWord(q2.getInfo());
        if(lastWord1.equals(lastWord2)){
            return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
        return lastWord1.compareTo(lastWord2);
    }

    private String getLastWord(String s){
        String[] words = s.split(" ");
        return words[words.length - 1];
    }


}
