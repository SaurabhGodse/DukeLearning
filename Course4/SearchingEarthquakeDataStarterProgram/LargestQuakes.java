import java.util.*;
/**
 * Write a description of LargestQuakes here.
 * 
 * @author Saurabh Godse
 * @version (a version number or a date)
 */
public class LargestQuakes {
    
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());
        ArrayList<QuakeEntry> ans = getLargest(list, 50);
        for(QuakeEntry qe : ans){
            System.out.println(qe);
        }

        
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> data){
        int index = 0;
        double maxMagnitude = 0;
        for(int i = 0; i < data.size(); i++){
            QuakeEntry qe = data.get(i);
            double magnitude = qe.getMagnitude();
            if(magnitude > maxMagnitude){
                maxMagnitude = magnitude;
                index = i;
            }
        }
        //System.out.println("earthquake is at location " + index + " of magnitude " + maxMagnitude);
        return index;
    }
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        int min = Math.min(howMany, quakeData.size());
        for(int i = 0; i < min; i++){
            int maxindex = indexOfLargest(quakeData);
            answer.add(quakeData.get(maxindex));
            quakeData.remove(maxindex);
        }
        return answer;
    }

}
