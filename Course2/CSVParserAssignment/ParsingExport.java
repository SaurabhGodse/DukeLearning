import edu.duke.*;
import org.apache.commons.csv.*;
/**
 * Write a description of ParsingExport here.
 * 
 * @author Saurabh Godse 
 * @version (a version number or a date)
 */
public class ParsingExport {
    public String countryInfo(CSVParser parser, String country){
        for(CSVRecord record : parser){
            String c = record.get("Country");
//            System.out.println("hi");
//            System.out.println(c + " : " + country + " : ");
            if(c.equals(country)){
                return c + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
            }
        }
        return "Not found";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String expitem1, String expitem2){
        for(CSVRecord record : parser){
            String export = record.get("Exports");
            if(export.contains(expitem1) && export.contains(expitem2)){
                System.out.println(record.get("Country"));
            }
        }
        
    }
    
    public int numberOfExporters(CSVParser parser, String expitem){
        int count = 0;
        for(CSVRecord record : parser){
            String export = record.get("Exports");
            System.out.println(export);
            if(export.contains(expitem)){
                count += 1;
            }
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount){
        int amtlen = amount.length();
        for(CSVRecord record : parser){
            String value = record.get("Value (dollars)");
            if(value.length() > amtlen){
                System.out.println(record.get("Country") + " " + value);
            }
        }
    }
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //System.out.println(countryInfo(parser, "Nauru"));
        //listExportersTwoProducts(parser, "fish", "nuts");
        //System.out.println("Number of exporters : " + numberOfExporters(parser, "sugar"));
        //System.out.println("Big exporters are : ");
        bigExporters(parser, "$999,999,999,999");
        
        
        
    }
    

}
