package codewizards.com.ua.gallery.util;

import android.media.MediaScannerConnection;
import android.net.Uri;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import codewizards.com.ua.gallery.GalleryApp;

/**
 * Created by User on 28.02.2017.
 */

public class FileHelper {

    private static Logger logger = Logger.getLogger(FileHelper.class);

    public static void saveFile(InputStream stream, File file) {
        try {
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

    public static void refreshContentProvider(String filePath) {
        MediaScannerConnection.scanFile(
                GalleryApp.getAppContext(),
                new String[]{ filePath },
                new String[]{ "*/*" },
                new MediaScannerConnection.MediaScannerConnectionClient() {
                    public void onMediaScannerConnected() {}
                    public void onScanCompleted(String path, Uri uri) {}
                });
    }
}
