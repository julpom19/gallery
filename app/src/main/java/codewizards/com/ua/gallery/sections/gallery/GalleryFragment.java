package codewizards.com.ua.gallery.sections.gallery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import codewizards.com.ua.gallery.R;
import codewizards.com.ua.gallery.model.Image;
import codewizards.com.ua.gallery.sections.BaseFragment;
import codewizards.com.ua.gallery.sections.gallery.recycler_view.ImageAdapter;
import codewizards.com.ua.gallery.util.IntentHelper;

/**
 * Created by Интернет on 19.01.2017.
 */

public class GalleryFragment extends BaseFragment implements OnImageSelectedListener {
    private static final String TAG = "GalleryFragment";
    private RecyclerView rvImages;
    ImageAdapter imageAdapter;
    GalleryFragmentPresenter presenter;

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
        imageAdapter = new ImageAdapter(this);
        rvImages.setAdapter(imageAdapter);
        rvImages.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        presenter = new GalleryFragmentPresenter();
        presenter.init(this, getActivity());
        presenter.update();
        setHasOptionsMenu(true);
    }

    public void onImagesUpdated(List<Image> images) {
        imageAdapter.setImageList(images);
        imageAdapter.notifyDataSetChanged();
    }

    @Override
    public void onImageSelected(Image image) {
        IntentHelper.openPictureActivity(getActivity(), image.getUrl());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.gallery_sort_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_sort_by_date: {
                presenter.sortByDate();
                return true;
            }
            case R.id.menu_item_sort_by_name: {
                presenter.sortByName();
                return true;
            }
            case R.id.menu_item_sort_by_date_rev: {
                presenter.sortByDateReversed();
                return true;
            }
            case R.id.menu_item_sort_by_name_rev: {
                presenter.sortByNameReversed();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
