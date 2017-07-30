
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
public class MovieRunnerWithFilters {
    
    public void printAverageRatings () {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
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
    
    public void printAverageRatingsByYear() {       
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("number of raters: " + tr.getRaterSize());
        MovieDatabase.initialize();
        System.out.println("number of movies: " + MovieDatabase.size());
        
        Filter filter = new YearsAfterFilter(2000);
        ArrayList<Rating> listAve= tr.getAverageRatingsByFilter(20,filter);
        Collections.sort(listAve);
        System.out.println("found: " + listAve.size() + " moives");
        for(Rating rating : listAve) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem()) + " " + MovieDatabase.getTitle(rating.getItem()));
        }                
    }
    
    public void printAverageRatingsByGenre() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("number of raters: " + tr.getRaterSize());
        MovieDatabase.initialize();
        System.out.println("number of movies: " + MovieDatabase.size());
        
        Filter filter = new GenreFilter("Comedy");
        ArrayList<Rating> listAve= tr.getAverageRatingsByFilter(20,filter);
        Collections.sort(listAve);
        System.out.println("found: " + listAve.size() + " moives");
        for(Rating rating : listAve) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()) + "\n    " + MovieDatabase.getGenres(rating.getItem()));
        }        
    }
    
    public void printAverageRatingsByMinutes() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("number of raters: " + tr.getRaterSize());
        MovieDatabase.initialize();
        System.out.println("number of movies: " + MovieDatabase.size());
        
        Filter filter = new MinutesFilter(105,135);
        ArrayList<Rating> listAve= tr.getAverageRatingsByFilter(5,filter);
        Collections.sort(listAve);
        System.out.println("found: " + listAve.size() + " moives");
        for(Rating rating : listAve) {
            System.out.println(rating.getValue() + " Time: " + MovieDatabase.getMinutes(rating.getItem()) + " " + MovieDatabase.getTitle(rating.getItem()));
        }        
    }    

    public void printAverageRatingsByDirectors() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("number of raters: " + tr.getRaterSize());
        MovieDatabase.initialize();
        System.out.println("number of movies: " + MovieDatabase.size());
        
        Filter filter = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        ArrayList<Rating> listAve= tr.getAverageRatingsByFilter(4,filter);
        Collections.sort(listAve);
        System.out.println("found: " + listAve.size() + " moives");
        for(Rating rating : listAve) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()) + "\n    " + MovieDatabase.getDirector(rating.getItem()));
        }        
    }  
    
    public void printAverageRatingsByYearAfterAndGenre() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
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
    
    public void printAverageRatingsByDirectorsAndMinutes() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("number of raters: " + tr.getRaterSize());
        MovieDatabase.initialize();
        System.out.println("number of movies: " + MovieDatabase.size());
        
        AllFilters filter = new AllFilters();
        filter.addFilter(new MinutesFilter(90,180));
        filter.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
        ArrayList<Rating> listAve= tr.getAverageRatingsByFilter(3,filter);
        Collections.sort(listAve);
        System.out.println("found: " + listAve.size() + " moives");
        for(Rating rating : listAve) {
            System.out.println(rating.getValue() + " Time: " + MovieDatabase.getMinutes(rating.getItem()) + " " + MovieDatabase.getTitle(rating.getItem()) + "\n    " + MovieDatabase.getDirector(rating.getItem()));
        }                 
    }    
    
}
