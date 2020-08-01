import java.util.*;
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author Saurabh Godse
 * @version (a version number or a date)
 */
public class MovieRunnerSimilarRatings {
    private void printRating(String ratingfile, String moviefile, int minimalRaters, Filter f){
        FourthRatings fourthRt = new FourthRatings(ratingfile);
        //System.out.println(thirdRt.getRaterSize());
        MovieDatabase.initialize(moviefile);
        ArrayList<Rating> ratings = fourthRt.getAverageRatingsByFilter(minimalRaters, f);
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
        FourthRatings fourthRt = new FourthRatings(ratingfile);
        //System.out.println(thirdRt.getRaterSize());
        MovieDatabase.initialize(moviefile);
        ArrayList<Rating> ratings = fourthRt.getAverageRatings(minimalRaters);
        System.out.println("Found " + ratings.size() + " ratings");
        /*System.out.println(secondRt.getMovieSize());
        System.out.println(secondRt.getRaterSize());
        ArrayList<Rating> ratings = secondRt.getAverageRatings(3);*/
        /*Collections.sort(ratings);
        for(Rating r : ratings){
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
        }*/
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
    
    public void printSimilarRatings(){
        String ratingfile, moviefile, rater_id;
        ratingfile = "data/ratings.csv";
        moviefile = "ratedmoviesfull.csv";
        rater_id = "964";
        int minimalRaters = 15;
        int numSimilarRaters = 20;
        FourthRatings fourthRt = new FourthRatings(ratingfile);
        MovieDatabase.initialize(moviefile);
        ArrayList<Rating> list = fourthRt.getSimilarRatings(rater_id, numSimilarRaters, minimalRaters);
        System.out.println("Found " + list.size() + " movies");
        Collections.sort(list, Collections.reverseOrder());
        for(Rating r : list){
            System.out.println(MovieDatabase.getMovie(r.getItem()) + " : " + r.getValue());
        }
    }
    
    private void printSimilarRatingByFilter(String ratingfile, String moviefile, String rater_id, int minimalRaters, int numSimilarRaters, Filter f){
        FourthRatings fourthRt = new FourthRatings(ratingfile);
        MovieDatabase.initialize(moviefile);
        ArrayList<Rating> list = fourthRt.getSimilarRatingsByFilter(rater_id, numSimilarRaters, minimalRaters, f);
        System.out.println("Found " + list.size() + " movies");
        Collections.sort(list, Collections.reverseOrder());
        for(Rating r : list){
            System.out.println(MovieDatabase.getMovie(r.getItem()) + " : " + r.getValue());
        }
    }
    
    public void printSimilarRatingsByGenre(){
        String ratingfile, moviefile, rater_id;
        ratingfile = "data/ratings.csv";
        moviefile = "ratedmoviesfull.csv";
        rater_id = "964";
        int minimalRaters = 15;
        int numSimilarRaters = 20;
        GenreFilter gf = new GenreFilter("Mystery");
        printSimilarRatingByFilter(ratingfile, moviefile, rater_id, minimalRaters, numSimilarRaters, gf);
    }
    
    public void printSimilarRatingsByDirector(){
        String ratingfile, moviefile, rater_id;
        ratingfile = "data/ratings.csv";
        moviefile = "ratedmoviesfull.csv";
        rater_id = "120";
        int minimalRaters = 2;
        int numSimilarRaters = 10;
        DirectorsFilter df = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        printSimilarRatingByFilter(ratingfile, moviefile, rater_id, minimalRaters, numSimilarRaters, df);
    }
    
    public void printSimilarRatingsByGenreAndMinutes(){
        String ratingfile, moviefile, rater_id;
        ratingfile = "data/ratings.csv";
        moviefile = "ratedmoviesfull.csv";
        rater_id = "168";
        int minimalRaters = 3;
        int numSimilarRaters = 10;
        AllFilters af = new AllFilters();
        GenreFilter gf = new GenreFilter("Drama");
        MinutesFilter mf = new MinutesFilter(80, 160);
        af.addFilter(gf);
        af.addFilter(mf);
        printSimilarRatingByFilter(ratingfile, moviefile, rater_id, minimalRaters, numSimilarRaters, af);
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes(){
        String ratingfile, moviefile, rater_id;
        ratingfile = "data/ratings.csv";
        moviefile = "ratedmoviesfull.csv";
        rater_id = "314";
        int minimalRaters = 5;
        int numSimilarRaters = 10;
        AllFilters af = new AllFilters();
        YearAfterFilter yaf = new YearAfterFilter(1975);
        MinutesFilter mf = new MinutesFilter(70, 200);
        af.addFilter(yaf);
        af.addFilter(mf);
        printSimilarRatingByFilter(ratingfile, moviefile, rater_id, minimalRaters, numSimilarRaters, af);
    }
    
   

}
