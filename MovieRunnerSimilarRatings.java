
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerSimilarRatings {
    
    public void printAverageRatings () {
        FourthRatings tr = new FourthRatings("ratings.csv");
        System.out.println("number of raters: " + tr.getRaterSize());
        MovieDatabase.initialize();
        System.out.println("number of movies: " + MovieDatabase.size());
        
        ArrayList<Rating> listAve= tr.getAverageRatings(35);
        Collections.sort(listAve);
        System.out.println("found: " + listAve.size() + " moives");
        for(Rating rating : listAve) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }    
    
    public void printAverageRatingsByYearAfterAndGenre() {
        FourthRatings tr = new FourthRatings("ratings.csv");
        System.out.println("number of raters: " + tr.getRaterSize());
        MovieDatabase.initialize();
        System.out.println("number of movies: " + MovieDatabase.size());
        
        AllFilters filter = new AllFilters();
        filter.addFilter(new YearsAfterFilter(1990));
        filter.addFilter(new GenreFilter("Drama"));
        ArrayList<Rating> listAve= tr.getAverageRatingsByFilter(8,filter);
        Collections.sort(listAve);
        System.out.println("found: " + listAve.size() + " moives");
        for(Rating rating : listAve) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem()) + " " + MovieDatabase.getTitle(rating.getItem()) + "\n    " + MovieDatabase.getGenres(rating.getItem()));
        }                 
    }    

    //similar rating
    public void printSimilarRatings () {
        FourthRatings tr = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for" + RaterDatabase.size() + " raters");
        
        ArrayList<Rating> listAve= tr.getSimilarRatings(String.valueOf(71),20,5);
        System.out.println("found: " + listAve.size() + " moives");
        for(Rating rating : listAve) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printSimilarRatingsByGenre() {
        FourthRatings tr = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        AllFilters filter = new AllFilters();
        filter.addFilter(new GenreFilter("Mystery"));        
        ArrayList<Rating> listAve= tr.getSimilarRatingsByFilter(String.valueOf(964),20,5,filter);
        System.out.println("found: " + listAve.size() + " moives");
        for(Rating rating : listAve) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }                
    }    
 
    public void printSimilarRatingsByDirector() {
        FourthRatings tr = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        AllFilters filter = new AllFilters();
        filter.addFilter(new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));        
        ArrayList<Rating> listAve= tr.getSimilarRatingsByFilter(String.valueOf(120),10,2,filter);
        System.out.println("found: " + listAve.size() + " moives");
        for(Rating rating : listAve) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }                   
    } 
    
    public void printSimilarRatingsByGenreAndMinutes() {
        FourthRatings tr = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        AllFilters filter = new AllFilters();
        filter.addFilter(new MinutesFilter(80,160));        
        filter.addFilter(new GenreFilter("Drama")); 
        ArrayList<Rating> listAve= tr.getSimilarRatingsByFilter(String.valueOf(168),10,3,filter);
        System.out.println("found: " + listAve.size() + " moives");
        for(Rating rating : listAve) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }                   
    }     

    public void printSimilarRatingsByYearAfterAndMinutes() {
        FourthRatings tr = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        AllFilters filter = new AllFilters();
        filter.addFilter(new YearsAfterFilter(1975));
        filter.addFilter(new MinutesFilter(70,200)); 
        ArrayList<Rating> listAve= tr.getSimilarRatingsByFilter(String.valueOf(314),10,5,filter);
        System.out.println("found: " + listAve.size() + " moives");
        for(Rating rating : listAve) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }         
    }     
    
}
