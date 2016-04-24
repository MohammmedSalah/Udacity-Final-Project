package salah.seifeldin.com.moviesapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by mohammed on 06/04/2016.
 */


public  class Json_RealData extends AsyncTask<String,Void,ArrayList<Movie_Class> > {


    ArrayList<Movie_Class> listmovieclass ;
    Movie_Class movie_class ;
    OnNetworkResponse onNetworkResponse;
    public void setOnNetwrokResponse(OnNetworkResponse onNetwrokResponse){
        this.onNetworkResponse=onNetwrokResponse;
    }


    @Override
    protected ArrayList<Movie_Class> doInBackground(String... params) {
        //ArrayList<String> arrayList = new ArrayList<String>() ;
        listmovieclass  = new ArrayList<Movie_Class>();
        HttpURLConnection httpURLConnection = null ;
        BufferedReader bufferedReader = null ;
        try{
            URL url = new URL(params[0]);
            httpURLConnection  = (HttpURLConnection) url.openConnection() ;
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            bufferedReader = new BufferedReader(new InputStreamReader(
                    httpURLConnection.getInputStream() )
            );
            StringBuffer stringBuffer  = new StringBuffer();
            String line = null ;
            while((line = bufferedReader.readLine() )!= null){
                stringBuffer.append(line) ;
            }
            String finaljson = stringBuffer.toString() ;
            JSONObject parentobject = new JSONObject(finaljson) ;
            JSONArray parentarray  = parentobject.getJSONArray("results") ;
            for(int i=0 ;i<parentarray.length() ; i++){
                JSONObject finalobject =  parentarray.getJSONObject(i) ;
                //arrayList.add("http://image.tmdb.org/t/p/w500" + finalobject.getString("poster_path"));
                listmovieclass.add(new Movie_Class(finalobject.getString("original_title"),
                        finalobject.getString("overview"),
                        ("http://image.tmdb.org/t/p/w185/" + finalobject.getString("poster_path")),
                        ("http://image.tmdb.org/t/p/w185/" + finalobject.getString("backdrop_path")),
                        finalobject.getDouble("vote_average"),
                        finalobject.getString("release_date"),
                        finalobject.getInt("id"),
                        "nfav")) ;
            }


        }
        catch(MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        finally {
            if(httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            try {
                if( bufferedReader!= null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        //return arrayList;
        return listmovieclass ;
    }

    @Override
    protected void onPostExecute(ArrayList<Movie_Class> strings) {
        super.onPostExecute(strings);

        String path=null;
        //save(strings);
        //Movie_Class.setReailData(strings);
        //Movie_List.setPath("yyyyyyyyyyyyyyyyy");
        /*try {
            Movie_List.setPath("---------------------");
            path = Read_Write_File.write_To_file(strings);
            Movie_List.setPath(path);
            Movie_List.setPath("Server serv server");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //Movie_List.setPath(path);
        try {
            onNetworkResponse.OnResponse(strings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



