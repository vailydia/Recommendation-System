
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");    
    }

    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings firstRatings = new FirstRatings();
        myMovies = firstRatings.loadMovies("data/" + moviefile);
        myRaters = firstRatings.loadRaters("data/" + ratingsfile);
    }
    
    public int getMovieSize() {
        return myMovies.size();
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }    
    
    private double getAverageByID(String id, int minimalRaters) {
        double average = 0.0;
        FirstRatings firstRatings = new FirstRatings();
        double allRating = 0.0;
        int ratingsNum = firstRatings.ratingsNumOf(id,myRaters);
        if(ratingsNum < minimalRaters || ratingsNum == 0){
            return 0.0;
        }
        
        for(Rater rater : myRaters) {
            if(rater.hasRating(id)) {
                allRating = allRating + rater.getRating(id);
            }
        }
        
        average = allRating / ratingsNum;
        return average;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> listOfAveRating = new ArrayList<Rating>();
        for(Movie movie : myMovies) {
            double average = getAverageByID(movie.getID(),minimalRaters);
            if(average != 0.0) {
               Rating rating = new Rating(movie.getID(), average);
               listOfAveRating.add(rating);  
            }            
        }
        return listOfAveRating;
    }
    
    public String getTitle(String id) {
        for(Movie movie : myMovies) {
            if(movie.getID().equals(id)) {
                return movie.getTitle();
            }
        }
        return "NO SUCH ID";
    }
    
    public String getID(String title) {
        for(Movie movie : myMovies) {
            if(movie.getTitle().equals(title)) {
                return movie.getID();
            }
        }
        return "NO SUCH TITLE";        
    }
    
    public int high(int num) {
        int count = 0;
        FirstRatings firstRatings = new FirstRatings();
        for(Movie movie : myMovies) {
            int ratingsNum = firstRatings.ratingsNumOf(movie.getID(),myRaters);
            if(ratingsNum >= num){
                count ++;
            }            
                
        }

        return count;
    }    
    
}
