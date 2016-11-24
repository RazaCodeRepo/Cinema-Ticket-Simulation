/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematicketsim;

import java.util.ArrayList;

/**
 *
 * @author Kontrol
 */
public class CreateFilters {
    
    public ArrayList<Movie> byGenre(String genre){
        ArrayList<Movie> filteredMovies = new ArrayList<Movie>();
    
        ProcessFilters processFilters = new ProcessFilters();
        
        Filter myFilter = new GenreFilter(genre);
        
        ArrayList<String> movies = processFilters.getMoviesByFilter(myFilter);
        
        for(String temp : movies){
            filteredMovies.add(MovieDatabase.getMovie(temp));
        }
        return filteredMovies;
    }
    
    public ArrayList<Movie> byYear(int year){
        ArrayList<Movie> filteredMovies = new ArrayList<Movie>();
        
        ProcessFilters processFilters = new ProcessFilters();
        
        Filter myFilter = new YearFilter(year);
        
        ArrayList<String> movies = processFilters.getMoviesByFilter(myFilter);
        
        for(String temp : movies){
            filteredMovies.add(MovieDatabase.getMovie(temp));
        }    
        return filteredMovies;
    }
    
    
    
}
