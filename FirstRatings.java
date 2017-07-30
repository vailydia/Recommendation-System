
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    
    
    public ArrayList<Movie> loadMovies(String filename) {
        ArrayList<Movie> listOfMovies = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord record : parser) {
            //String anID, String aTitle, String aYear, String theGenres, String aDirector,
            //String aCountry, String aPoster, int theMinutes
            Movie movie = new Movie(record.get("id"),record.get("title"),record.get("year"),record.get("genre"),
            record.get("director"),record.get("country"),record.get("poster"),
            Integer.parseInt(record.get("minutes")));
            listOfMovies.add(movie);
        }
        return listOfMovies;
    }
    
    public void testLoadMovies() {
        String filename = "data/ratedmoviesfull.csv";
        ArrayList<Movie> listOfMovies = loadMovies(filename);
        System.out.println("there are " + listOfMovies.size() + " movies in total.");
        /*
        for(Movie movie : listOfMovies) {
            System.out.println(movie);
        }
        */
        
        int numOfComedy=0;
        int numOfLongMovie=0;
        HashMap<String, Integer> MoviesNumByDirector = new HashMap<String, Integer>();
        for(Movie movie : listOfMovies) {
           //determine how many movies include the Comedy genre
            if(movie.getGenres().contains("Comedy")) {
               numOfComedy ++;               
           } 
           //determine how many movies are greater than 150 minutes in length.
           if(movie.getMinutes() > 150) {
               numOfLongMovie++;
           }
           //determine the maximum number of movies by any director, and who the directors are
           String director = movie.getDirector();
           String[] directors = movie.getDirector().trim().split("\\s*,\\s*");
           for(String dr : directors) {
               if(MoviesNumByDirector.containsKey(dr)){
                   MoviesNumByDirector.put(dr, MoviesNumByDirector.get(dr) + 1);
               } else {
                   MoviesNumByDirector.put(dr,1);
               }
           }           
        }
        System.out.println("There are " + numOfComedy + " Comedy movies. ");
        System.out.println("There are " + numOfLongMovie + " movies that are greater than 150 minutes in length. ");

        int maxMovies = findDrOfMaxMovies(MoviesNumByDirector);
        ArrayList<String> listOfDr = new ArrayList<String>();
        for(String dr : MoviesNumByDirector.keySet()) {
            if(MoviesNumByDirector.get(dr) == maxMovies) {
                listOfDr.add(dr);
            }
        }       
        System.out.println("The maximum number of movies by any director is " + maxMovies + ". These are " +
               listOfDr.size() + " directors:\n" +  listOfDr);
        
    }
    
    private int findDrOfMaxMovies(HashMap<String, Integer> MoviesNumByDirector) {
        int maxMovies=0;
        for(String dr : MoviesNumByDirector.keySet()) {
            if(MoviesNumByDirector.get(dr) > maxMovies){
                maxMovies = MoviesNumByDirector.get(dr);
            }
        }

        return maxMovies;
    }
    
    public ArrayList<Rater> loadRaters(String filename) {
        HashMap<String,Rater> myMap = new HashMap<String,Rater>();
        
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord record : parser) {        
            String rater_id = record.get("rater_id");
            if(!myMap.containsKey(rater_id)) {
                Rater ra = new EfficientRater(rater_id);
                ra.addRating(record.get("movie_id"),Double.parseDouble(record.get("rating")));
                myMap.put(rater_id,ra);                
            } else {
                Rater rater = myMap.get(rater_id);
                rater.addRating(record.get("movie_id"),Double.parseDouble(record.get("rating")));
            }
        }
        
        //hashMap to ArrayList
        ArrayList<Rater> listOfRaters = new ArrayList<Rater>();
        for(String raterID : myMap.keySet()){
            listOfRaters.add(myMap.get(raterID));
        }
        return listOfRaters;
    }    
   
    public void testLoadRaters() {

        String filename = "data/ratings.csv";
        ArrayList<Rater> listOfRaters = loadRaters(filename);
        System.out.println("there are " + listOfRaters.size() + " raters in total.");
        /*
        for(Rater rater : listOfRaters) {
            System.out.println("rater id is: " + rater.getID() + ", the number of ratings: " + rater.numRatings());
            ArrayList<String> itemsRated = rater.getItemsRated();
            for(String item : itemsRated) {
                System.out.printf(item + ": " + rater.getRating(item) + "; " );
            }
            System.out.println("\n___________");
        }
        */
        String rater_id = "193";
        int numberOfRatings = numberOfRatings(rater_id, listOfRaters);
        System.out.println("there are " + numberOfRatings + " ratings for rater_id = " + rater_id);
        Rater ra= maxRatings(listOfRaters);
        System.out.println("the maximum ratings is " + ra.numRatings() + ", the rater is: " + ra.getID());   
        String item = "0068646";
        int ratingsNum = ratingsNumOf(item, listOfRaters);
        System.out.println("the number of ratings that " + item + " has is " + ratingsNum);  
        int NumOfMoviesRated = NumOfMoviesRated(listOfRaters);
        System.out.println("the number of different movies rated ratings is " + NumOfMoviesRated); 
    }
    
    //find the number of ratings for a particular rater you specify 
    public int numberOfRatings(String rater_id, ArrayList<Rater> listOfRaters) {
        int numberOfRatings=-1;
        for(Rater rater : listOfRaters) {
            if(rater.getID().equals(rater_id)){
                numberOfRatings = rater.numRatings();
            }
        }
        return numberOfRatings;
    }

    //find the maximum number of ratings by any rater
    public Rater maxRatings(ArrayList<Rater> listOfRaters) {
        int maxRatings=-1;
        Rater re = null;
        for(Rater rater : listOfRaters) {
            if(rater.numRatings() > maxRatings){
                maxRatings = rater.numRatings();
                re=rater; 
            }
        }
        return re;
    }
    
    //find the number of ratings a particular movie has
    public int ratingsNumOf(String item, ArrayList<Rater> listOfRaters) {
        int ratingsNum=0;
        for(Rater rater : listOfRaters) {
            if(rater.hasRating(item)){
                ratingsNum ++;
            }
        }
        return ratingsNum;
    }
    
    //determine how many different movies have been rated by all these raters.
    public int NumOfMoviesRated(ArrayList<Rater> listOfRaters) {
        int NumOfMoviesRated=0;
        HashSet<String> movies = new HashSet<String>();
        for(Rater rater : listOfRaters) {
            movies.addAll(rater.getItemsRated());
        }
        return movies.size();
    }    
    
}
