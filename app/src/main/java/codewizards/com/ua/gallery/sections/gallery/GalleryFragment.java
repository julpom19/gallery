package codewizards.com.ua.gallery.sections.gallery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.TimerTask;

import codewizards.com.ua.gallery.R;
import codewizards.com.ua.gallery.managers.SoundManager;
import codewizards.com.ua.gallery.model.ui.GalleryImage;
import codewizards.com.ua.gallery.mvp.PresenterCache;
import codewizards.com.ua.gallery.mvp.PresenterFactory;
import codewizards.com.ua.gallery.sections.abs.BaseFragment;
import codewizards.com.ua.gallery.sections.ui.ImageAdapter;
import codewizards.com.ua.gallery.util.Const;
import codewizards.com.ua.gallery.util.IntentHelper;

/**
 * Created by Интернет on 19.01.2017.
 */

public class GalleryFragment extends BaseFragment implements OnImageActionListener, SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView rvImages;
    private SwipeRefreshLayout swipeToRefresh;

    ImageAdapter imageAdapter;

    GalleryFragmentPresenter presenter;
    PresenterFactory<GalleryFragmentPresenter> factory = GalleryFragmentPresenter::new;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        logger.d("onCreateView()");
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        rvImages = (RecyclerView) view.findViewById(R.id.rvImages);
        swipeToRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageAdapter = new ImageAdapter(this);
        rvImages.setAdapter(imageAdapter);
        rvImages.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        swipeToRefresh.setOnRefreshListener(this);
        swipeToRefresh.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.colorAccentBlue));
        presenter = PresenterCache.get().getPresenter(Const.PRESENTER_GALLERY_FRAGMENT, factory);
        presenter.init(this, getActivity());
        presenter.start();
        setHasOptionsMenu(true);
    }

    public void onImagesUpdated(List<GalleryImage> images) {
        imageAdapter.setImageList(images);
        imageAdapter.notifyDataSetChanged();
        swipeToRefresh.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        swipeToRefresh.setRefreshing(true);
        swipeToRefresh.postDelayed(new TimerTask() {
            @Override
            public void run() {
                presenter.update();
            }
        }, 200);
    }

    @Override
    public void onImageSelected(GalleryImage image) {
        IntentHelper.openPictureActivity(getActivity(), image.getUrl());
    }

    @Override
    public void onImageFavorite(GalleryImage image) {
        presenter.addToFavorite(image);
        SoundManager.get().playLikeSound();
    }

    @Override
    public void onImageUnfavorite(GalleryImage image) {
        presenter.removeFromFavorites(image);
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
