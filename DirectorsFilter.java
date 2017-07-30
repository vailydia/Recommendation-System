
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter {
    private String myDirector;
    public DirectorsFilter (String director) {
        myDirector = director;
    }
    public boolean satisfies (String id) {        
        String[] directors = MovieDatabase.getDirector(id).trim().split("\\s*,\\s*");
        for(String dr : directors) { 
            if(myDirector.contains(dr)){
                return true;
            }
        }
        return false;
    }   
}
