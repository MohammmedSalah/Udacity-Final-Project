package salah.seifeldin.com.moviesapp;

/**
 * Created by mOhammed on 20/04/2016.
 */
import android.os.Environment;
import android.widget.Toast;

import java.io.*;

public class Read_Write_File {

    public static String write_To_file(Object object, String file) throws IOException {
        String path = Environment.getExternalStorageDirectory() + File.separator + "Movies_App" + File.separator;
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        path += file;
        File data = new File(path);
        if (!data.createNewFile()) {
            data.delete();
            data.createNewFile();
        }
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(data));
        objectOutputStream.writeObject(object);
        objectOutputStream.close();
        return path;
    }

    public static Object read_From_file(String path) throws IOException, ClassNotFoundException {
        Object object = null;
        File data = new File(path);
        if(data.exists()) {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(data));
            object = objectInputStream.readObject();
            objectInputStream.close();
        }
        return object;
    }
}

