package salah.seifeldin.com.moviesapp;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class Movie_Description extends Fragment {

    private long movie_id ;

    public static ImageView im_fav ;

    public void setMovie_id(long id) {
        this.movie_id = id;
    }

    public Movie_Description() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_movie__description, container, false);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if (view != null) {
            TextView textView = (TextView) view.findViewById(R.id.desc_textview);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView_desc);
            RatingBar ratingBar = (RatingBar) view.findViewById(R.id.rating);
            TextView data = (TextView) view.findViewById(R.id.date);
            TextView title = (TextView) view.findViewById(R.id.Title);

            final Movie_Class movie_class = Movie_Class.my_movie[(int) movie_id];
            Picasso.with(getActivity()).load(movie_class.backdrop_path).into(imageView);
            textView.setText(movie_class.description);
            data.setText(movie_class.release_date);
            ratingBar.setRating((float) movie_class.vote_average / 2);
            title.setText(movie_class.name);

            im_fav = (ImageView) view.findViewById(R.id.imageView2);

            //final int res_id_fav =MY_Adapter.mycontext.getResources().getIdentifier("fav", "drawable", MY_Adapter.mycontext.getPackageName()) ;
            //final int res_id_nfav = MY_Adapter.mycontext.getResources().getIdentifier("nfav", "drawable", MY_Adapter.mycontext.getPackageName()) ;
            //im_fav.setImageResource((movie_class.fav_name == "fav") ? res_id_fav : res_id_nfav);
            im_fav.setImageResource(MY_Adapter.contain(movie_class) ? MY_Adapter.res_id_fav : MY_Adapter.res_id_nfav);
            im_fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    set_fav(movie_class);
                }
            });


        }
    }
    public static void set_fav(Movie_Class temp){

        if (MY_Adapter.contain(temp)) {
            //Movie_Class.my_movie[((int) movie_id)].fav_name = "fav";

            im_fav.setImageResource(MY_Adapter.res_id_nfav);
            Movie_Class.my_favorite_list.remove(temp) ;
            Movie_List.my_adapter.notifyDataSetChanged();
        } else {
            im_fav.setImageResource(MY_Adapter.res_id_fav);
            Movie_Class.my_favorite_list.add(temp);
            Movie_List.my_adapter.notifyDataSetChanged();
        }
    }

}



