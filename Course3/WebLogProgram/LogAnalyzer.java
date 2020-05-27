import edu.duke.*;
import java.util.*;
import java.text.*;
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author Saurabh Godse
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for(String line : fr.lines()){
             records.add(WebLogParser.parseEntry(line));
            }
         
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs(){
         ArrayList<String> uniqueIps = new ArrayList<String>();
         for(LogEntry le : records){
             String ip = le.getIpAddress();
             if(!uniqueIps.contains(ip)){
                 uniqueIps.add(ip);
             }
             
         }
         return uniqueIps.size();
     }
     
     public void printAllHigherThanNum(int num){
         for(LogEntry le : records){
             if(le.getStatusCode() > num){
                 System.out.println(le);
             }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> uniqueIps = new ArrayList<String>();

         for(LogEntry le : records){
             Date d = le.getAccessTime();
             String ip = le.getIpAddress();
             //System.out.println(ip + " : " + d.toString());
             if(d.toString().contains(someday)){
                 //System.out.println("Day is today");
                 if(!uniqueIps.contains(ip)){
                     //System.out.println("About to add ip");
                     uniqueIps.add(ip);
                 }
                 
             }
         }
         return uniqueIps;
         
     }
     
     public int countUniqueIPsInRange(int low, int high){
         ArrayList<String> uniqueIps = new ArrayList<String>();
         for(LogEntry le : records){
             int sc = le.getStatusCode();
             String ip = le.getIpAddress();
             if(low <= sc && sc <= high){
                 if(!uniqueIps.contains(ip)){
                     uniqueIps.add(ip);
                 }
             }
             
         }
         return uniqueIps.size();
     }
     
     public HashMap<String, Integer> countVisitsPerIP(){
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         for(LogEntry le : records){
             String ip = le.getIpAddress();
             if(!counts.containsKey(ip)){
                 counts.put(ip, 1);
             }
             else{
                 counts.put(ip, counts.get(ip) + 1);
             }
         }
         return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> counts){
         int maxcount = 0;
         for(int val : counts.values()){
             if(val > maxcount){
                 maxcount = val;
             }
         }
         return maxcount;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts, int maxcount){
         ArrayList<String> mostVisitingIps = new ArrayList<String>();
         for(String ip : counts.keySet()){
             if(maxcount == counts.get(ip)){
                 mostVisitingIps.add(ip);
             }
             
         }
         return mostVisitingIps;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays(){
         HashMap<String, ArrayList<String>> mapIpForDay = new HashMap<String, ArrayList<String>>();
         for(LogEntry le : records){
             //System.out.println(le);
             Date d = le.getAccessTime();
             String ip = le.getIpAddress();
             String day = new SimpleDateFormat("MMM dd").format(d);
             //System.out.println(d + " : " + day);
             if(mapIpForDay.containsKey(day)){
                 mapIpForDay.get(day).add(ip);
             }
             else{
                 ArrayList<String> temp = new ArrayList<String>();
                 temp.add(ip);
                 mapIpForDay.put(day, temp);
                 
             }
         }
         return mapIpForDay;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> mapIpForDay){
         String maxVisitDay = "";
         int maxvisit = 0;
         for(String day : mapIpForDay.keySet()){
             int temp = mapIpForDay.get(day).size();
             if( temp > maxvisit){
                 maxvisit = temp;
                 maxVisitDay = day;
             }
         }
         return maxVisitDay;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> mapIpForDay, String day){
         if(mapIpForDay.containsKey(day)){
            ArrayList<String> ipsForDay = mapIpForDay.get(day);
            HashMap<String, Integer> ipfreq = new HashMap<String, Integer>();
            for(String ip : ipsForDay){
                if(ipfreq.containsKey(ip)){
                    ipfreq.put(ip, ipfreq.get(ip) + 1);
                }
                else{
                    ipfreq.put(ip, 1);
                }
            }
            int maxcount = mostNumberVisitsByIP(ipfreq);
            return iPsMostVisits(ipfreq, maxcount);
         }
         return null;
     }
}
