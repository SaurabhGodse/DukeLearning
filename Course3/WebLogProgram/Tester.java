
/**
 * Write a description of class Tester here.
 * 
 * @author Saurabh Godse
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        //la.printAll();
        //System.out.println("Unique Ip count : " + la.countUniqueIPs());
        //la.printAllHigherThanNum(400);
        //System.out.println(la.uniqueIPVisitsOnDay("Sep 27").size());
        //System.out.println(la.countUniqueIPsInRange(200, 299));
        //HashMap<String, Integer> hm = la.countVisitsPerIP();
        //int maxcount = la.mostNumberVisitsByIP(hm);
        //System.out.println(maxcount);
        //System.out.println(la.iPsMostVisits(hm, maxcount));
        HashMap<String, ArrayList<String>> hmd = la.iPsForDays();
        //System.out.println(hmd);
        //System.out.println(la.dayWithMostIPVisits(hmd));
        System.out.println(la.iPsWithMostVisitsOnDay(hmd, "Sep 30"));
    }
}
