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
public class YearFilter implements Filter {
    
    private int myYear;
	
	public YearFilter(int year) {
		myYear = year;
	}
	
	@Override
	public boolean satisfies(String id) {
		return MovieDatabase.getYear(id) == myYear;
	}
    
}
