package codewizards.com.ua.gallery.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import codewizards.com.ua.gallery.GalleryApp;

/**
 * Created by kmikhailovskiy on 15.02.2016.
 */
public class FileUtils {
    public final static String ASSETS_ROOT = "file:///android_asset/";
    public static final String TEMP_SUBDIRECTORY = "/.temp/";
    public static final String ASSETS_PAGES_DIR = "pages";
    public static final String PATH_DIV = "/";

    public static File getCacheDir() {
        return GalleryApp.getAppContext().getExternalCacheDir();
    }

    public static AssetManager getAssets() {
        return GalleryApp.getAppContext().getAssets();
    }

    public static File createCacheFile(String name) {
        return createFileIfNotExist(getCacheDir(), name);
    }

    private static File createFileIfNotExist(File dir, String name) {
        return createFile(new File(dir, name));
    }

    private static File createFile(File file) {
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    Log.w("FileUtils", "you can't override directory with file");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public static void writeToFile(String data, File file) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public static String readFromFile(File file) {
        String res = "";
        try {
            InputStream inputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String receiveString;
            StringBuilder stringBuilder = new StringBuilder();
            while ( (receiveString = bufferedReader.readLine()) != null ) {
                stringBuilder.append(receiveString);
            }
            inputStream.close();
            res = stringBuilder.toString();
        }
        catch (FileNotFoundException e) {
            Log.e(Const.TAG, "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e(Const.TAG, "Can not read file: " + e.toString());
        }
        return res;
    }

    public static void close(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> getFileLinesFromAssets(Context context,
                                                      String assetsPath) {
        InputStream is = null;
        List<String> buf = null;
        BufferedReader br = null;

        try {
            if (assetsPath.startsWith(ASSETS_ROOT)) {
                int start = ASSETS_ROOT.length();
                int end = assetsPath.length();
                assetsPath = assetsPath.substring(start, end);
            }

            is = context.getAssets().open(assetsPath);
            buf = new ArrayList<>();
            br = new BufferedReader(new InputStreamReader(is));

            String line = br.readLine();
            while (line != null) {
                buf.add(line);
                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.error(FileUtils.class,
                    "CAUGHT EXCEPTION WHEN READ FROM ASSETS: " + e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Logger.error(FileUtils.class,
                        "CAUGHT EXCEPTION WHEN CLOSING OPENED STREAMS: " + e);
            }
        }

        return buf;
    }

    public static boolean isFileExistInAssets(Context context, String assetsPath) {
        InputStream is = null;
        boolean exist = false;

        try {
            if (assetsPath.startsWith(ASSETS_ROOT)) {
                int start = ASSETS_ROOT.length();
                int end = assetsPath.length();
                assetsPath = assetsPath.substring(start, end);
            }

            is = context.getAssets().open(assetsPath);

            exist = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            FileUtils.close(is);
        }

        return exist;
    }

    public static boolean isFilePathToAssets(String filePath) {
        return filePath.startsWith(ASSETS_ROOT);
    }

    public static String trimAssetsPath(String filePath) {
        int start = ASSETS_ROOT.length();
        int end = filePath.length();

        return filePath.substring(start, end);
    }

    public static String getFileDataFromAssets(String fileName, Context context) {
        AssetManager manager = context.getAssets();
        InputStream fileStream = null;
        String data = null;
        try {
            fileStream = manager.open(fileName, AssetManager.ACCESS_BUFFER);
            data = IOUtils.toString(fileStream, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(fileStream);
        }
        return data;
    }

    public static String replaceHtmlSymbols(String str) {
        String res = str;
        if (res.contains("%2F")) {
            res = res.replace("%2F", "/");
        }

        if (res.contains("%3F")) {
            res = res.replace("%3F", "?");
        }

        if (res.contains("%3D")) {
            res = res.replace("%3D", "=");
        }

        if (res.contains("%26")) {
            res = res.replace("%26", "&");
        }

        if (res.contains("%2523")) {
            res = res.replace("%2523", "#");
        }
        return res;
    }

    public static void copyFile(File sourceFile, File destFile) {
        try {
            if (!destFile.exists()) {
                destFile.createNewFile();
            }
            FileChannel source = null;
            FileChannel destination = null;
            try {
                source = new FileInputStream(sourceFile).getChannel();
                destination = new FileOutputStream(destFile).getChannel();
                destination.transferFrom(source, 0, source.size());
            } finally {
                if (source != null) {
                    source.close();
                }
                if (destination != null) {
                    destination.close();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
