import edu.duke.*;
/**
 * Write a description of WordPlay here.
 * 
 * @author Saurabh Godse
 * @version (a version number or a date)
 */
public class WordPlay {
    public boolean isVowel(char ch){
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ||
        ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U';
    }
    
    public String replaceVowels(String phrase, char ch){
        StringBuilder s = new StringBuilder(phrase);
        int len = s.length();
        for(int i = 0; i < len; i++){
            char currChar = s.charAt(i);
            if(isVowel(currChar)){
                s.setCharAt(i, ch);
            }
        }
        return s.toString();
        
    }
    
    public boolean isEqual(char a, char b){
        return Character.toUpperCase(a) == Character.toUpperCase(b);
    }
    public String emphasize(String phrase, char ch){
        StringBuilder s = new StringBuilder(phrase);
        int len = s.length();
        for(int i = 0; i < len; i++){
            char currChar = s.charAt(i);
            if(isEqual(currChar, ch)){
                if(i % 2 == 0){
                    s.setCharAt(i, '*');
                }
                else{
                    s.setCharAt(i, '+');
                }
            }
        }
        return s.toString();
        
    }
    public void tester(){
        System.out.println(replaceVowels("Hello World", '*'));
        System.out.println(emphasize("dna ctgaaactga", 'a'));
    }
}
