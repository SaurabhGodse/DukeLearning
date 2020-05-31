import java.util.*;
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes {
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<Double> dist = new ArrayList<Double>();
        HashMap<Double, QuakeEntry> mapDist = new HashMap<Double, QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            Location loc = qe.getLocation();
            double d = current.distanceTo(loc);
            dist.add(d);
            mapDist.put(d, qe);
        }
        Collections.sort(dist);
        int count = 0;
        int min = Math.min(quakeData.size(), howMany);
        if(min != 0){
            for(double d : dist){
                ret.add(mapDist.get(d));
                count++;
                if(count == min)
                    break;
            }
        }
        return ret;
    
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list,jakarta,3);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
    
}
