
/**
 * Write a description of YearsAfterFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class YearsAfterFilter implements Filter{
    private int myYear;
    
    public YearsAfterFilter (int year) {
        myYear = year;
    }
    
    public boolean satisfies(String id) {
        return MovieDatabase.getYear(id) >= myYear;
    }
}
