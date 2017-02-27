package codewizards.com.ua.gallery.sections.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import codewizards.com.ua.gallery.sections.abs.BaseFragment;

/**
 * Created by User on 28.02.2017.
 */

public class FragmentGalleryPagerAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragments;
    private List<String> titles;

    public FragmentGalleryPagerAdapter(FragmentManager fm, List<BaseFragment> fragments, List<String> titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public int getCount() {
        return fragments != null ? fragments.size() : 0;
    }
}
