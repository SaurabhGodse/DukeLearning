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
    public CSVRecord getSmallestOfTwo(CSVRecord smallestSoFar, CSVRecord currentRecord, String attr){
        if(smallestSoFar == null){
            smallestSoFar = currentRecord;
        }
        else{
            try{
                double smallestTemp = Double.parseDouble(smallestSoFar.get(attr));
                double currentTemp = Double.parseDouble(currentRecord.get(attr)); 
                if(currentTemp != -9999 && currentTemp < smallestTemp){
                    smallestSoFar = currentRecord;
                }
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        return smallestSoFar;
    }
    
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord smallestSoFar = null;
        for(CSVRecord currentRecord : parser){
            smallestSoFar = getSmallestOfTwo(smallestSoFar, currentRecord, "TemperatureF");
        }
        return smallestSoFar;
        
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord smallestSoFar = null;
        for(CSVRecord currentRecord : parser){
            smallestSoFar = getSmallestOfTwo(smallestSoFar, currentRecord, "Humidity");
        }
        return smallestSoFar;
    }
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVRecord record = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature was " + record.get("TemperatureF")
        + " at " + record.get("DateUTC") + " " + record.get("TimeEDT"));

    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVRecord record = lowestHumidityInFile(fr.getCSVParser());
        System.out.println("Lowest humidity is " + record.get("Humidity")
        + " at " + record.get("DateUTC") + " " + record.get("TimeEDT"));

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
    
    public CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord smallestSoFar = null;
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRecord = lowestHumidityInFile(fr.getCSVParser());
            smallestSoFar = getSmallestOfTwo(smallestSoFar, currentRecord, "Humidity");
        }
        return smallestSoFar;
    }
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord record = lowestHumidityInManyFiles();
        System.out.println("Lowest humidity is " + record.get("Humidity")
        + " at " + record.get("DateUTC") + " " + record.get("TimeEDT"));
        
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        double sum = 0;
        int count = 0;
        for(CSVRecord record : parser){
            sum += Double.parseDouble(record.get("TemperatureF"));
            count += 1;
        }
        return sum / count;
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        System.out.println("Average temperature in file : " + averageTemperatureInFile(fr.getCSVParser()));
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double sum = 0;
        int count = 0;
        for(CSVRecord record : parser){
            int humidity = Integer.parseInt(record.get("Humidity"));
            if(humidity >= value){
                sum += Double.parseDouble(record.get("TemperatureF"));
                count += 1;
            }
        }
        return sum / count;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        System.out.println("Avg temp when high humidity : " + averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80));
        
    }

}
