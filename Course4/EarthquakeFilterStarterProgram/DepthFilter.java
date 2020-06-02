
/**
 * Write a description of DepthFilter here.
 * 
 * @author Saurabh Godse 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter{
    private double minDep;
    private double maxDep;
    
    public DepthFilter(double a, double b){
        minDep = a;
        maxDep = b;
    }
    
    public boolean satisfies(QuakeEntry qe){
        return minDep <= qe.getDepth() && qe.getDepth() <= maxDep;
    }
    

}
