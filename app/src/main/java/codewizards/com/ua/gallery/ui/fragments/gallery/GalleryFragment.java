package codewizards.com.ua.gallery.ui.fragments.gallery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import codewizards.com.ua.gallery.R;
import codewizards.com.ua.gallery.ui.fragments.BaseFragment;
import codewizards.com.ua.gallery.ui.fragments.gallery.recycler_view.ImageAdapter;
import codewizards.com.ua.gallery.util.StorageHelper;

/**
 * Created by Интернет on 19.01.2017.
 */

public class GalleryFragment extends BaseFragment {
    private RecyclerView rvImages;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        rvImages = (RecyclerView) view.findViewById(R.id.rvImages);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageAdapter imageAdapter = new ImageAdapter();
        imageAdapter.setImageList(StorageHelper.getAllImages(getActivity()));
        rvImages.setAdapter(imageAdapter);
        rvImages.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    }
}
