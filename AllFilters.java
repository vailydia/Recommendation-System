
/**
 * Write a description of AllFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class AllFilters implements Filter {
    private ArrayList<Filter> allFilters;
    public AllFilters () {
        allFilters = new ArrayList<Filter>();
    }
    
    public void addFilter (Filter f) {
        allFilters.add(f);
    }
    
    public boolean satisfies (String id) {
        for(Filter f : allFilters) {
            if(!f.satisfies(id)) {
                return false;
            }
        }
        return true;
    }
}
