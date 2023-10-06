
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.FileResource;

public class ThirdRatings {
    private ArrayList<EfficientRater> myRaters;
    private HashMap<String, ArrayList<EfficientRater>> movieRaterMap;

    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }

    public ThirdRatings(String ratingsfile) {
        FileResource ratingsfileResource = new FileResource("data/" + ratingsfile);
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsfileResource);
        this.movieRaterMap = new HashMap<String, ArrayList<EfficientRater>>();
        this.buildMovieRaterMap();
    }

    public int getRaterSize() {
        return myRaters.size();
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ArrayList<String> movieIDs = MovieDatabase.filterBy(new TrueFilter());
        for (String id : movieIDs) {
            double average = this.getAverageByID(id, minimalRaters);
            if (0.0 != average) {
                ratings.add(new Rating(id, average));
            }
        }
        return ratings;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter f) {
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ArrayList<String> movieIDs = MovieDatabase.filterBy(f);
        for (String id : movieIDs) {
            double average = this.getAverageByID(id, minimalRaters);
            if (0.0 != average) {
                ratings.add(new Rating(id, average));
            }
        }
        return ratings;
    }

    public double getAverageByID(String id, int minimalRaters) {
        double sum = 0.0;
        int count = 0;
        for (EfficientRater rater : this.myRaters) {
            if (rater.hasRating(id)) {
                sum += rater.getRating(id);
                count++;
            }
        }
        if (count >= minimalRaters) {
            return sum / count;
        } else {
            return 0.0;
        }
    }

    public HashMap<String, ArrayList<EfficientRater>> movieRaterThan(int minRaters) {
        HashMap<String, ArrayList<EfficientRater>> map = new HashMap<String, ArrayList<EfficientRater>>();
        for (String movie : this.movieRaterMap.keySet()) {
            ArrayList<EfficientRater> raters = this.movieRaterMap.get(movie);
            if (raters.size() >= minRaters) {
                map.put(movie, raters);
            }
        }
        return map;
    }

    private void buildMovieRaterMap() {
        ArrayList<String> movieIDs = MovieDatabase.filterBy(new TrueFilter());
        for (String id : movieIDs) {
            for (EfficientRater rater : myRaters) {
                if (!rater.hasRating(id)) {
                    continue;
                }
                ArrayList<EfficientRater> raters = new ArrayList<EfficientRater>();
                if (this.movieRaterMap.containsKey(id)) {
                    raters = this.movieRaterMap.get(id);
                }
                raters.add(rater);
                this.movieRaterMap.put(id, raters);
            }
        }
    }
}