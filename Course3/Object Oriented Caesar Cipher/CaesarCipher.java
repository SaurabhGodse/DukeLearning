
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    public CaesarCipher(int key){
        mainKey = key;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
    }
    
    public String encrypt(String message){
        String encrypted = "";
        StringBuilder s = new StringBuilder(message.toUpperCase());
        int len = s.length();
        for(int i = 0; i < len; i++){
            char ch = message.charAt(i);
            if(Character.isLetter(ch)){
                if(Character.isLowerCase(ch)){
                    int idx = alphabet.indexOf(s.charAt(i));
                    char shiftedCh = shiftedAlphabet.charAt(idx);
                    s.setCharAt(i, Character.toLowerCase(shiftedCh));
                }
                else{
                    int idx = alphabet.indexOf(s.charAt(i));
                    char shiftedCh = shiftedAlphabet.charAt(idx);
                    s.setCharAt(i, shiftedCh);
                    
                }
            }
        }
        return s.toString();
    }
    
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(encrypted);
    }

}
