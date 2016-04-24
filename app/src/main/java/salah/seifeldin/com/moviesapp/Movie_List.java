package salah.seifeldin.com.moviesapp;


import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Movie_List extends Fragment {

    ArrayList<Movie_Class> listmovieclass ;


    public static boolean p,r,f=false,f2=false;
    public static String url,file,sh_path ;
    public static boolean fin= false ;
    public static Context c ;
    public static MY_Adapter my_adapter;
    static GridView mgridview ;

    public static interface Listener {
         void itemclicked(long id) ;
    }


    private Listener listener ;
    Intent intent;
    public Movie_List() {
        // Required empty public constructor
    }


    @Override
    public void onStart() {
        super.onStart();
        /*Json_RealData json_realData = new Json_RealData() ;
        json_realData.execute();

        json_realData.setOnNetwrokResponse(new OnNetworkResponse() {
            @Override
            public void OnResponse(ArrayList<Movie_Class> m) {
                Movie_Class.setReailData(m);
            }

            @Override
            public void OnFail() {

            }
        });*/

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        c = getActivity().getApplicationContext();
        View view =inflater.inflate(R.layout.fragment_movie__list, container, false) ;
        TextView textView =(TextView) view.findViewById(R.id.kind);
        mgridview = (GridView) view.findViewById(R.id.mygridview) ;


        p=r=false;
        if(fin == false){
            url="http://api.themoviedb.org/3/movie/popular?api_key=c0520a06c65f6f64fa310517d8daaf3a";
            file="data";
            sh_path=  "msa";
            fin=true;
        }


        /*Movie_Class a[]= new Movie_Class[my_fav_movie.size()] ;
        a = my_fav_movie.toArray(a);*/


        /*Json_RealData json_realData = new Json_RealData() ;
        json_realData.execute();*/

        if(f2){
            setadapter(nn(Movie_Class.my_favorite_list));
            textView.setText("My Favorite");
            f2=false;
        }
        else {
            if (isNetworkAvailable()) {

                //Toast.makeText(c,"started  ",Toast.LENGTH_SHORT).show() ;
                if (file.equals("data") && p) {
                    setadapter(Movie_Class.my_popular_movie);
                    Movie_Class.my_movie = Movie_Class.my_popular_movie;
                    textView.setText("Most popular");
                } else if (file.equals("data2") && r) {
                    setadapter(Movie_Class.my_rate_movie);
                    Movie_Class.my_movie = Movie_Class.my_rate_movie;
                    textView.setText("Top Rated");
                } else {
                    Json_RealData json_realData = new Json_RealData();
                    json_realData.execute(url);
                    json_realData.setOnNetwrokResponse(new OnNetworkResponse() {
                        @Override
                        public void OnResponse(ArrayList<Movie_Class> m) throws IOException {
                            Movie_Class[] name = new Movie_Class[m.size()];
                            name = nn(m);
                            setadapter(name);
                            if (file.equals("data")) {
                                p = true;
                                Movie_Class.my_movie = Movie_Class.my_popular_movie = name;
                            } else {
                                r = true;
                                Movie_Class.my_movie = Movie_Class.my_rate_movie = name;
                            }
                            //Toast.makeText(c,"ize =========  " + s,Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void OnFail() {

                        }
                    });
                }
                }else{
                    Toast.makeText(c, "No internet access ! this old data   ", Toast.LENGTH_LONG).show();
                    String s2;
                    ArrayList<Movie_Class> m = new ArrayList<Movie_Class>();

            /*SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
            s2 = preferences.getString("mohammed", "");*/

                    s2 = PreferenceManager.getDefaultSharedPreferences(c).getString(sh_path, "_");
                    //Toast.makeText(c,s2,Toast.LENGTH_SHORT).show();
                    if (!s2.equals("_")) {
                        try {
                            Movie_Class.my_movie = (Movie_Class[]) Read_Write_File.read_From_file(s2);

                        } catch (IOException e) {

                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    //Toast.makeText(c,String.valueOf(m.size()),Toast.LENGTH_SHORT).show();
                    //Movie_Class.my_movie = (m);
                    this.setadapter(Movie_Class.my_movie);

                }
            }

         /*my_adapter = new MY_Adapter(getContext(),R.layout.row,Movie_Class.my_movie) ;

        if(Movie_Class.sss==true)
            mgridview.setAdapter(my_adapter);*/


        //intent = new Intent(getActivity(),Description.class) ;

        mgridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                /*Toast.makeText(MainActivity.this, my_movie[position].name,
                        Toast.LENGTH_SHORT).show();*/
                /*intent.putExtra("d", Movie_Class.my_movie[position].description);
                intent.putExtra("i", Movie_Class.my_movie[position].image_name);
                startActivity(intent);*/
                if (listener != null)
                    listener.itemclicked(id);


            }
        });

        return view ;

    }


    public static void return_fav(){
        String s = PreferenceManager.getDefaultSharedPreferences(c).getString("msa_f","_");

        if( !s.equals("_")) {

            try {
                Movie_Class.my_favorite_list = (ArrayList<Movie_Class>) Read_Write_File.read_From_file(s);
                Toast.makeText(c,String.valueOf(Movie_Class.my_favorite_list.size()),Toast.LENGTH_SHORT).show();
            } catch (IOException e) {


            } catch (ClassNotFoundException e) {
                e.printStackTrace();

            }
        }
    }
    public static void setPath(String s,String sh_path){
        /*SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(c);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("mohammed",s);
        editor.apply();*/

        //Toast.makeText(c,s,Toast.LENGTH_SHORT).show();
        PreferenceManager.getDefaultSharedPreferences(c).edit().putString(sh_path,s).commit();
    }


    private boolean isNetworkAvailable() {
        Context context  = getActivity().getApplicationContext();
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public static Movie_Class [] nn(ArrayList<Movie_Class> l ){
        Movie_Class [] s = new Movie_Class[l.size()] ;
        for (int i=0 ; i<l.size() ; i++){
            s[i] = l.get(i) ;
        }

        return  s ;
    }

    public static void setadapter(Movie_Class m[]){
        if(f==false){
            return_fav();
            f=true ;
        }
        my_adapter = new MY_Adapter(c, R.layout.row, m);
        mgridview.setAdapter(my_adapter);
    }
    public void setUrl(String url, String file, String sh_path){
        this.url= url;
        this.file= file ;
        this.sh_path = sh_path;
        fin=true;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.listener = (Listener) activity ;
    }

}
