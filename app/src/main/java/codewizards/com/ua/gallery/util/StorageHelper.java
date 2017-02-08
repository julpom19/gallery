package codewizards.com.ua.gallery.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import codewizards.com.ua.gallery.model.Image;

/**
 * Created by madless on 08.02.2017.
 */
public class StorageHelper {
    static public List<Image> getAllImages(Context context) {
        List<Image> imageList = new ArrayList<>();
        // which image properties are we querying
        String[] projection = new String[] {
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Images.Media.DATE_TAKEN,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATA
        };

// content:// style URI for the "primary" external storage volume
        Uri images = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

// Make the query.
        Cursor cur = context.getContentResolver().query(images,
                projection, // Which columns to return
                null,       // Which rows to return (all rows)
                null,       // Selection arguments (none)
                null        // Ordering
        );

        Log.i("ListingImages"," query count=" + cur.getCount());

        if (cur.moveToFirst()) {
            String bucket;
            long date;
            String name;
            String data;
            int bucketColumn = cur.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);

            int dateColumn = cur.getColumnIndex(MediaStore.Images.Media.DATE_TAKEN);

            int nameColumn = cur.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME);

            int dataColumn = cur.getColumnIndex(MediaStore.Images.Media.DATA);

            do {
                // Get the field values
                bucket = cur.getString(bucketColumn);
                date = cur.getLong(dateColumn);
                name = cur.getString(nameColumn);
                data = cur.getString(dataColumn);
                imageList.add(new Image(data, name, date));

                // Do something with the values.
                Log.i("ListingImages", " bucket=" + bucket
                        + "  date_taken=" + date + " name=" + name + " data=" + data);
            } while (cur.moveToNext());

        }
        return imageList;
    }
}