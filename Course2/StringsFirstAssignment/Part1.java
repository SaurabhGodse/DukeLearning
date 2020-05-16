
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String s){
        int startIndex = s.indexOf("ATG");
        if(startIndex == -1)
            return "";
        int endIndex = s.indexOf("TAA", startIndex + 3);
        if(endIndex == -1)
            return "";
        System.out.println("Start : " + startIndex);
        System.out.println("End : " + endIndex);
        if((endIndex - startIndex) % 3 == 0)
            return s.substring(startIndex, endIndex + 3);
        return "Not a valid DNA string";
        
             
    }
    public void testSimpleGene(){
        System.out.println("1 " + findSimpleGene("ATATATGATA"));
        System.out.println("2 " + findSimpleGene("TAAATAG"));
        System.out.println("3 " + findSimpleGene("ATGATATCATC"));
        System.out.println("4 " + findSimpleGene("AACACATGCACTAATATACGACG"));
        System.out.println("5 " + findSimpleGene("CTCTCTC"));
        
    }

}
