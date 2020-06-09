
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakeData){
        int n = quakeData.size();
        for(int i = 0; i < n - 1; i++){
            if(quakeData.get(i).getMagnitude() > quakeData.get(i + 1).getMagnitude()){
                return false;
            }
        }
        return true;
    }
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted){
        int n = quakeData.size() - numSorted;
        for(int i = 0; i < n - 1; i++){
            QuakeEntry a = quakeData.get(i);
            QuakeEntry b = quakeData.get(i + 1);
            if(a.getMagnitude() > b.getMagnitude()){
                quakeData.set(i, b);
                quakeData.set(i + 1, a);
            }
        }
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> quakeData){
        int n = quakeData.size();
        for(int i = 0; i < n - 1; i++){
            onePassBubbleSort(quakeData, i);
        }
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> quakeData){
        int n = quakeData.size();
        int i;
        for(i = 0; i < n - 1; i++){
            if(checkInSortedOrder(quakeData))
                break;
            onePassBubbleSort(quakeData, i);
        }
        System.out.println("number of passes : " + i);
    }
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from){
        int maxindex = from;
        QuakeEntry largest = quakeData.get(from);
        for(int i = from + 1; i < quakeData.size(); i++){
            if(largest.getDepth() < quakeData.get(i).getDepth()){
                largest = quakeData.get(i);
                maxindex = i;
            }
        }
        return maxindex;
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> quakeData){
        for(int i = 0; i < 50; i++){
            int max = getLargestDepth(quakeData, i);
            QuakeEntry largest = quakeData.get(max);
            QuakeEntry current = quakeData.get(i);
            quakeData.set(i, largest);
            quakeData.set(max, current);
        }
        
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in){
        int i;
        for(i  = 0; i < in.size(); i++){
            if(checkInSortedOrder(in))
                break;
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            
        }
        System.out.println("number of passes : " + i);
    }
    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        //sortByMagnitudeWithBubbleSort(list);
        //sortByMagnitudeWithCheck(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
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
}
