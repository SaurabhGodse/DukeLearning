import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            if(qe.getMagnitude() > magMin){
                answer.add(qe);
            }
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            Location loc = qe.getLocation();
            double dist = from.distanceTo(loc);
            //System.out.println("Distance between " + loc.toString() + " and " + from.toString() + " is : " + dist);
            if(dist < distMax){
                answer.add(qe);
            }
        }

        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> answer = filterByMagnitude(list, 5.0);
        for(QuakeEntry qe : answer){
            System.out.println(qe);
        }
        System.out.println("Found " + answer.size() + " quakes that matches criteria");

    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        // This location is Durham, NC
        //Location cityDurham = new Location(35.988, -78.907);
        //ArrayList<QuakeEntry> distFromDurham = filterByDistanceFrom(list, 1000, cityDurham);
        // This location is Bridgeport, CA
        Location cityBridgeport = new Location(38.17, -118.82);
        ArrayList<QuakeEntry> distFromBridgeport = filterByDistanceFrom(list, 1000000, cityBridgeport);

        for(QuakeEntry qe : distFromBridgeport){
            Location loc = qe.getLocation();
            System.out.println(cityBridgeport.distanceTo(loc) + qe.getInfo());
        }
        System.out.println("Found " + distFromBridgeport.size() + " quakes that match criteria");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDep, double maxDep){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            double dep = qe.getDepth();
            if(minDep < dep && dep < maxDep){
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        ArrayList<QuakeEntry> ans = filterByDepth(list, -4000.0, -2000.0);
        for(QuakeEntry qe : ans){
            System.out.println(qe);
        }
        System.out.println("Found " + ans.size() + " quakes that match criteria");
        
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            if(where.equals("start") && qe.getInfo().startsWith(phrase)){
                answer.add(qe);
            }
            else if(where.equals("end") && qe.getInfo().endsWith(phrase)){
                answer.add(qe);
            }
            else if(where.equals("any") && qe.getInfo().contains(phrase)){
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public void quakesByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        ArrayList<QuakeEntry> ans = filterByPhrase(list, "any", "Can");
        for(QuakeEntry qe : ans){
            System.out.println(qe);
        }
        System.out.println("Found " + ans.size() + " quakes that match criteria");
        
        
    }
    
}
