import edu.duke.*;

/**
 * Write a description of CaeserCipher here.
 * 
 * @author Saurabh Godse 
 * @version (a version number or a date)
 */
public class CaeserCipher {
    public String encrypt(String input, int key){
        StringBuilder encrypted = new StringBuilder(input);
        int len = encrypted.length();
        String upperAlpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlpha = "abcdefghijklmnopqrstuvwxyz";
        String shiftedUpperAlpha = upperAlpha.substring(key) + upperAlpha.substring(0, key);
        String shiftedLowerAlpha = lowerAlpha.substring(key) + lowerAlpha.substring(0, key);
        
        for(int i = 0; i < len; i++){
            char currChar = encrypted.charAt(i);
            int idx;
            if(Character.isUpperCase(currChar)){
                idx = upperAlpha.indexOf(currChar);
                char newChar = shiftedUpperAlpha.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
            else if(Character.isLowerCase(currChar)){
                idx = lowerAlpha.indexOf(currChar);
                char newChar = shiftedLowerAlpha.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
            
        }
        return encrypted.toString();
    }
    
    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder encrypted = new StringBuilder(input);
        int len = encrypted.length();
        String upperAlpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlpha = "abcdefghijklmnopqrstuvwxyz";
        String shiftedUpperAlpha1 = upperAlpha.substring(key1) + upperAlpha.substring(0, key1);
        String shiftedLowerAlpha1 = lowerAlpha.substring(key1) + lowerAlpha.substring(0, key1);
        String shiftedUpperAlpha2 = upperAlpha.substring(key2) + upperAlpha.substring(0, key2);
        String shiftedLowerAlpha2 = lowerAlpha.substring(key2) + lowerAlpha.substring(0, key2);
        
        for(int i = 0; i < len; i++){
            if(i % 2 == 0){
                char currChar = encrypted.charAt(i);
                int idx;
                if(Character.isUpperCase(currChar)){
                    idx = upperAlpha.indexOf(currChar);
                    char newChar = shiftedUpperAlpha1.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
                else if(Character.isLowerCase(currChar)){
                    idx = lowerAlpha.indexOf(currChar);
                    char newChar = shiftedLowerAlpha1.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            }
            else{
                char currChar = encrypted.charAt(i);
                int idx;
                if(Character.isUpperCase(currChar)){
                    idx = upperAlpha.indexOf(currChar);
                    char newChar = shiftedUpperAlpha2.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
                else if(Character.isLowerCase(currChar)){
                    idx = lowerAlpha.indexOf(currChar);
                    char newChar = shiftedLowerAlpha2.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
                
            }
            
        }
        return encrypted.toString();
        
    }
    public void testCaeser(){
        String encrypted = encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15);
        System.out.println("Encrypted string : " + encrypted);
        String decrypted = encrypt(encrypted, 6);
        System.out.println("Decrypted string : " + decrypted);
        System.out.println("Encrypted string : " + encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
    }

}
