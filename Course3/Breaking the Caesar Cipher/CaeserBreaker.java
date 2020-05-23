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
        String message = "Xifqvximt tsdtlxzrx iijirvtl ek Uybi afvbw yehvv xyi gfqdse iekmfrrpzdrxzse fj xyi jzich sw tsdtlxrxzseec xifqvxic, fjkie xmmie zr xyi trwk, xyek klv nsipu rvfyeh yj zw xyvvi-hzqvrjmfrrp eeh ulijxzsew lfa xymekj zr xymj nsipu iiceki xf vetl sklvv eii melvvvrkpp xifqvximt. Xrov dsmmek e tzees xyvfyxl e hfsi-wvrqv rru gprremek e jcmxlk-gekl xyek rzfmuw gfpcmjmfrj nmkl sklvv ezvgprrvw ej kaf vbrqgpvw. Zx wyslpu klvvvjfvv esk jyitimji xyek tsdtlxzrx gvftvvkmvw esslx xyiji kvsdikvzg xymekj rru klvmi zrkiietxzse rvv tsdqfr-tceti eeh mdtfvkeex. Nlzpv klzw mj jxzpc r mecmu rvxydiex, ni afych pzov ks edieh xyek dsjx sw klv xifqvximt hyvwkmfrj giftci gfrtiir xyidwvpmij nmkl lrzv ks hf nmkl lfa xymekj rvv tservgkiu. Mk zw mdtfvkeex xyek ymxlnepw eii wljwmtmvrkpp jxiezkyx eeh wdsfxy ks wltgsix xyi himmmek sw wejx grvj, flx eesklvv ijwvrkmrp tisgiixp, aymtl av lwlecpp kebi jfv kieexvh, zw xyek ymxlnepw eii gfrkmeyfyj, mehviu tservgkmek E xf S, eeh rfx nlwk rtgvfbzqrxvpp. Xyi gfviijtfrumek wlfwmvpu fj gfqgykekmfrrp kvsdikvp zw swxvr vvjvviiu ks ej tsdtlxrxzseec ksgscsxc. R xsfh tfvkmfr sw fyi vjwsixj dep si gcejwzjziu ks fvpfrx ks xymj jysjzich eeh eii himmie sc egtcmtekmfrj zr e zrvzikc sw fxyii wmvpuw, klv gvvhzgkmfr sw klv jxiytxlvv fj jfpuiu gvfxvmew eeh xyi vvgfrjxiytxzse fj llqrr sikrrj sizrx kaf. Xyi lrpcqrvb fj slv afvb zw jrwk rpxsimkldw xyek zqgpvqvrk deklvqrxzgrp qfhvpj ks swjvv mewzkyxj zrks eeh eewniiw xf jytl ulijxzsew." 
        +

" Av rvv vbgpfvzrx zwjyvw wlgy rw lfa xvgyrzulij wsi jsczzrx gvffcidw grr fv umjgfzvvvh, zqgvfzvh, rrrppdvh, rru uidsewkvrxvh xf si gfviitx si ftkmdec. Av vbgitx xf debi pveumek gfrkvzflxzsew me tsdtlxrxzseec xifqvxic, xifqvximt dsuicmek, ueke wkvlgkyiij, lzky-giijfvdeegv tsdtlxzrx, M/S-iwjzgziegp wsi vbkiirrp qvqfvp, kvsxvrtymt zrwsiqrxzse jcjxvqj (KZW), fzscsxmtec tsdtlxzrx, eeh hrxr tsdtiijwzse."
+

" Sitelwv fj xyi kvsdikvzg rrxlvv fj xyi tycjmtec nsipu zr aymtl av cmmi, xifqvximt gvffcidw eimji me rrp rvve xyek zrkiietxj nmkl xyi tycjmtec nsipu. Kvsdikvzg gfqgykmek jfglwvw se uijmxrzrx, eeeccqmek, rru zqgpvqvrkmek iwjzgziex eckfvzxyqj wsi xifqvximt gvffcidw. Klv xifqvximt tsdtlxzrx xvfyg fj xyi hvtrvkqvrk zw mexvveekmfrrpcc vvrfaeiu wsi zxj tseximsykmfrj ks xyi jlruediexrp tisspvqj zr gfqgykekmfrrp kvsdikvp, euhiijwzrx dejwzzv ueke qrrrkvqvrk zwjyvw me xifqvximt gvffcidw, rru rtgppmek kvsdikvzg xvgyrzulij ks e zrvzikc sw rvvej, megcyumek qfpvglprv fzscsxc, xifqvximt dsuicmek, issskmtw, xifkieglzg mejfvdekmfr wpwkidw, vgfpfkp, eeh tysksemtw."
+

" Xyi kislt etxzzvpp tscprffvrxvw azxy fxyii xvfygw azxyme klv uigeixdiex eeh azxy klv iijirvtlvvj zr sklvv hvtrvkqvrkw ek Uybi. Klvc gfpcessieki azxy wetycxp zr Qrxyidekmtw, Smfpfkp, Fzstlvqzwkvp, Icitximtec rru Tsdtlxvv Iekzrviimek, rru klv Emtlfprw Wtlfsc fj Iezzvfrdiex. Sipseh Hlov, xyi kislt ecwf tscprffvrxvw azxy iijirvtlvvj rx zrvzslw xft mewkmkykij. Fvgryji sw zxj uigxy rru svveuxy, xyi kvsdikvzg gfqgykmek kislt ek Uybi mj rvxyrfcc xyi xft kvsdikvzg gfqgykmek kislt me klv eekmfr.";
        decryptTwoKeys(message);
    }

}
