
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author Saurabh Godse
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    public CaesarCipherTwo(int key1, int key2){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
    }
    
    public String encrypt(String input){
        int len = input.length();
        StringBuilder encrypted = new StringBuilder(input.toUpperCase());
        for(int i = 0; i < len; i++){
            char ch = input.charAt(i);
            if(i % 2 == 0){
                if(Character.isLetter(ch)){
                    if(Character.isLowerCase(ch)){
                        int idx = alphabet.indexOf(encrypted.charAt(i));
                        char shiftedCh = shiftedAlphabet1.charAt(idx);
                        encrypted.setCharAt(i, Character.toLowerCase(shiftedCh));
                    }
                    else{
                        int idx = alphabet.indexOf(encrypted.charAt(i));
                        char shiftedCh = shiftedAlphabet1.charAt(idx);
                        encrypted.setCharAt(i, shiftedCh);
                        
                    }
                }
            }
            else{
                if(Character.isLetter(ch)){
                    if(Character.isLowerCase(ch)){
                        int idx = alphabet.indexOf(encrypted.charAt(i));
                        char shiftedCh = shiftedAlphabet2.charAt(idx);
                        encrypted.setCharAt(i, Character.toLowerCase(shiftedCh));
                    }
                    else{
                        int idx = alphabet.indexOf(encrypted.charAt(i));
                        char shiftedCh = shiftedAlphabet2.charAt(idx);
                        encrypted.setCharAt(i, shiftedCh);
                        
                    }
                }
                
            }
        }
        return encrypted.toString();
    }
    public String decrypt(String input, int key1, int key2){
        CaesarCipherTwo cct = new CaesarCipherTwo(26 - key1, 26 - key2);
        return cct.encrypt(input);
    }

}
