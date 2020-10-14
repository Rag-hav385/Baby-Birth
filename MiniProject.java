import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of MiniProject here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MiniProject {
     
    public void totalBirths(FileResource fr){
        int totalBirths = 0;
        int totalBoys = 0, totalGirls = 0;
        int boyNames = 0, girlNames = 0;

        for(CSVRecord record: fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(record.get(2));         //gets the number of babies born with the name
            totalBirths += numBorn;
            if(record.get(1).equals("M")){
                totalBoys += numBorn;
                boyNames++;
            }else{
                totalGirls += numBorn;
                girlNames++;
            }
        }
        System.out.println("Total births: " + totalBirths + " Total names: " + boyNames+girlNames);
        System.out.println("Total boys: " + totalBoys + " Total boy names: " + boyNames);
        System.out.println("Total girls: " + totalGirls + " Total girl names: " + girlNames);
    }

    //TEST method for totalBirths
    public  void testTotalBirths(){
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
  
    public int getRank(int year , String name , String gender){
        //changing year to file//
        
        
        String Subfolder = "data";
        
        
        FileResource fr = new FileResource("yob" + year + ".csv");
        
        //Getting Rank 
        int rank = 0;
        
        for(CSVRecord record : fr.getCSVParser(false)){
                
                
                if (record.get(1).equals(gender)){
                    rank = rank + 1;
                    
                }
                if (record.get(0).equals(name) && record.get(1).equals(gender)){
                    
                    return rank;
                }
                
        
        
            }
            
        return -1;
    }
       
       public void TestGetRank(){
        int rank = getRank(1971 , "Frank" , "M");
        
        System.out.println("Rank : " + rank);
    }
    
    public String getName(int year , int rank , String gender){
        FileResource fr = new FileResource("yob" + year + ".csv");
        int rank_var = 0;
        for (CSVRecord record : fr.getCSVParser(false)){
            String Name = record.get(0);
            
            if (record.get(1).equals(gender)){
                rank_var = rank_var + 1;
            }
            if (rank_var == rank && record.get(1).equals(gender)){
                return record.get(0);
            }
        }
        return "NO NAME";
    }
    
    public void TestGetName(){
        String Name = getName(1980 , 350 , "F");
        String Name1 = getName(1982 , 450 , "M");
        System.out.println("Name of rank :" + Name + " " + Name1);  
    }
        
    
    public String WhatNameInYear (String name , int year , int newyear , String gender){
        int rank = getRank(year , name , gender);
        String Name = getName(newyear , rank , gender);
        
        return Name;
        
        
    }
    
    public void TestWhatNameInYear(){
        String Name = WhatNameInYear("Susan" , 1972 , 2014 , "F");
        String Name1 = WhatNameInYear("Owen" , 1974 , 2014 , "M");
        System.out.println("New name : " + Name + Name1); 
        
    }
    
    public int YearOfHighestRank(String name , String gender){
        DirectoryResource dr = new DirectoryResource();
        int highestRank = 0;
        int highestYear = -1;

        for(File f: dr.selectedFiles()){
            int currYear = Integer.parseInt(f.getName().replaceAll("[^\\d]", ""));
            int currRank = getRank(currYear, name, gender);

            if(highestRank == 0 && currRank != -1){
                highestRank = currRank;
                highestYear = currYear;
            }
            if(currRank < highestRank && currRank != -1){
                highestRank = currRank;
                highestYear = currYear;
            }
        }
        return highestYear;
    }
    
    
    public void TestYearOfHighestRank(){
        int  MaxYearRank = YearOfHighestRank("Genevieve" , "F");
        int max = YearOfHighestRank("Mich" , "M");
        System.out.println("Max year of Rank :" + MaxYearRank + max);
    }

    public double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int totalRank = 0, countedYears = 0;

        for(File f: dr.selectedFiles()){
            int currYear = Integer.parseInt(f.getName().replaceAll("[^\\d]", ""));
            int currRank = getRank(currYear,name, gender);

            if(currRank != -1){
                totalRank += currRank;
                countedYears++;
            }
        }
        double result = (double)totalRank/countedYears;
        if(result > 0){ return result; }
        return -1;
    }
    
    public void TestgetAverageRank(){
        double Average = getAverageRank("Susan" , "F");
        double Average1 = getAverageRank("Robert" , "M");
        System.out.println("Average :" + Average + " " + Average1);
    }


}


