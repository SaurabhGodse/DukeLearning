import java.util.*;
/**
 * Write a description of ThirdRatings here.
 * 
 * @author Saurabh Godse
 * @version (a version number or a date)
 */
public class ThirdRatings {
    private HashMap<String, ArrayList<Rater>> myRaters;
    private HashMap<String, ArrayList<Double>> movieRatings;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile){
        FirstRatings firstRt = new FirstRatings();
        //myMovies = firstRt.loadMovies(moviefile);
        myRaters = firstRt.loadRaters(ratingsfile);
        movieRatings = firstRt.getMovieRatings();
    }
    
    public HashMap<String, ArrayList<Double>> getMovieRatings(){
        return movieRatings;
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
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> avgRaters = new ArrayList<Rating>();
        for(String movie_id : movies){
            double rating = getAverageByID(movie_id, minimalRaters);
            if(rating != 0.0){
                Rating r = new Rating(movie_id, rating);
                avgRaters.add(r);
            }
        }
        return avgRaters;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> avgRaters = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for(String movie_id : movies){
            double rating = getAverageByID(movie_id, minimalRaters);
            if(rating != 0.0){
                Rating r = new Rating(movie_id, rating);
                avgRaters.add(r);
            }
        }
        return avgRaters;
    }
    
}
