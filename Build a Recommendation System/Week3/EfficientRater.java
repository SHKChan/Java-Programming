import java.util.*;

public class EfficientRater implements Rater {
    private String myID;
    // private ArrayList<Rating> myRatings;
    private HashMap<String, Rating> movieRatingMap; // movieID -> rating

    public EfficientRater(String id) {
        this.myID = id;
        this.movieRatingMap = new HashMap<String, Rating>();
    }

    public void addRating(String item, double rating) {
        String paddingID = String.format("%07d", Integer.parseInt(item));
        this.movieRatingMap.put(paddingID, new Rating(paddingID, rating));
    }

    public boolean hasRating(String item) {
        String paddingID = String.format("%07d", Integer.parseInt(item));
        return this.movieRatingMap.containsKey(paddingID);
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        if (!hasRating(item)) {
            return -1;
        }
        String paddingID = String.format("%07d", Integer.parseInt(item));
        return movieRatingMap.get(paddingID).getValue();
    }

    public int numRatings() {
        return movieRatingMap.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for (String key : this.movieRatingMap.keySet()) {
            list.add(this.movieRatingMap.get(key).getItem());
        }
        return list;
    }

    // Returns a string of the item's information
    public String toString() {
        String result = "Rater [ ID=" + myID;
        for (String key : this.movieRatingMap.keySet()) {
            result += ", " + this.movieRatingMap.get(key).toString();
        }
        result += " ]";
        return result;
    }
}
