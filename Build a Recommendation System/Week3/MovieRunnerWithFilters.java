import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {
    private ThirdRatings tr;

    public MovieRunnerWithFilters(String moviesfile, String ratingsfile) {
        this.tr = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviesfile);
        // System.out.println("Loaded " + this.tr.getRaterSize() + " raters.");
        // System.out.println("Loaded " + MovieDatabase.size() + " movies.");
    }

    public MovieRunnerWithFilters() {
        this.tr = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        // System.out.println("Loaded " + this.tr.getRaterSize() + " movies.");
        // System.out.println("Loaded " + MovieDatabase.size() + " movies.");
    }

    public void printAverageRatings(int minimal) {
        ArrayList<Rating> ratings = this.tr.getAverageRatings(minimal);
        for (Rating rating : ratings) {
            String title = MovieDatabase.getTitle(rating.getItem());
            if (null != title) {
                System.out.println(rating.getValue() + " " + title);
            } else {
                System.out.println(rating.getValue() + " Title not found for ID " + rating.getItem());
            }
        }
        Collections.sort(ratings);
        System.out.println("The movie has the lowest average rating : "
                + MovieDatabase.getTitle(ratings.get(ratings.size() - 1).getItem()));
    }

    public void printAverageRatingsByYear(int minimal, int year) {
        ArrayList<Rating> ratings = this.tr.getAverageRatingsByFilter(minimal, new YearAfterFilter(year));
        for (Rating rating : ratings) {
            String title = MovieDatabase.getTitle(rating.getItem());
            if (null != title) {
                System.out.println(rating.getValue() + " " + title);
            } else {
                System.out.println(rating.getValue() + " Title not found for ID " + rating.getItem());
            }
        }
    }

    public void printAverageRatingsByGenre(int minimal, String gerne) {
        ArrayList<Rating> ratings = this.tr.getAverageRatingsByFilter(minimal, new GenreFilter(gerne));
        for (Rating rating : ratings) {
            String genres = MovieDatabase.getGenres(rating.getItem());
            String title = MovieDatabase.getTitle(rating.getItem());
            System.out.println(rating.getValue() + " " + title);
            System.out.println("\t" + genres);
        }
    }

    public void printAverageRatingsByMinutes(int minimal, int min, int max) {
        ArrayList<Rating> ratings = this.tr.getAverageRatingsByFilter(minimal, new MinutesFilter(min, max));
        for (Rating rating : ratings) {
            int time = MovieDatabase.getMinutes(rating.getItem());
            String title = MovieDatabase.getTitle(rating.getItem());
            System.out.println(rating.getValue() + " Time: " + time + " Title: " + title);
        }
    }

    public void printAverageRatingsByDirectors(int minimal, String directors) {
        ArrayList<Rating> ratings = this.tr.getAverageRatingsByFilter(minimal, new DirectorsFilter(directors));
        for (Rating rating : ratings) {
            String drts = MovieDatabase.getDirector(rating.getItem());
            String title = MovieDatabase.getTitle(rating.getItem());
            System.out.println(rating.getValue() + " " + title);
            System.out.println("\t" + drts);
        }
    }

    public void printAverageRatingsByYearAfterAndGenre(int minimal, int year, String genre) {
        AllFilters f = new AllFilters();
        f.addFilter(new YearAfterFilter(year));
        f.addFilter(new GenreFilter(genre));
        ArrayList<Rating> ratings = this.tr.getAverageRatingsByFilter(minimal, f);
        for (Rating rating : ratings) {
            String gernes = MovieDatabase.getGenres(rating.getItem());
            int y = MovieDatabase.getYear(rating.getItem());
            String title = MovieDatabase.getTitle(rating.getItem());
            System.out.println(rating.getValue() + " " + y + " " + title);
            System.out.println("\t" + gernes);
        }
    }

    public void printAverageRatingsByDirectorsAndMinutes(int minimal, String directors, int min, int max) {
        AllFilters f = new AllFilters();
        f.addFilter(new DirectorsFilter(directors));
        f.addFilter(new MinutesFilter(min, max));
        ArrayList<Rating> ratings = this.tr.getAverageRatingsByFilter(minimal, f);
        for (Rating rating : ratings) {
            String drts = MovieDatabase.getDirector(rating.getItem());
            int time = MovieDatabase.getMinutes(rating.getItem());
            String title = MovieDatabase.getTitle(rating.getItem());
            System.out.println(rating.getValue() + " Time: " + time + " " + title);
            System.out.println("\t" + drts);
        }
    }

    public void test() {
        // this.printAverageRatings(35);
        // this.printAverageRatingsByYear(20, 2000);
        // this.printAverageRatingsByGenre(20, "Comedy");
        // this.printAverageRatingsByMinutes(5, 105, 135);
        this.printAverageRatingsByDirectors(4,
                "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        // this.printAverageRatingsByYearAfterAndGenre(8, 1990, "Drama");
        /* this.printAverageRatingsByDirectorsAndMinutes(3,
                "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack",
                90, 180); */
    }
}
