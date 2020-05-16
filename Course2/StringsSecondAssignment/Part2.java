
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String a, String b){
        int count = 0;
        int currIndex = b.indexOf(a);
        while(currIndex != -1){
            count += 1;
            currIndex = b.indexOf(a, currIndex + 1);
        }
        return count;
    }
    public void testHowMany(){
        System.out.println(howMany("aa", "aaaaa"));
        System.out.println(howMany("abc", "abcdbcabc"));
        System.out.println(howMany("xyz", "asf;lj"));
    }

}

