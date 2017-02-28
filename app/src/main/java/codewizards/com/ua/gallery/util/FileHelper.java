package codewizards.com.ua.gallery.util;

import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by User on 28.02.2017.
 */

public class FileHelper {

    private static Logger logger = Logger.getLogger(FileHelper.class);

    public static void saveFile(InputStream stream, String name) {
        try {
            File dirDownloads = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File file = new File(dirDownloads, name);
            logger.d("dirDownloads: " + dirDownloads);
            logger.d("File: " + file.getAbsolutePath());
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
//            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
//            String line = bufferedReader.readLine();
//            while (line != null) {
//                bufferedWriter.write(line);
//                line = bufferedReader.readLine();
//            }
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            BufferedInputStream bufferedInputStream = new BufferedInputStream(stream);

            int c;
            while ((c = stream.read()) != -1) {
                //writes to the output Stream
                bufferedOutputStream.write(c);
            }
            bufferedInputStream.close();
            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
