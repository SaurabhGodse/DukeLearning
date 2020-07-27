import java.util.*;
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author Saurabh Godse
 * @version (a version number or a date)
 */
public class MovieRunnerAverage {
    public void printAverageRatings(){
        SecondRatings secondRt = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
        System.out.println(secondRt.getMovieSize());
        System.out.println(secondRt.getRaterSize());
        ArrayList<Rating> ratings = secondRt.getAverageRatings(3);
        Collections.sort(ratings);
        for(Rating r : ratings){
            System.out.println(r.getValue() + " " + secondRt.getTitle(r.getItem()));
        }
    } 
    
    public void getAverageRatingOneMovie(){
        SecondRatings secondRt = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
        String id = secondRt.getID("The Godfather");
        System.out.println(secondRt.avg(secondRt.getMovieRatings().get(id)));
    }

}
