/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematicketsim;

/**
 *
 * @author Kontrol
 */
public class DateTime {
    
    private String id;
    private String showDate;
    private String showTime;
    
    public DateTime(String mid,String mDate,String mTime){
        id = mid.trim();
        showDate = mDate.trim();
        showTime = mTime.trim();
    }
    
    public String getMovieId(){
        return id;
    }
    
    public String getShowDate(){
        return showDate;
    }
    
    public String getShowTime(){
        return showTime;
    }
    
    public String toString(){
        String result = id + " " + showDate + " " + showTime;
        return result;
    }

    
}
