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
public class ProcessFilters {
    
    public ArrayList<String> getMoviesByFilter(Filter filterCriteria){
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        return movies;
    }
}
