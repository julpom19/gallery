package codewizards.com.ua.gallery.sections.images;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import codewizards.com.ua.gallery.R;
import codewizards.com.ua.gallery.sections.abs.BaseFragment;
import codewizards.com.ua.gallery.sections.favorites.FavoritesFragment;
import codewizards.com.ua.gallery.sections.gallery.GalleryFragment;
import codewizards.com.ua.gallery.sections.internet.InternetFragment;
import codewizards.com.ua.gallery.sections.ui.FragmentGalleryPagerAdapter;

/**
 * Created by User on 14.03.2017.
 */

public class ImagesFragment extends BaseFragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private FragmentGalleryPagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_images, container, false);
        viewPager = (ViewPager) root.findViewById(R.id.viewPager);
        tabLayout = (TabLayout) root.findViewById(R.id.tabLayout);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initFragments();
    }

    public void initFragments() {
        logger.d("initFragments()");
        GalleryFragment galleryFragment = new GalleryFragment();
        FavoritesFragment favoritesFragment = new FavoritesFragment();
        InternetFragment internetFragment = new InternetFragment();
        List<BaseFragment> fragments = new ArrayList<>(Arrays.asList(galleryFragment, favoritesFragment, internetFragment));
        List<String> titles = new ArrayList<>(Arrays.asList("Gallery", "Favorites", "Google search"));
        adapter = new FragmentGalleryPagerAdapter(getChildFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
