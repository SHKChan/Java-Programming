import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerAverage {
    private SecondRatings sr;

    public MovieRunnerAverage(String moviefile, String ratingsfile) {
        this.sr = new SecondRatings(moviefile, ratingsfile);
    }

    public MovieRunnerAverage() {
        this("data\\ratedmoviesfull.csv", "data\\ratings.csv");
        // this("data\\ratedmovies_short.csv", "data\\ratings_short.csv");
    }

    public void printAverageRatings(int minimal) {
        ArrayList<Rating> ratings = this.sr.getAverageRatings(minimal);
        for (Rating rating : ratings) {
            String title = this.sr.getTitleByID(rating.getItem());
            if (null != title) {
                System.out.println(rating.getValue() + " " + title);
            } else {
                System.out.println(rating.getValue() + " Title not found for ID " + rating.getItem());
            }
        }
        Collections.sort(ratings);
        System.out.println("The movie has the lowest average rating : "
                + this.sr.getTitleByID(ratings.get(ratings.size() - 1).getItem()));
    }

    public void getAverageRatingOneMovie(int minimal) {
        String title = "Vacation";
        String id = this.sr.getIDByTitle(title);
        if (null == id) {
            System.out.println("Movie not found.");
            return;
        }
        double average = this.sr.getAverageByID(id, minimal);
        System.out.println(title + " has an average rating of " + average);
    }

    public void test() {
        this.printAverageRatings(12);
        // this.getAverageRatingOneMovie(20);

        /*
         * int minimalRaters = 20;
         * HashMap<String, ArrayList<Rater>> map =
         * this.sr.movieRaterThan(minimalRaters);
         * System.out.println("Movies with more than " + minimalRaters + " ratings: " +
         * map.size());
         * // Find minimum number of ratings
         * String minimalID = "";
         * int minimal = Integer.MAX_VALUE;
         * for (String movie : map.keySet()) {
         * if (minimal > map.get(movie).size()) {
         * minimal = map.get(movie).size();
         * minimalID = movie;
         * }
         * }
         * System.out.println("Movie with minimum number of ratings: " +
         * this.sr.getTitleByID(minimalID));
         */
    }
}
