package info.tiamed.MoeWallpaper.adapter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import info.tiamed.MoeWallpaper.fragment.GalleryFragment;
import info.tiamed.MoeWallpaper.fragment.WebviewFragment;

public class TabPagerAdapter extends FragmentPagerAdapter {
    String[] homeTabs;
    Bundle bundle = new Bundle();

    public TabPagerAdapter(FragmentManager fm, String[] homeTabs) {
        super(fm);
        this.homeTabs = homeTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                GalleryFragment galleryFragment = new GalleryFragment();
                bundle.putString("tag", "get");
                galleryFragment.setArguments(bundle);
                return galleryFragment; //ChildFragment1 at position 0
            case 1:
                return new WebviewFragment(); //ChildFragment2 at position 1
        }
        return null; //does not happen
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = homeTabs[position];
        return title.subSequence(title.lastIndexOf(".") + 1, title.length());
    }
}
