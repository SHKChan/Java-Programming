
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.FileResource;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    private HashMap<String, ArrayList<Rater>> movieRaterMap;

    public SecondRatings() {
        // default constructor
        this("data\\ratedmoviesfull.csv", "data\\ratings.csv");
    }

    public SecondRatings(String moviefile, String ratingsfile) {
        FileResource moviefileResource = new FileResource(moviefile);
        FileResource ratingsfileResource = new FileResource(ratingsfile);
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefileResource);
        myRaters = fr.loadRaters(ratingsfileResource);
        this.movieRaterMap = new HashMap<String, ArrayList<Rater>>();
        this.buildMovieRaterMap();
    }

    public int getMovieSize() {
        return myMovies.size();
    }

    public int getRaterSize() {
        return myRaters.size();
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        for (Movie movie : myMovies) {
            double average = this.getAverageByID(movie.getID(), minimalRaters);
            if (0.0 != average) {
                ratings.add(new Rating(movie.getID(), average));
            }
        }
        return ratings;
    }

    public double getAverageByID(String id, int minimalRaters) {
        double sum = 0.0;
        int count = 0;
        for (Rater rater : this.myRaters) {
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

    public String getTitleByID(String id) {
        for (Movie movie : myMovies) {
            if (movie.getID().equals(id)) {
                return movie.getTitle();
            }
        }
        return null;
    }

    public String getIDByTitle(String title) {
        for (Movie movie : myMovies) {
            if (movie.getTitle().equals(title)) {
                return movie.getID();
            }
        }
        return null;
    }

    public HashMap<String, ArrayList<Rater>> movieRaterThan(int minRaters) {
        HashMap<String, ArrayList<Rater>> map = new HashMap<String, ArrayList<Rater>>();
        for (String movie : this.movieRaterMap.keySet()) {
            ArrayList<Rater> raters = this.movieRaterMap.get(movie);
            if (raters.size() >= minRaters) {
                map.put(movie, raters);
            }
        }
        return map;
    }

    private void buildMovieRaterMap() {
        for (Movie movie : myMovies) {
            for (Rater rater : myRaters) {
                if (!rater.hasRating(movie.getID())) {
                    continue;
                }
                ArrayList<Rater> raters = new ArrayList<Rater>();
                if (this.movieRaterMap.containsKey(movie.getID())) {
                    raters = this.movieRaterMap.get(movie.getID());
                }
                raters.add(rater);
                this.movieRaterMap.put(movie.getID(), raters);
            }
        }
    }
}