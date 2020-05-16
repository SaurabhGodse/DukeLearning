
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    boolean twoOccurrences(String a, String b){
        int firstOc = b.indexOf(a);
        if(firstOc == -1)
            return false;
        int secondOc = b.indexOf(a, firstOc + 1);
        if(secondOc == -1)
            return false;
        return true;
    }
    String lastPart(String a, String b){
        int firstOc = b.indexOf(a);
        if(firstOc == -1)
            return b;
        return b.substring(firstOc + a.length(), b.length());
    }
    void testing(){
        System.out.println(twoOccurrences("ab", "ababab"));
        System.out.println(twoOccurrences("abc", "abcba"));
        System.out.println(twoOccurrences("ac", "def"));
        System.out.println(twoOccurrences("aa", "aaa"));
        System.out.println(lastPart("an", "banana"));
        System.out.println(lastPart("zoo", "forest"));
    }

}
