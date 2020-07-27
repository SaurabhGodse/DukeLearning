import java.util.*;
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author Saurabh Godse 
 * @version (a version number or a date)
 */
public class MovieRunnerWithFilters {
    private void printRating(String ratingfile, String moviefile, int minimalRaters, Filter f){
        ThirdRatings thirdRt = new ThirdRatings(ratingfile);
        //System.out.println(thirdRt.getRaterSize());
        MovieDatabase.initialize(moviefile);
        ArrayList<Rating> ratings = thirdRt.getAverageRatingsByFilter(minimalRaters, f);
        System.out.println("Found " + ratings.size() + " ratings");
        /*Collections.sort(ratings);
        for(Rating r : ratings){
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
        }*/
        
    }
    public void printAverageRatings(){
        //SecondRatings secondRt = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
        String ratingfile, moviefile;
        ratingfile = "data/ratings.csv";
        moviefile = "ratedmoviesfull.csv";
        int minimalRaters = 35;
        ThirdRatings thirdRt = new ThirdRatings(ratingfile);
        //System.out.println(thirdRt.getRaterSize());
        MovieDatabase.initialize(moviefile);
        ArrayList<Rating> ratings = thirdRt.getAverageRatings(minimalRaters);
        System.out.println("Found " + ratings.size() + " ratings");
        /*System.out.println(secondRt.getMovieSize());
        System.out.println(secondRt.getRaterSize());
        ArrayList<Rating> ratings = secondRt.getAverageRatings(3);*/
        /*Collections.sort(ratings);
        for(Rating r : ratings){
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
        }*/
    } 
    
    public void printAverageRatingsByYear(){
        String ratingfile, moviefile;
        ratingfile = "data/ratings.csv";
        moviefile = "ratedmoviesfull.csv";
        int minimalRaters = 20;
        YearAfterFilter yaf = new YearAfterFilter(2000);
        printRating(ratingfile, moviefile, minimalRaters, yaf);
    }
    
    public void printAverageRatingsByGenre(){
        String ratingfile, moviefile;
        ratingfile = "data/ratings.csv";
        moviefile = "ratedmoviesfull.csv";
        int minimalRaters = 20;
        GenreFilter gf = new GenreFilter("Comedy");
        printRating(ratingfile, moviefile, minimalRaters, gf);
    }
    
    public void printAverageRatingsByMinutes(){
        String ratingfile, moviefile;
        ratingfile = "data/ratings.csv";
        moviefile = "ratedmoviesfull.csv";
        int minimalRaters = 5;
        MinutesFilter mf = new MinutesFilter(105, 135);
        printRating(ratingfile, moviefile, minimalRaters, mf);
    }
    
    public void printAverageRatingsByDirectors(){
        String ratingfile, moviefile;
        ratingfile = "data/ratings.csv";
        moviefile = "ratedmoviesfull.csv";
        int minimalRaters = 4;
        DirectorsFilter df = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        printRating(ratingfile, moviefile, minimalRaters, df);
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        String ratingfile, moviefile;
        ratingfile = "data/ratings.csv";
        moviefile = "ratedmoviesfull.csv";
        int minimalRaters = 8;
        AllFilters af = new AllFilters();
        YearAfterFilter yaf = new YearAfterFilter(1990);
        GenreFilter gf = new GenreFilter("Drama");
        af.addFilter(yaf);
        af.addFilter(gf);
        printRating(ratingfile, moviefile, minimalRaters, af);
    }
    
    public void printAverageRatingsByDirectorsAndMinutes(){
        String ratingfile, moviefile;
        ratingfile = "data/ratings.csv";
        moviefile = "ratedmoviesfull.csv";
        int minimalRaters = 3;
        AllFilters af = new AllFilters();
        DirectorsFilter df = new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
        MinutesFilter mf = new MinutesFilter(90, 180);
        af.addFilter(df);
        af.addFilter(mf);
        printRating(ratingfile, moviefile, minimalRaters, af);
    }
}
