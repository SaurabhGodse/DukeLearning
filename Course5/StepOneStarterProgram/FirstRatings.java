import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
/**
 * Write a description of FirstRatings here.
 * 
 * @author Saurabh Godse
 * @version (a version number or a date)
 */
public class FirstRatings {
    private HashMap<String, ArrayList<String>> dirToMovie;
    private ArrayList<Movie> movies;
    private HashMap<String, ArrayList<Rater>> raters;
    private HashMap<String, Integer> movieRaters;
    private HashMap<String, ArrayList<Double>> movieRatings;
    
    public FirstRatings(){
        dirToMovie = new HashMap<String, ArrayList<String>>();
        movies = new ArrayList<Movie>();
        raters = new HashMap<String, ArrayList<Rater>>();
        movieRaters = new HashMap<String, Integer>();
        movieRatings = new HashMap<String, ArrayList<Double>>();
    }
    
    public HashMap<String, ArrayList<Double>> getMovieRatings(){
        return movieRatings;
    }
    public ArrayList<Movie> loadMovies(String filename){
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        
        for(CSVRecord record : parser){
            String id = record.get("id");
            String title = record.get("title");
            String year = record.get("year");
            String country = record.get("country");
            String genre = record.get("genre");
            String director = record.get("director");
            int minutes = Integer.parseInt(record.get("minutes"));
            String poster = record.get("poster");
            Movie movie = new Movie(id, title, year, genre, director, country, poster, minutes);
            movies.add(movie);
        }
        buildMap();
        return movies;
    }
    
    private void buildMap(){
        for(Movie movie : movies){
            String[] directors = movie.getDirector().split(",");
            String title = movie.getTitle();
            for(String director : directors){
                director = director.trim();
                if(dirToMovie.containsKey(director)){
                    dirToMovie.get(director).add(title);
                }
                else{
                    ArrayList<String> temp = new ArrayList<String>();
                    temp.add(title);
                    dirToMovie.put(director, temp);
                }
            }
        }
    }
    
    private int countComedy(ArrayList<Movie> movies){
        int count = 0;
        for(Movie movie : movies){
            String genre = movie.getGenres();
            if(genre.contains("Comedy"))
                count++;
        }
        return count;
    }
    
    private int countLengthyMovies(ArrayList<Movie> movies, int length){
        int count = 0;
        for(Movie movie : movies){
            int minutes = movie.getMinutes();
            if(minutes > length)
                count++;
        }
        return count;
    }
    
    private int maxDirectedMovieCount(){
        int maxcount = 0;
        for(String director : dirToMovie.keySet()){
            int temp = dirToMovie.get(director).size();
            if(temp > maxcount){
                maxcount = temp;
            }
        }
        return maxcount;
    }
    
    private ArrayList<String> maxDirectedDirectors(int maxcount){
        ArrayList<String> directors = new ArrayList<String>();
        for(String director : dirToMovie.keySet()){
            if(maxcount == dirToMovie.get(director).size())
                directors.add(director);
        }
        return directors;
    }
    
    public void testLoadMovies(){
        loadMovies("data/ratedmoviesfull.csv");
        //System.out.println("Number of movies : " + movies.size());
        /*for(Movie movie : movies){
            System.out.println(movie);
        }*/
        //System.out.println("Comedy movie count : " + countComedy(movies));
        //System.out.println("Lengthy movie count : " + countLengthyMovies(movies, 150));
        int maxcount = maxDirectedMovieCount();
        System.out.println("Maximum movies by any director : " + maxcount);
        System.out.println("Directors that directed that many movies : " + maxDirectedDirectors(maxcount));
        
    }
    
    public HashMap<String, ArrayList<Rater>> loadRaters(String filename){
        //System.out.println("Calling loadrates...\n");
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord record : parser){
            String rater_id = record.get("rater_id");
            String movie_id = record.get("movie_id");
            double rating = Double.parseDouble(record.get("rating"));
            Rater rater = new Rater(rater_id);
            rater.addRating(movie_id, rating);
            
            if(raters.containsKey(rater_id)){
                //System.out.println("Inside if \n");
                raters.get(rater_id).add(rater);
            }
            else{
                //System.out.println("Inside else \n");
                ArrayList<Rater> r = new ArrayList<Rater>();
                r.add(rater);
                raters.put(rater_id, r);
            }
            
            if(movieRaters.containsKey(movie_id)){
                movieRaters.put(movie_id, movieRaters.get(movie_id) + 1);
            }
            else{
                movieRaters.put(movie_id, 1);
            }
            
            if(movieRatings.containsKey(movie_id)){
                movieRatings.get(movie_id).add(rating);
            }
            else{
                ArrayList<Double> temp = new ArrayList<Double>();
                temp.add(rating);
                movieRatings.put(movie_id, temp);
            }
            //System.out.println(raters);
            //System.out.println("\n");
        }
        return raters;
    }
    
    private void getNoOfRatings(String rater_id){
        if(raters.containsKey(rater_id))
            System.out.println("Number of ratings by " + rater_id + " : " + raters.get(rater_id).size());
        else
            System.out.println("No rater found");
    }
    
    private int maxRatingCount(){
        int maxcount = 0;
        for(String rater_id : raters.keySet()){
            int temp = raters.get(rater_id).size();
            if(temp > maxcount)
                maxcount = temp;
        }
        return maxcount;
    }
    
    private void ratersWithMaxRating(int maxcount){
        ArrayList<String> list = new ArrayList<String>();
        for(String rater_id : raters.keySet()){
            if(maxcount == raters.get(rater_id).size())
                list.add(rater_id);
        }
        System.out.println("Raters with max ratings : " + list);
    }
    
    private void getNoOfMovieRatings(String movie_id){
        if(movieRaters.containsKey(movie_id)){
            System.out.println(movie_id + " is rated by : " + movieRaters.get(movie_id) + " raters");
        }
        else{
            System.out.println("No such movie found");
        }
        
    }
    public void testLoadRaters(){
        loadRaters("data/ratings.csv");
        System.out.println("Total number of raters : " + raters.size());
        getNoOfRatings("193");
        
        //System.out.println(raters);
        int maxcount = maxRatingCount();
        System.out.println("Maximum number of ratings : " + maxcount);
        ratersWithMaxRating(maxcount);
        getNoOfMovieRatings("1798709");
        System.out.println("Number of movies rated by raters : " + movieRaters.size());
    }
}
