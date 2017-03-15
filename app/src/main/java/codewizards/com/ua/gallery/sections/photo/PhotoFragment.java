package codewizards.com.ua.gallery.sections.photo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import codewizards.com.ua.gallery.R;
import codewizards.com.ua.gallery.sections.abs.BaseFragment;

/**
 * Created by User on 14.03.2017.
 */

public class PhotoFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_photo, container, false);
        return root;
    }
}
