package codewizards.com.ua.gallery.sections.favorites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import codewizards.com.ua.gallery.R;
import codewizards.com.ua.gallery.model.ui.GalleryImage;
import codewizards.com.ua.gallery.mvp.PresenterCache;
import codewizards.com.ua.gallery.mvp.PresenterFactory;
import codewizards.com.ua.gallery.sections.abs.BaseFragment;
import codewizards.com.ua.gallery.sections.gallery.OnImageActionListener;
import codewizards.com.ua.gallery.sections.ui.ImageAdapter;
import codewizards.com.ua.gallery.util.Const;

/**
 * Created by User on 27.02.2017.
 */

public class FavoritesFragment extends BaseFragment implements OnImageActionListener {

    private RecyclerView rvFavoriteImages;

    private FavoritesFragmentPresenter presenter;
    private PresenterFactory<FavoritesFragmentPresenter> factory = FavoritesFragmentPresenter::new;
    private ImageAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favorites, container, false);
        rvFavoriteImages = (RecyclerView) root.findViewById(R.id.rvFavoriteImages);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = PresenterCache.get().getPresenter(Const.PRESENTER_FAVORITES_FRAGMENT, factory);
        presenter.setView(this);
        initRecyclerView();
        presenter.update();
    }

    public void initRecyclerView() {
        adapter = new ImageAdapter(this);
        rvFavoriteImages.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvFavoriteImages.setAdapter(adapter);
    }

    public void updateImages(List<GalleryImage> images) {
        adapter.setImageList(images);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onImageSelected(GalleryImage image) {

    }

    @Override
    public void onImageFavorite(GalleryImage image) {
        presenter.addToFavorite(image);
    }

    @Override
    public void onImageUnfavorite(GalleryImage image) {
        presenter.removeFromFavorites(image);
    }
}
