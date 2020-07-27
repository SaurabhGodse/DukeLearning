import java.util.*;
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter{
    private String[] directors;
    public DirectorsFilter(String d){
        directors = d.split(",");
    }
    public boolean satisfies(String id){
        String movie_directors = MovieDatabase.getDirector(id);
        for(String director : directors){
            if(movie_directors.contains(director))
                return true;
        }
        return false;
    }

}
