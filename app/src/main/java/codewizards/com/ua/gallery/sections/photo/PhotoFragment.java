package codewizards.com.ua.gallery.sections.photo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

import codewizards.com.ua.gallery.R;
import codewizards.com.ua.gallery.sections.abs.BaseFragment;
import codewizards.com.ua.gallery.util.FileHelper;

/**
 * Created by User on 14.03.2017.
 */

public class PhotoFragment extends BaseFragment {

    private static final int CAMERA_REQUEST = 1888;

    private FloatingActionButton fabPhoto;
    private ImageView ivPhoto;
    private TextView tvNoPhoto;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_photo, container, false);
        fabPhoto = (FloatingActionButton) root.findViewById(R.id.fabPhoto);
        ivPhoto = (ImageView) root.findViewById(R.id.ivPhoto);
        tvNoPhoto = (TextView) root.findViewById(R.id.tvNoPhoto);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fabPhoto.setOnClickListener(v -> {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            if(photo != null) {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
                byte[] bitmapdata = bos.toByteArray();
                ByteArrayInputStream bs = new ByteArrayInputStream(bitmapdata);
                String name = "photo.jpg";
                tvNoPhoto.setVisibility(View.GONE);
                ivPhoto.setImageBitmap(photo);
                File dirPics = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                File file = new File(dirPics, name);
                FileHelper.saveFile(bs, file);
                FileHelper.refreshContentProvider(file.getAbsolutePath());
            }
        }
    }
}
