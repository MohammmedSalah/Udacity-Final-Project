package salah.seifeldin.com.moviesapp;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements Movie_List.Listener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Movie_Class.my_rate_movie[19]=Movie_Class.my_popular_movie[19] = new Movie_Class("__",null,null,null,0,null,0,null);

        String url="http://api.themoviedb.org/3/movie/popular?api_key=c0520a06c65f6f64fa310517d8daaf3a";
        setList(url, "data", "msa");


        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.myfavorite){
            Movie_Class m[] = new Movie_Class[Movie_Class.my_favorite_list.size()] ;
            //Toast.makeText(this.getApplicationContext(),"size =  "+  String.valueOf(Movie_Class.fav_id.size()) , Toast.LENGTH_SHORT).show();
            m = Movie_List.nn(Movie_Class.my_favorite_list) ;
            //Toast.makeText(getApplicationContext(),String.valueOf(Movie_Class.my_rate_movie[0].id_f) + "  " + String.valueOf(m[0].id_f) , Toast.LENGTH_SHORT).show();

            Movie_List.f2=true;
            setList("","","");
            Movie_Class.my_movie = m;
        }
        if(id == R.id.pop){
            String url="http://api.themoviedb.org/3/movie/popular?api_key=c0520a06c65f6f64fa310517d8daaf3a";
            setList(url, "data", "msa");
        }
        if(id == R.id.rat){
            String url = "http://api.themoviedb.org/3/movie/top_rated?api_key=c0520a06c65f6f64fa310517d8daaf3a";
            setList(url, "data2", "msa2");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            String s = Read_Write_File.write_To_file(Movie_Class.my_favorite_list, "f_fav");
            Movie_List.setPath(s,"msa_f");
            if(!Movie_Class.my_popular_movie[19].name.equals("__")){
            s = Read_Write_File.write_To_file(Movie_Class.my_popular_movie, "data");
            Movie_List.setPath(s, "msa");}
            if(!Movie_Class.my_rate_movie[19].name.equals("__")){
            s = Read_Write_File.write_To_file(Movie_Class.my_rate_movie, "data2");
            Movie_List.setPath(s, "msa2");}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void itemclicked(long id) {
        /*Intent intent = new Intent(this,Description.class) ;
        intent.putExtra("d", Movie_Class.my_movie[((int) id)].description);
        intent.putExtra("i", Movie_Class.my_movie[((int) id)].image_name);
        startActivity(intent);*/
        View fragmentContainer = findViewById(R.id.frag_container) ;
        if(fragmentContainer != null){
            Toast.makeText(this, "seifeldin salah ", Toast.LENGTH_LONG).show() ;
            Movie_Description details = new Movie_Description();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            details.setMovie_id(id);
            ft.replace(R.id.frag_container, details);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
        else {

            Intent intent = new Intent(this, Detail.class);
            intent.putExtra(Detail.EXTRA_Movie_ID, (int) id);
            startActivity(intent);
        }

    }
    public  void setList(String url,String file,String path){
        //View fragmentContainer = findViewById(R.id.list_frage) ;
        //Toast.makeText(this, "mohammed salah list", Toast.LENGTH_LONG).show() ;
        Movie_List movie_list = new Movie_List();

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        movie_list.setUrl(url, file, path);
        ft.replace(R.id.list_frage,movie_list);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();

    }
}
