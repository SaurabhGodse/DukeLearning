import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        /*Filter f = new MinMagFilter(4.0); 
        ArrayList<QuakeEntry> m7  = filter(list, f); 
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        } */
        Filter magFilter = new MagnitudeFilter(3.5, 4.5);
        Filter depFilter = new DepthFilter(-55000.0, -20000.0);
        /*Filter distFilter = new DistanceFilter(new Location(39.7392, -104.9903), 1000000.0);
        Filter phraseFilter = new PhraseFilter("end", "a");*/
        ArrayList<QuakeEntry> m = filter(list, magFilter);
        ArrayList<QuakeEntry> m1 = filter(m, depFilter);
        for(QuakeEntry qe : m1){
            System.out.println(qe);
        }
        System.out.println(m1.size());
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }
    
    public void testMatchAllFilter(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        MatchAllFilter maf = new MatchAllFilter();
        Filter f1 = new MagnitudeFilter(1.0, 4.0);
        Filter f2 = new DepthFilter(-180000.0, -30000.0);
        Filter f3 = new PhraseFilter("any", "o");
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);
        for(QuakeEntry qe : list){
            if(maf.satisfies(qe)){
                answer.add(qe);
            }
        }
        for(QuakeEntry qe : answer){
            System.out.println(qe);
        }
        System.out.println("Found " + answer.size() + " quakes that satisfies the condition");
        
    }
    public void testMatchAllFilter2(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        MatchAllFilter maf = new MatchAllFilter();
        Filter f1 = new MagnitudeFilter(0.0, 5.0);
        Filter f2 = new DistanceFilter(new Location(55.7308, 9.1153), 3000000);
        Filter f3 = new PhraseFilter("any", "e");
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);
        for(QuakeEntry qe : list){
            if(maf.satisfies(qe)){
                answer.add(qe);
            }
        }
        for(QuakeEntry qe : answer){
            System.out.println(qe);
        }
        System.out.println("Found " + answer.size() + " quakes that satisfies the condition");
        
    }

}
