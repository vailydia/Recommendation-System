
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class FourthRatings {
    
    public FourthRatings() {
        this("ratings.csv");
    } 
    
    public FourthRatings(String ratingsfile) {
        RaterDatabase.initialize(ratingsfile);        
    }    
    
    private double getAverageByID(String id, int minimalRaters) {
        double average = 0.0;
        FirstRatings firstRatings = new FirstRatings();
        double allRating = 0.0;
        int ratingsNum = firstRatings.ratingsNumOf(id,RaterDatabase.getRaters());
        if(ratingsNum < minimalRaters || ratingsNum == 0){
            return 0.0;
        }
        
        for(Rater rater : RaterDatabase.getRaters()) {
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
    
    public int getRaterSize() {
        return RaterDatabase.size();
    } 

    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> list = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        for(Rater ra : RaterDatabase.getRaters()) {
            if(!ra.getID().equals(id)){
                double rating = dotProduct(me,ra);
                if(rating > 0){
                    list.add(new Rating(ra.getID(),rating));
                }
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;

    }
    
    private double dotProduct(Rater me, Rater r) {
        double dotProduct = 0.0;
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String item : movies) {
            if(me.hasRating(item) && r.hasRating(item)){
                dotProduct = dotProduct + (me.getRating(item)-5)*(r.getRating(item)-5);//get similarity 的关键点在这里
            }            
        }
        return dotProduct;
    }

    private double getSimilarAverageByID(String id, int minimalRaters, ArrayList<Rating> similarityRating) {
        double average = 0.0;
        double allRating = 0.0;
        int ratingsNum = ratingsNum(id, RaterDatabase.getRaters(), similarityRating);
            
            if(ratingsNum >= minimalRaters){
                
                for(Rating r : similarityRating) {
                    Rater rater = RaterDatabase.getRater(r.getItem());
                    if(rater.hasRating(id)) {
                        allRating = allRating + rater.getRating(id)*r.getValue();
                    }
                }                
                
            }else{
               return average;
            }
        average = allRating / ratingsNum;
        return average;
    }    
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> aveRating = new ArrayList<Rating>();
        
        ArrayList<Rating> similarityRating = new ArrayList<Rating>(getSimilarities(id).subList(0,numSimilarRaters));
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        for(String movieID : movies) {            
            double average = getSimilarAverageByID(movieID,minimalRaters,similarityRating);
            if(average != 0.0) {
               Rating rating = new Rating(movieID, average);
               aveRating.add(rating);  
            }            
        }         
        
        Collections.sort(aveRating, Collections.reverseOrder());
        return aveRating;
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> aveRating = new ArrayList<Rating>();
        ArrayList<Rating> similarityRating = new ArrayList<Rating>(getSimilarities(id).subList(0,numSimilarRaters));
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        
        for(String movieID : movies) {            
            double average = getSimilarAverageByID(movieID,minimalRaters,similarityRating);
            if(average != 0.0) {
               Rating rating = new Rating(movieID, average);
               aveRating.add(rating);  
            }            
        }         
        
        Collections.sort(aveRating, Collections.reverseOrder());
        return aveRating;
    }    
    
    private int ratingsNum(String item, ArrayList<Rater> listOfRaters, ArrayList<Rating> similarityRating) {
        int ratingsNum = 0;
        for(Rater rater : listOfRaters) {
            if(rater.hasRating(item) && isInSimilar(rater, similarityRating)){
                ratingsNum ++;
            }
        }
        return ratingsNum;
    }    
    
    private boolean isInSimilar(Rater rater, ArrayList<Rating> similarityRating){

        for(Rating r : similarityRating){
            if(r.getItem().equals(rater.getID())){
                return true;
            }
        }
        return false;
    }
}

