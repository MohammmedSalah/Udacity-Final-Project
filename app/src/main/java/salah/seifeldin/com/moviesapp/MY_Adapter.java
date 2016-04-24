package salah.seifeldin.com.moviesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mohammed on 01/04/2016.
 */
public class MY_Adapter extends ArrayAdapter<Movie_Class> {

    public static Context mycontext ;
    int layout_resource_id ;
    Movie_Class mydata[] = null;
    static int res_id_fav ;
    static int res_id_nfav ;
    static ImageView imageView2;
    //String url = "http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg" ;

    public MY_Adapter(Context context, int resource, Movie_Class[] objects) {
        super(context, resource, objects);
        this.mycontext = context ;
        this.layout_resource_id = resource ;
        mydata = objects  ;
        res_id_fav =MY_Adapter.mycontext.getResources().getIdentifier("fav", "drawable", MY_Adapter.mycontext.getPackageName()) ;
        res_id_nfav = MY_Adapter.mycontext.getResources().getIdentifier("nfav", "drawable", MY_Adapter.mycontext.getPackageName()) ;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView ;
        LayoutInflater inflater = LayoutInflater.from(mycontext) ;
        row = inflater.inflate(layout_resource_id,parent,false) ;

        ImageView imageView = (ImageView) row.findViewById(R.id.imageView_image) ;
        final Movie_Class temp = mydata[position] ;


        //int res_id = mycontext.getResources().getIdentifier(temp.image_name,"drawable",mycontext.getPackageName()) ;
        /*imageView.setImageResource(res_id);*/
        Picasso.with(mycontext).load(temp.image_name).into(imageView);

        final int res_id_fav = mycontext.getResources().getIdentifier("fav","drawable",mycontext.getPackageName()) ;
        final int res_id_nfav = mycontext.getResources().getIdentifier("nfav","drawable",mycontext.getPackageName()) ;
        imageView2 = (ImageView) row.findViewById(R.id.imageView_fav);
        imageView2.setImageResource(contain(temp) ? res_id_fav:res_id_nfav  );
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_fav(temp);
            }
        });

        return row ;
    }
    public static void set_fav(Movie_Class temp){

        if (contain(temp)) {
            //Movie_Class.my_movie[((int) movie_id)].fav_name = "fav";

            imageView2.setImageResource(res_id_nfav);
            Movie_Class.my_favorite_list.remove(temp) ;
            Movie_List.my_adapter.notifyDataSetChanged();
        } else {
            imageView2.setImageResource(res_id_fav);
            Movie_Class.my_favorite_list.add(temp);
            Movie_List.my_adapter.notifyDataSetChanged();
        }
    }
    public static  boolean contain(Movie_Class a){

        for(int i=0 ;i< Movie_Class.my_favorite_list.size() ; i++){
            if(Movie_Class.my_favorite_list.get(i).id_f  == a.id_f)
                return true ;
        }
        return false ;

    }
}
