import edu.duke.*;

/**
 * Write a description of CaeserBreaker here.
 * 
 * @author Saurabh Godse 
 * @version (a version number or a date)
 */
public class CaeserBreaker {
    public void countLetters(String s, int[] counts){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        for(int i = 0; i < s.length(); i++){
            char ch = Character.toLowerCase(s.charAt(i));
            int idx = alph.indexOf(ch);
            if(idx != -1){
                counts[idx]++;
            }
        }
    }
    
    public int indexOfMax(int[] counts){
        int maxindex = 0;
        int maxval = 0;
        for(int i = 0; i < counts.length; i++){
            if(counts[i] > maxval){
                maxval = counts[i];
                maxindex = i;
            }
        }
        return maxindex;
    }
    
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int[] freq = new int [26];
        countLetters(encrypted, freq);
        int maxindex = indexOfMax(freq);
        int dkey = maxindex - 4;
        
        if(maxindex < 4){
            dkey = 26 - (4 - maxindex);
        }
        System.out.println("Key : " + dkey);
        return cc.encrypt(encrypted, 26 - dkey);
    }
    
    public String halfOfString(String message, int start){
        String newString = "";
        int len = message.length();
        for(int i = start; i < len; i += 2){
            newString += message.charAt(i);
        }
        return newString;
    }
    
    public String mergeString(String a, String b){
        String mergedStr = "";
        int len1 = a.length();
        int len2 = b.length();
        for(int i = 0; i < len1 + len2; i++){
            if(i % 2 == 0){
                mergedStr += a.charAt(i / 2);
            }
            else{
                mergedStr += b.charAt(i / 2);
            }
        }
        return mergedStr;
    }
    
    public void decryptTwoKeys(String encrypted){
        String evenstr = halfOfString(encrypted, 0);
        String oddstr = halfOfString(encrypted, 1);
        CaesarCipher cc = new CaesarCipher();
        //System.out.println(mergeString(cc.encrypt(evenstr, 26 - 2), cc.encrypt(oddstr, 26 - 20)));
        String evenstrdecr = decrypt(evenstr);
        String oddstrdecr = decrypt(oddstr);
        System.out.println(mergeString(evenstrdecr, oddstrdecr));
        
    }
    public void testDecrypt(){
        //CaesarCipher cc = new CaesarCipher();
        //String encrypted = cc.encrypt("I want eeeeeee", 1);
        //System.out.println(decrypt(encrypted));
        //System.out.println(halfOfString("I am God", 0));
        //System.out.println(halfOfString("I am God", 1));
        FileResource fr = new FileResource();
        String message = fr.asString();
        decryptTwoKeys(message);
    }

}
