import java.util.*;
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter{
    private int min_minutes, max_minutes;
    
    public MinutesFilter(int min, int max){
        min_minutes = min;
        max_minutes = max;
    }
    public boolean satisfies(String id){
        int len = MovieDatabase.getMinutes(id);
        return min_minutes <= len && len <= max_minutes;
    }

}
