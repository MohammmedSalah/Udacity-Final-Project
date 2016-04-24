package salah.seifeldin.com.moviesapp;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by mohammed on 09/04/2016.
 */
public interface OnNetworkResponse {
    public void OnResponse(ArrayList<Movie_Class> m) throws IOException;
    public void OnFail();
}
