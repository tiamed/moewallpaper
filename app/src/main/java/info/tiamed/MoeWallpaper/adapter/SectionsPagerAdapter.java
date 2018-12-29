package info.tiamed.MoeWallpaper.adapter;

import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import info.tiamed.MoeWallpaper.fragment.WallpaperFragment;

import java.util.ArrayList;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    ArrayList<String> urls = new ArrayList<>();
    ArrayList<String> titles = new ArrayList<>();

    public SectionsPagerAdapter(FragmentManager fm, ArrayList<String> urls, ArrayList<String> titles) {
        super(fm);
        this.urls = urls;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new WallpaperFragment();
        Bundle args = new Bundle();
        Log.d("getItem", "section number " + (i));
        args.putInt(WallpaperFragment.ARG_SECTION_NUMBER, i);
        args.putStringArrayList("urls", urls);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

}
