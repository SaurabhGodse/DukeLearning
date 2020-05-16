
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String s, String startToken, String endToken){
        boolean upper = true;
        char[] a = s.toCharArray();
        if(Character.isLowerCase(a[0]))
            upper = false;
        s = s.toUpperCase();
        startToken = startToken.toUpperCase();
        endToken = endToken.toUpperCase();
        
        int startIndex = s.indexOf(startToken);
        if(startIndex == -1)
            return "";
        int endIndex = s.indexOf(endToken, startIndex + 3);
        if(endIndex == -1)
            return "";
        System.out.println("Start : " + startIndex);
        System.out.println("End : " + endIndex);
        if((endIndex - startIndex) % 3 == 0)
        {
            if(upper)
            {
                return s.substring(startIndex, endIndex + 3);
            }
            else
            {
                return s.substring(startIndex, endIndex + 3).toLowerCase();
            }
        }
            
        return "Not a valid DNA string";
        
             
    }
    public void testSimpleGene(){
        System.out.println("1 " + findSimpleGene("ATATATGATA", "ATG", "TAA"));
        System.out.println("2 " + findSimpleGene("TAAATAG", "ATG", "TAA"));
        System.out.println("3 " + findSimpleGene("ATGATATCATC", "ATG", "TAA"));
        System.out.println("4 " + findSimpleGene("AACACATGCACTAATATACGACG", "ATG", "TAA"));
        System.out.println("5 " + findSimpleGene("aatatgtaa", "ATG", "TAA"));
        
    }
}
