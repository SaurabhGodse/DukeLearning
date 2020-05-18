import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of ParsingWeather here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ParsingWeather {
    public CSVRecord getSmallestOfTwo(CSVRecord smallestSoFar, CSVRecord currentRecord){
        if(smallestSoFar == null){
            smallestSoFar = currentRecord;
        }
        else{
            double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
            double currentTemp = Double.parseDouble(currentRecord.get("TemperatureF")); 
            if(currentTemp < smallestTemp){
                smallestSoFar = currentRecord;
            }
        }
        return smallestSoFar;
    }
    
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord smallestSoFar = null;
        for(CSVRecord currentRecord : parser){
            smallestSoFar = getSmallestOfTwo(smallestSoFar, currentRecord);
        }
        return smallestSoFar;
        
    }
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVRecord record = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature was " + record.get("TemperatureF")
        + " at " + record.get("DateUTC") + " " + record.get("TimeEST"));

    }
    
    public void fileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord smallestSoFar = null;
        File smallest = null;
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRecord = coldestHourInFile(fr.getCSVParser());
            if(smallestSoFar == null){
                smallestSoFar = currentRecord;
                smallest = f;
            }
            else{
                double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
                double currentTemp = Double.parseDouble(currentRecord.get("TemperatureF")); 
                if(currentTemp < smallestTemp){
                    smallestSoFar = currentRecord;
                    smallest = f;
                }
            }
        }
        System.out.println("Coldest day was in file " + smallest.getName());
        System.out.println("Coldest temp on that day was " + smallestSoFar.get("TemperatureF"));
        
    }

}
