
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.awt.Image;
public class RecommendationRunner implements Recommender {
    
    public ArrayList<String> getItemsToRate () {        
        ArrayList<String> yearAfterMovies = MovieDatabase.filterBy(new YearsAfterFilter(2015));
        ArrayList<String> list = new ArrayList<String>(yearAfterMovies.subList(0,14));        
        return list;
    }
    
    public void printRecommendationsFor (String webRaterID) {
        FourthRatings tr = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");       
        ArrayList<Rating> listAve= tr.getSimilarRatings(webRaterID,10,5);      
        int numberToPrinted;
        
        if(listAve.size()==0){
            System.out.println("Sorry, there is no suitable movies to be recommened.");
            return;
        }else if(listAve.size()>15){
            numberToPrinted=15;
        }else {
            numberToPrinted=listAve.size();
        }
        
        
        System.out.println("There are some movies recommended to you:\n");
        System.out.println("<table style=\"width:100%\">");
        System.out.println("<tr>");
        System.out.println("<th></th>"); 
        System.out.println("<th>Movie Title</th>"); 
        System.out.println("<th>Year</th>"); 
        System.out.println("<th>Director</th>");
        System.out.println("</tr>");
        for(int k=0; k<numberToPrinted; k++) {            
            System.out.println("<tr>");
            System.out.println("<td>");
            String imgSrc = MovieDatabase.getPoster(listAve.get(k).getItem());
            imgSrc = imgSrc.replaceFirst("http://","data/");
            String s = "<img" + " " + "src=" + imgSrc + " " + "style=\"width:80px;height:100px;\"" + ">";
            System.out.println(s);           
            System.out.println("</td>");
            System.out.println("<td>");
            System.out.println(MovieDatabase.getTitle(listAve.get(k).getItem())); 
            System.out.println("</td>");
            System.out.println("<td>");
            System.out.println(MovieDatabase.getYear(listAve.get(k).getItem())); 
            System.out.println("</td>"); 
            System.out.println("<td>");
            System.out.println(MovieDatabase.getGenres(listAve.get(k).getItem())); 
            System.out.println("</td>");               
            System.out.println("</tr>");          

        }        
        System.out.println("</table>");  
        
        
        
        System.out.println("<style>");
        String str1 = "table, th, td {" + "\n" + "border: 1px solid black;}";
        System.out.println(str1);
        String str2 = "th, td {" + "\n" + "padding: 5px;}";
        System.out.println(str2);
        String str3 = "th{" + "\n" + "text-align: left;}";
        System.out.println(str3);
        System.out.println("</style>");

        
    }

}
