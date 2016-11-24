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
public class GenreFilter implements Filter {
    private String myGenre;
    
    public GenreFilter(String genre){
        myGenre = genre;
    }
    
    public boolean satisfies(String id){
        String temp = MovieDatabase.getGenres(id);
        if(temp.indexOf(myGenre) != -1){
            return true;
        }
        return false;
    }
}
