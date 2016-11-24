/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematicketsim;

import edu.duke.FileResource;
import java.io.File;
import java.util.ArrayList;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author Kontrol
 */
public class DateTimeClassParser {
    
    public ArrayList<DateTime> loadData(String filename){
        
        ArrayList<DateTime> dateTimeData = new ArrayList<DateTime>();
        
        try{
            File f = new File(filename);
            filename = f.getCanonicalPath();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        FileResource file = new FileResource(filename);
        
     
        CSVParser parser = file.getCSVParser();
        
        for(CSVRecord record : parser){
        
            String movieId = record.get("id");
        
            String showDate = record.get("date");
        
            String showTime = record.get("time");
        
            
            DateTime movieInfo = new DateTime(movieId,showDate,showTime);
        
            dateTimeData.add(movieInfo);
        
                
        }
        
        
        
        return dateTimeData;
    }
    
}
