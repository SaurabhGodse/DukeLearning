import edu.duke.*;
import java.io.File;
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
    public void findWebLinks(){
    URLResource file = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
    //System.out.println("File ready");
    for (String item : file.words()) {
           String itemLower = item.toLowerCase();
           //System.out.println(itemLower);
           int pos = itemLower.indexOf("youtube.com");
           if (pos != -1) {
                int beg = item.lastIndexOf("\"",pos);
                int end = item.indexOf("\"", pos+1);
                System.out.println(item.substring(beg+1,end));
               }
   	}
    }

}
