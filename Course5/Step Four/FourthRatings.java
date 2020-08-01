import java.util.*;

/**
 * Write a description of FourthRatings here.
 * 
 * @author Saurabh Godse
 * @version (a version number or a date)
 */
public class FourthRatings {
    private HashMap<String, ArrayList<Double>> movieRatings;
    
    public FourthRatings(String ratingsfile){
        FirstRatings firstRt = new FirstRatings();
        RaterDatabase.initialize(ratingsfile);
        firstRt.loadRaters(ratingsfile);
        movieRatings = firstRt.getMovieRatings();
    }
    
    private double dotProduct(Rater me, Rater r){
        double similarity = 0;
        for(String movie_id : movieRatings.keySet()){
            //System.out.println(movie_id);
            if(me.hasRating(movie_id) && r.hasRating(movie_id)){
                similarity += (me.getRating(movie_id) - 5) * (r.getRating(movie_id) - 5);
            }
        }
        return similarity;
    }
    
    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> rating = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        
        for(Rater r : RaterDatabase.getRaters()){
            if(!r.getID().equals(id)){
                double similarity = dotProduct(me, r);
                //System.out.println(r.getID() + " : " + similarity);
                if(similarity > 0){
                    rating.add(new Rating(r.getID(), similarity));
                    //System.out.println("Adding rating");
                }
            }
        }
        Collections.sort(rating, Collections.reverseOrder());
        return rating;
    }
    
    private int countRaters(String movie_id, ArrayList<Rating> similarRaters){
        int count = 0;
        for(Rating r : similarRaters){
            Rater currRater = RaterDatabase.getRater(r.getItem());
            if(currRater.hasRating(movie_id)){
                count++;
            }
        }
        return count;
    }
    
    private ArrayList<Rating> getSimilarRaters(ArrayList<Rating> similarRaters, int numSimilarRaters){
        ArrayList<Rating> list = new ArrayList<Rating>();
        for(int i = 0; i < numSimilarRaters && i < similarRaters.size(); i++){
            list.add(similarRaters.get(i));
        }
        return list;
    }
    
    private double getAverageRatings(String movie_id, ArrayList<Rating> similarRaters){
        double sum = 0;
        int count = 0;
        for(Rating r : similarRaters){
            Rater currRater = RaterDatabase.getRater(r.getItem());
            if(currRater.hasRating(movie_id)){
                sum += r.getValue() * currRater.getRating(movie_id);
                count++;
            }
        }
        return sum / count;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        //System.out.println("Inside get similar ratings");
        ArrayList<Rating> similarRaters = getSimilarities(id);
        //System.out.println("Number of similar raters : " + similarRaters.size());
        similarRaters = getSimilarRaters(similarRaters, numSimilarRaters);
        //System.out.println("Number of similar raters : " + similarRaters.size());
        ArrayList<Rating> list = new ArrayList<Rating>();
        for(String movie_id : MovieDatabase.filterBy(new TrueFilter())){
            if(countRaters(movie_id, similarRaters) >= minimalRaters){
                double average = getAverageRatings(movie_id, similarRaters);
                list.add(new Rating(movie_id, average));
            }
        }
        return list;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria){
        //System.out.println("Inside get similar ratings");
        ArrayList<Rating> similarRaters = getSimilarities(id);
        //System.out.println("Number of similar raters : " + similarRaters.size());
        similarRaters = getSimilarRaters(similarRaters, numSimilarRaters);
        //System.out.println("Number of similar raters : " + similarRaters.size());
        ArrayList<Rating> list = new ArrayList<Rating>();
        for(String movie_id : MovieDatabase.filterBy(filterCriteria)){
            if(countRaters(movie_id, similarRaters) >= minimalRaters){
                double average = getAverageRatings(movie_id, similarRaters);
                list.add(new Rating(movie_id, average));
            }
        }
        return list;
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
