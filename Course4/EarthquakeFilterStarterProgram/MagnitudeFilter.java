
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author Saurabh Godse
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter{
    private double minMag;
    private double maxMag;
    
    public MagnitudeFilter(double a, double b){
        minMag = a;
        maxMag = b;
    }
    
    public boolean satisfies(QuakeEntry qe){
        return minMag <= qe.getMagnitude() && qe.getMagnitude() <= maxMag;
    }

}
