
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
public class MovieRunnerAverage {
    
    public void printAverageRatings () {
        SecondRatings sr = new SecondRatings();
        System.out.println("number of movies: " + sr.getMovieSize());
        System.out.println("number of raters: " + sr.getRaterSize());
        
        ArrayList<Rating> listAve= sr.getAverageRatings(3);
        Collections.sort(listAve);
        for(Rating rating : listAve) {
            System.out.println(rating.getValue() + " " + sr.getTitle(rating.getItem()));
        }
    }

    public void getAverageRatingOneMovie() {
        String nameOfMovie = "The Maze Runner";
        SecondRatings sr = new SecondRatings();
        ArrayList<Rating> listAve= sr.getAverageRatings(3);
        for(Rating rating : listAve) {
            if(sr.getTitle(rating.getItem()).equals(nameOfMovie)){
                System.out.println(nameOfMovie + " " + rating.getValue());
            }
        }        
    }
 
    public void getAverageRating() {
        String[] nameOfMovie = {"Identity Thief","Spring Breakers","The Purge","The Hangover Part III","Mama"};
        SecondRatings sr = new SecondRatings();
        ArrayList<Rating> listAve= sr.getAverageRatings(3);
        for(String name : nameOfMovie) {
            for(Rating rating : listAve) {
                if(sr.getTitle(rating.getItem()).equals(name)){
                    System.out.println(name + " " + rating.getValue());
                }
            }            
        }
        
        //大于50个ratings的电影个数
        System.out.println(sr.high(50));
        
    }
}
