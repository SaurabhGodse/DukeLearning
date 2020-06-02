import java.util.*;
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author Saurabh Godse
 * @version (a version number or a date)
 */
public class MatchAllFilter implements Filter{
    ArrayList<Filter> filters;
    public MatchAllFilter(){
        filters = new ArrayList<Filter>();
    }
    public void addFilter(Filter f){
        filters.add(f);
    }
    
    public boolean satisfies(QuakeEntry qe){
        for(Filter f : filters){
            if(!f.satisfies(qe)){
                return false;
            }
        }
        return true;
    }

}
