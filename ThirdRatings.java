
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        this("ratings.csv");
    } 
    
    public ThirdRatings(String ratingsfile) {
        FirstRatings firstRatings = new FirstRatings();
        myRaters = firstRatings.loadRaters("data/" + ratingsfile);
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
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        
        for(String movieID : movies) {
            double average = getAverageByID(movieID, minimalRaters);
            if(average != 0.0) {
               Rating rating = new Rating(movieID, average);
               listOfAveRating.add(rating);  
            }            
        }        
        return listOfAveRating;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {       
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> list = new ArrayList<Rating>();
        for(String movieID : movies) {
            double average = getAverageByID(movieID, minimalRaters);
            if(average != 0.0) {
               Rating rating = new Rating(movieID, average);
               list.add(rating);  
            }            
        }        
        return list;
    }
    
    public int high(int num) {
        int count = 0;
        FirstRatings firstRatings = new FirstRatings();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String movieID : movies) {
            int ratingsNum = firstRatings.ratingsNumOf(movieID,myRaters);
            if(ratingsNum >= num){
                count ++;
            }            
                
        }

        return count;
    }    
    
}
