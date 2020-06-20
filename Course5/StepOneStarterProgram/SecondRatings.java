
/**
 * Write a description of SecondRatings here.
 * 
 * @author Saurabh Godse 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private HashMap<String, ArrayList<Rater>> myRaters;
    private HashMap<String, ArrayList<Double>> movieRatings;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile){
        FirstRatings firstRt = new FirstRatings();
        myMovies = firstRt.loadMovies(moviefile);
        myRaters = firstRt.loadRaters(ratingsfile);
        movieRatings = firstRt.getMovieRatings();
    }
    
    public HashMap<String, ArrayList<Double>> getMovieRatings(){
        return movieRatings;
    }   
    
    public int getMovieSize(){
        return myMovies.size();
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    public double avg(ArrayList<Double> list){
        double sum = 0;
        for(double d : list){
            sum += d;
        }
        return sum / list.size();
    }
    private double getAverageByID(String id, int minimalRaters){
        if(movieRatings.containsKey(id) && movieRatings.get(id).size() >= minimalRaters){
            return avg(movieRatings.get(id));
        }
        return 0.0;
        
    }
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> avgRaters = new ArrayList<Rating>();
        for(String movie_id : movieRatings.keySet()){
            double rating = getAverageByID(movie_id, minimalRaters);
            if(rating != 0.0){
                Rating r = new Rating(movie_id, rating);
                avgRaters.add(r);
            }
        }
        return avgRaters;
    }
    
    public String getTitle(String id){
        for(Movie m : myMovies){
            if(m.getID().equals(id))
                return m.getTitle();
        }
        return "ID not found";
    }
    
    public String getID(String title){
        for(Movie m : myMovies){
            if(m.getTitle().equals(title))
                return m.getID();
        }
        return "NO SUCH TITLE";
    }
}