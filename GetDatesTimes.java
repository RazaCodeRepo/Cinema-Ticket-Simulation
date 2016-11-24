/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematicketsim;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Kontrol
 */
public class GetDatesTimes {
    
    private HashMap<String,ArrayList<String>> idToDates;
    private HashMap<String,ArrayList<String>> dateToTime;  
    private ArrayList<String> dateTime;
    
    public  GetDatesTimes(){
        idToDates = new HashMap<String,ArrayList<String>>();
        dateToTime = new HashMap<String,ArrayList<String>>(); 
        dateTime = new ArrayList<String>();
        
    }
    
    public void mapMovieToDatesTimes(){
        
        
        DateTimeClassParser dateTimeLoader = new DateTimeClassParser();
        ArrayList<DateTime> movieList = dateTimeLoader.loadData("moviedatetime_small.csv");
        
        for(DateTime temp : movieList){
            ArrayList<String> dateTime = new ArrayList<String>();
            String tempId = temp.getMovieId();
            if(!idToDates.containsKey(tempId)){
                for(DateTime temp2 : movieList){
                    
                    if(temp2.getMovieId().equals(tempId)){
                        String tempDate = temp2.getShowDate();
                        String tempTime = temp2.getShowTime();
                        String tempCon = tempDate + "," + tempTime;
                        dateTime.add(tempCon);
                        //dateTime.add(temp2.getShowDate());
                    }
                   idToDates.put(tempId,dateTime); 
                }
            }
        }
    }
    
    public ArrayList<String> getDates(String movieId){
        ArrayList<String> dates = new ArrayList<String>();
        //ArrayList<String> dates = new ArrayList<String>();
        dateTime = idToDates.get(movieId);
        
        for(String temp : dateTime){
            
            int index = temp.indexOf(",");
            String tempDate = temp.substring(0,index);
            
            if(!dates.contains(tempDate)){
                if(checkDate(tempDate)){
                    dates.add(tempDate);
                }
            }
            
            
        }
        return dates;
    }
    
    public ArrayList<String> getTime(String d){
        
        for(String temp : dateTime){
            
            ArrayList<String> times = new ArrayList<String>();
            
            int index = temp.indexOf(",");
            String tempDate = temp.substring(0,index);
            
            String tempTime = temp.substring(index+1,temp.length());
            if(!dateToTime.containsKey(tempDate)){
                for(String temp2 : dateTime){
                    int index2 = temp2.indexOf(",");
                    String tempDate2 = temp2.substring(0,index2);
                    String tempTime2 = temp2.substring(index2+1,temp2.length());
                   
                    if(tempDate.equals(tempDate2)){
                        times.add(tempTime2);
                    }
                }
                dateToTime.put(tempDate,times);
                
            }
            
            
        }   
        ArrayList<String> timeList = dateToTime.get(d);
        return timeList;
    }
    
    private boolean checkDate(String date){
        boolean check = false;
        DateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        try{
            boolean check1 = false;
            boolean check2 = false;
            Date parsedDate = sdf.parse(date);
            Date todayDate = new Date();
            String stringTodayDate = sdf.format(todayDate);
            
            if(date.equals(stringTodayDate)){
                
                check1 = true;
            }
            if(parsedDate.after(todayDate)){
                
                check2 = true;
            }
            
            if(check1 == true || check2 == true){
                check = true;
            }
        }catch(ParseException e){
            e.printStackTrace();
        }
        return check;
        
    }
    
}
