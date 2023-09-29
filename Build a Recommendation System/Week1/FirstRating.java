import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.processing.FilerException;

import org.apache.commons.csv.CSVRecord;

import edu.duke.FileResource;

public class FirstRating {
    /*
     * Process every record from the CSV file, a file of movie information, and
     * return an ArrayList of type Movie.
     */
    ArrayList<Movie> loadMovies(FileResource fr) {
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        for (CSVRecord rec : fr.getCSVParser()) {
            String id = rec.get("id");
            String title = rec.get("title");
            String year = rec.get("year");
            String country = rec.get("country");
            String genres = rec.get("genre");
            String director = rec.get("director");
            int minutes = Integer.parseInt(rec.get("minutes"));
            String poster = rec.get("poster");
            Movie movie = new Movie(id, title, year, genres, director, country, poster, minutes);
            movieList.add(movie);
        }
        System.out.println("Loaded " + movieList.size() + " movies.");
        return movieList;
    }

    /*
     * Process every record from the CSV file, a file of raters and their
     * ratings, and return an ArrayList of type Rater .
     */
    ArrayList<Rater> loadRaters(FileResource fr) {
        ArrayList<Rater> raterList = new ArrayList<Rater>();
        for (CSVRecord rec : fr.getCSVParser()) {
            String rid = rec.get("rater_id");
            // for 'Rating' class
            String mid = rec.get("movie_id");
            String rating = rec.get("rating");
            Rater findRater = null;
            for (Rater rater : raterList) {
                if (rid.equals(rater.getID())) {
                    findRater = rater;
                    break;
                }
            }
            if (null == findRater) {
                Rater newRater = new Rater(rid);
                newRater.addRating(mid, Double.parseDouble(rating));
                raterList.add(newRater);
            } else {
                findRater.addRating(mid, Double.parseDouble(rating));
            }
        }

        System.out.println("Loaded " + raterList.size() + " raters.");
        return raterList;
    }

    void testLoadMovies() {
        FileResource fr = new FileResource();
        ArrayList<Movie> movieList = this.loadMovies(fr);
        /*
         * for (Movie movie : movieList) {
         * System.out.println(movie);
         * }
         */

        // How many movies include 'Comedy' genre
        int count = 0;
        for (Movie movie : movieList) {
            if (movie.getGenres().contains("Comedy")) {
                count++;
            }
        }
        System.out.println("Movies in the Comedy genre: " + count);

        // How many movies great than 150 minutes
        count = 0;
        for (Movie movie : movieList) {
            if (movie.getMinutes() > 150) {
                count++;
            }
        }
        System.out.println("Movies great than 150 minutes: " + count);

        // Maximum number of movies by any director, and who the directors are
        HashMap<String, ArrayList<String>> directorMoviesMap = new HashMap<String, ArrayList<String>>();
        for (Movie movie : movieList) {
            String[] directors = movie.getDirector().split(",");
            for (String director : directors) {
                if (directorMoviesMap.containsKey(director)) {
                    ArrayList<String> movies = directorMoviesMap.get(director);
                    movies.add(movie.getTitle());
                    // directorMoviesMap.put(director, movies);
                } else {
                    ArrayList<String> movies = new ArrayList<String>();
                    movies.add(movie.getTitle());
                    directorMoviesMap.put(director, movies);
                }
            }
        }

        int max = 0;
        for (String director : directorMoviesMap.keySet()) {
            ArrayList<String> movies = directorMoviesMap.get(director);
            if (max < movies.size()) {
                max = movies.size();
            }
        }
        for (String director : directorMoviesMap.keySet()) {
            ArrayList<String> movies = directorMoviesMap.get(director);
            if (max == movies.size()) {
                System.out.println("Director has maximum number of movies: " + director + " - " + movies.size());
            }
        }
    }

    void testLoadRater() {
        FileResource fr = new FileResource();
        ArrayList<Rater> raterList = this.loadRaters(fr);
        /*
         * for (Rater rater : raterList) {
         * System.out.println(rater);
         * }
         */

        // Find the number of ratings for a particular 'rater_id'
        String rid = "2";
        for (Rater rater : raterList) {
            if (rid.equals(rater.getID())) {
                System.out.println("Rater-" + rater.getID() + " has " + rater.numRatings() + " ratings.");
            }
        }

        // Find the maximum number of ratings by any rater
        int max = 0;
        for (Rater rater : raterList) {
            if (max < rater.numRatings()) {
                max = rater.numRatings();
            }
        }
        for (Rater rater : raterList) {
            if (max == rater.numRatings()) {
                System.out.println(
                        "Maximum number of ratings is made by Rater-" + rater.getID() + " - " + rater.numRatings());
            }
        }

        // Find the number of ratings a particular movie has
        String mid = "1798709";
        for (Rater rater : raterList) {
            if (mid.equals(rater.getID())) {
                System.out.println();
            }
        }
    }

    void test() {
        this.testLoadRater();
    }
}
