package codewizards.com.ua.gallery.sections;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import codewizards.com.ua.gallery.R;
import codewizards.com.ua.gallery.util.Const;

/**
 * Created by User on 22.02.2017.
 */

public class PictureActivity extends AppCompatActivity {

    ImageView ivPicture;
    String picturePath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        ivPicture = (ImageView) findViewById(R.id.ivPicture);

        picturePath = getIntent().getStringExtra(Const.EXTRA_PHOTO_URL);

        Glide.with(this).load(picturePath).into(ivPicture);
    }
}
