
/**
 * Write a description of PhraseFilter here.
 * 
 * @author Saurabh Godse
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter{
    String where;
    String phrase;
    public PhraseFilter(String a, String b){
        where = a;
        phrase = b;
    }
    public boolean satisfies(QuakeEntry qe){
        String title = qe.getInfo();
        if(where.equals("start")){
            return title.startsWith(phrase);
        }
        if(where.equals("end")){
            return title.endsWith(phrase);
        }
        if(where.equals("any")){
            return title.contains(phrase);
        }
        return false;
    }

}
