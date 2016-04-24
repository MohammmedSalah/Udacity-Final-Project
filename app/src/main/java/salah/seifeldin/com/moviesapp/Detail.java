package salah.seifeldin.com.moviesapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class Detail extends AppCompatActivity {

    public static final String EXTRA_Movie_ID = "id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Movie_Description d =(Movie_Description) getFragmentManager().findFragmentById(R.id.detail_frag) ;

        int Movie_id = (int) getIntent().getExtras().get(EXTRA_Movie_ID);
        //Toast.makeText(this.getApplicationContext(),String.valueOf(Movie_id),Toast.LENGTH_SHORT).show();
        d.setMovie_id(Movie_id);
    }

}
