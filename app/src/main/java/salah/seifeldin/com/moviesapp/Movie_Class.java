package salah.seifeldin.com.moviesapp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mohammed on 01/04/2016.
 */
public class Movie_Class implements Serializable{
    String name ;                //original_title
    String description ;        //overview
    String image_name ;        //poster_path
    String backdrop_path ;
    double vote_average ;
    String release_date ;
    int id_f ;

    String fav_name ;

    public static Movie_Class my_movie[] = new Movie_Class[20];
    public static Movie_Class my_rate_movie[] = new Movie_Class[20];
    public static Movie_Class my_popular_movie[] = new Movie_Class[20];
    public static  ArrayList<Movie_Class> my_favorite_list  = new ArrayList<Movie_Class>();
    //public static Movie_Class my_hr[] = new Movie_Class[20] ;
    //public static ArrayList<Integer> fav_id  = new ArrayList<Integer>();
    /*public static Movie_Class my_movie[] = {new Movie_Class("Fistdfdgfdgdfgdfd fgdfgFilm","this is action film ","a_1","nfav"),
            new Movie_Class("Second Film","this is comedy film","a_2","nfav"),
            new Movie_Class("Third Film","this is imaginary film","a_3","nfav"),
            new Movie_Class("Fourth Film","this is normal film","a_4","nfav"),
            new Movie_Class("Fourth Film","this is normal film","a_4","nfav"),
            new Movie_Class("Fourth Film","this is normal film","a_4","nfav"),
            new Movie_Class("Fourth Film","this is normal film","a_4","nfav"),
            new Movie_Class("Fourth Film","this is normal film","a_4","nfav"),
            new Movie_Class("Fourth Film","this is normal film","a_4","nfav"),
            new Movie_Class("Fourth Film","this is normal film","a_4","nfav"),
            new Movie_Class("Fourth Film","this is normal film","a_4","nfav"),
            new Movie_Class("Fourth Film","this is normal film","a_4","nfav"),
            new Movie_Class("Fourth Film","this is normal film","a_4","nfav"),
            new Movie_Class("Fourth Film","this is normal film","a_4","nfav"),
            new Movie_Class("Fourth Film","this is normal film","a_4","nfav"),
            new Movie_Class("Fourth Film","this is normal film","a_4","nfav"),
            new Movie_Class("Fourth Film","this is normal film","a_4","nfav"),
            new Movie_Class("Fourth Film","this is normal film","a_4","nfav"),
            new Movie_Class("Fourth Film","this is normal film","a_4","nfav"),
            new Movie_Class("Fourth Film","this is normal film","a_4","nfav"),} ;*/

    public static ArrayList<Movie_Class> myfavorite  = new ArrayList<Movie_Class>();

    public Movie_Class(String name, String description, String image_name ,String backdrop_path,double vote_average,String release_date,int id_f, String fav_name) {
        this.name = name;
        this.description = description;
        this.image_name = image_name;
        this.backdrop_path = backdrop_path ;
        this.vote_average = vote_average ;
        this.release_date = release_date ;
        this.id_f = id_f ;
        this.fav_name = fav_name ;
    }
    /*public static  boolean sss = false ;
    public static  void setReailData(ArrayList<Movie_Class> r){
        //r.clear();

        for(int i=0 ;i<r.size() ;i++){
            //my_movie[i] = r.get(i) ;
            my_movie[i].name = r.get(i).name ;
            my_movie[i].description = r.get(i).description ;
            my_movie[i].image_name = r.get(i).image_name ;
        }
        Movie_List.my_adapter.notifyDataSetChanged();
    }*/
}
