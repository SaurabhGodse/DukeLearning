import java.util.*;
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author Saurabh Godse
 * @version (a version number or a date)
 */
public class MovieRunnerAverage {
    public void printAverageRatings(){
        SecondRatings secondRt = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        System.out.println(secondRt.getMovieSize());
        System.out.println(secondRt.getRaterSize());
        ArrayList<Rating> ratings = secondRt.getAverageRatings(12);
        System.out.println("size of ratings : " + ratings.size());
        Collections.sort(ratings);
        for(Rating r : ratings){
            System.out.println(r.getValue() + " " + secondRt.getTitle(r.getItem()));
        }
    } 
    
    public void getAverageRatingOneMovie(){
        SecondRatings secondRt = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        String id = secondRt.getID("Vacation");
        System.out.println(secondRt.avg(secondRt.getMovieRatings().get(id)));
    }

}
