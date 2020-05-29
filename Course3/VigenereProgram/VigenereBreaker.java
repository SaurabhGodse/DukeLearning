import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        String slice = "";
        int len = message.length();
        for(int i = whichSlice; i < len; i += totalSlices){
            slice += message.charAt(i);
        }
        return slice;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for(int i = 0; i < klength; i++){
            //key[i] = 0;
            String slice = sliceString(encrypted, i, klength);
            CaesarCracker cc = new CaesarCracker(mostCommon);
            key[i] = cc.getKey(slice);
            //System.out.println(key[i]);
        }
        
        return key;
    }

    public void breakVigenere () {
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        FileResource fr = new FileResource();
        String message = fr.asString();
        String[] lang = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};
        for(String s : lang){
            FileResource dic = new FileResource("dictionaries/" + s);
            HashSet<String> dictionary = readDictionary(dic);
            languages.put(s, dictionary);
        }
        breakForAllLangs(message, languages);
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dictionary = new HashSet<String>();
        for(String line : fr.lines()){
            dictionary.add(line.toLowerCase());
        }
        return dictionary;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        int valid = 0;
        for(String word : message.split("\\W+")){
            if(dictionary.contains(word.toLowerCase())){
                valid++;
            }
        }
        return valid;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        int maxcount = 0;
        String decMes = "";
        
        for(int keylength = 1; keylength < 101; keylength++){
            int[] key = tryKeyLength(encrypted, keylength, mostCommonCharIn(dictionary));
            VigenereCipher vc = new VigenereCipher(key);
            String decMessage = vc.decrypt(encrypted);
            int temp = countWords(decMessage, dictionary);
            if(temp > maxcount){
                maxcount = temp;
                decMes = decMessage;
                /*System.out.println("for keylength : " + keylength);
                System.out.println("valid words : " + maxcount);
                for(int i = 0; i < keylength; i++){
                    System.out.println(key[i]);
                }*/
            }
        }
        return decMes;
    }
    
    public HashMap<Character, Integer> countFreq(HashSet<String> dictionary){
        HashMap<Character, Integer> freq = new HashMap<Character, Integer>();
        for(String word : dictionary){
            int len = word.length();
            for(int i = 0; i < len; i++){
                char ch = Character.toLowerCase(word.charAt(i));
                if(freq.containsKey(ch)){
                    freq.put(ch, freq.get(ch) + 1);
                }
                else{
                    freq.put(ch, 1);
                }
            }
        }
        return freq;
        
    }
    
    public char mostCommon(HashMap<Character, Integer> freq){
        int maxval = 0;
        char ch = 'e';
        for(char c : freq.keySet()){
            int temp = freq.get(c);
            if(temp > maxval){
                maxval = temp;
                ch = c;
            }
        }
        return ch;
        
    }
    public char mostCommonCharIn(HashSet<String> dictionary){
        return mostCommon(countFreq(dictionary));
        
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages){
        int maxcount = 0;
        String decrypted = "";
        String l = "";
        for(String lang : languages.keySet()){
            String decMsg = breakForLanguage(encrypted, languages.get(lang));
            int temp = countWords(decMsg, languages.get(lang));
            if(temp > maxcount){
                maxcount = temp;
                decrypted = decMsg;
                l = lang;
            }
        }
        System.out.println(l);
        System.out.println(decrypted);
    }
}
