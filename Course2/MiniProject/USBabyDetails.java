import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of USBabyDetails here.
 * 
 * @author Saurabh Godse 
 * @version (a version number or a date)
 */
public class USBabyDetails {
    public boolean isboy(CSVRecord record){
        return record.get(1).equals("M");
    }
    
    public void totalBirths(){
        FileResource fr = new FileResource();
        int total = 0;
        int boysCount = 0;
        int girlsCount = 0;
        for(CSVRecord record : fr.getCSVParser(false)){
            if(isboy(record)){
                boysCount += 1;
            }
            else{
                girlsCount += 1;
            }
            total += 1;
        }
        System.out.println("Total : " + total);
        System.out.println("Boys : " + boysCount);
        System.out.println("Girls : " + girlsCount);
    }
    
    public int getRank(String name, String gender, int year){
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + year + ".csv");
        int grank = 0;
        int brank = 0;
        for(CSVRecord record : fr.getCSVParser(false)){
            if(isboy(record)){
                brank += 1;
                if(record.get(0).equals(name) && record.get(1).equals(gender)){
                    return brank;
                }
                
            }
            else{
                grank += 1;
                if(record.get(0).equals(name) && record.get(1).equals(gender)){
                    return grank;
                }

            }
            
        }
        return -1;
    }
    
    public int getRankInFile(String name, String gender, FileResource fr){
        int grank = 0;
        int brank = 0;
        for(CSVRecord record : fr.getCSVParser(false)){
            if(isboy(record)){
                brank += 1;
                if(record.get(0).equals(name) && record.get(1).equals(gender)){
                    return brank;
                }
                
            }
            else{
                grank += 1;
                if(record.get(0).equals(name) && record.get(1).equals(gender)){
                    return grank;
                }

            }
            
        }
        return -1;
    }
    
    public String getName(String gender, int rank, int year){
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + year + ".csv");
        int grank = 0;
        int brank = 0;
        for(CSVRecord record : fr.getCSVParser(false)){
            if(isboy(record)){
                brank += 1;
                if(brank == rank && record.get(1).equals(gender)){
                    return record.get(0);
                }
                
            }
            else{
                grank += 1;
                if(grank == rank && record.get(1).equals(gender)){
                    return record.get(0);
                }

            }
            
        }
        return "NO NAME";
        
    }
    
    public void whatIsNameInYear(String name, String gender, int year, int newyear){
        int rank = getRank(name, gender, year);
        if(rank != -1){
            System.out.println(name + " born in " + year + " would be " + getName(gender, rank, newyear) + " if born in " + newyear);
            
        }
        else{
            System.out.println("NO NAME");
        }
    }
    
    public String yearOfHighestRank(String name, String gender){
        int highestRank = 0;
        String YOHR = "-1";
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            int currRank = getRankInFile(name, gender, fr);
            if(currRank != -1)
            {
                if(highestRank == 0){
                    highestRank = currRank;
                    YOHR = f.getName();
                }
                else{
                    if(currRank < highestRank){
                        highestRank = currRank;
                        YOHR = f.getName();
                    }
                }
            }
        }
        return YOHR;
    }
    
    public double getAverageRank(String name, String gender){
        double sum = 0;
        int count = 0;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            int rank = getRankInFile(name, gender, fr);
            if(rank != -1){
                sum += rank;
            }
            count += 1;
        }
        return sum / count;
    }
    
    public int getTotalBirthsRankedHigher(String name, String gender, int year){
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + year + ".csv");
        int rank = getRank(name, gender, year);
        int sum = 0;
        int brank = 0;
        int grank = 0;
        for(CSVRecord record : fr.getCSVParser(false)){
            if(rank == -1){
                if(record.get(1).equals(gender)){
                    sum += Integer.parseInt(record.get(2));
                }
            }
            else{
                if(isboy(record)){
                    brank += 1;
                    if(brank < rank && record.get(1).equals(gender)){
                        sum += Integer.parseInt(record.get(2));
                    }
                    
                }
                else{
                    grank += 1;
                    if(grank < rank && record.get(1).equals(gender)){
                        sum += Integer.parseInt(record.get(2));
                    }

                }
            }
        }
        return sum;
        
    }
    public void tester(){
        //totalBirths();
        //System.out.println("Rank is : " + getRank("Frank", "M", 1971));
        //System.out.println("Name : " + getName("M", 450, 1982));
        //whatIsNameInYear("Owen", "M", 1974, 2014);
        System.out.println("year of highest rank : " + yearOfHighestRank("Mich", "M"));
        //System.out.println("Average rank : " + getAverageRank("Robert", "M"));
        //System.out.println("Total births ranked higher : " + getTotalBirthsRankedHigher("Drew", "M", 1990));
    }

}
