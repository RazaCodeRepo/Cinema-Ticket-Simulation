/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematicketsim;

import edu.duke.FileResource;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author Kontrol
 */
public class MovieClassParser {
    
   private HashMap<String,String> idToMovies;
    
    public MovieClassParser(){
        idToMovies = new HashMap<String,String>();
    }
    
    public ArrayList<Movie> loadMovies(String filename){
      
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        
        
        
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
          
            String movieTitle = record.get("title");
           
            String movieYear = record.get("year");
         
            String movieCountry = record.get("country");
            
            String movieGenre = record.get("genre");
           
            String movieDirector = record.get("director");
           
            int movieMinutes = Integer.parseInt(record.get("minutes"));
            
            String moviePoster = record.get("poster");
            
            Movie movie = new Movie(movieId,movieTitle,movieYear,movieGenre,movieDirector,movieCountry,moviePoster,movieMinutes);
            movieList.add(movie);
        }
        
        for(Movie temp : movieList){
            String movieId = temp.getID();
            
            
            String movieTitle = temp.getTitle();
            
            idToMovies.put(movieId,movieTitle);
        }
        
        return movieList;
    }
    
    public String getMovieTitle(String id){
        
        return idToMovies.get(id);
        
    }
    
    public ArrayList<Movie> getMovie(String filename,String name){
      
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        
        
        
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
          
            String movieTitle = record.get("title");
           
            String movieYear = record.get("year");
         
            String movieCountry = record.get("country");
            
            String movieGenre = record.get("genre");
           
            String movieDirector = record.get("director");
           
            int movieMinutes = Integer.parseInt(record.get("minutes"));
            
            String moviePoster = record.get("poster");
            
            Movie movie = new Movie(movieId,movieTitle,movieYear,movieGenre,movieDirector,movieCountry,moviePoster,movieMinutes);
            movieList.add(movie);
        }
        
        ArrayList<Movie> movieSearch = new ArrayList<Movie>();
        String smallName = name.toLowerCase();
        for(Movie temp : movieList){
                String tempName = temp.getTitle();
                String lowerTempName = tempName.toLowerCase();
                if(lowerTempName.equals(smallName)){
                    movieSearch.add(temp);
                }

        }
        
        return movieSearch;
    
    }
    
    
}
