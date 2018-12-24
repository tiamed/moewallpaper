package info.tiamed.MoeWallpaper.adapters;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import info.tiamed.MoeWallpaper.fragments.WallpaperFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    String[] mWallpaperInfo;
    public SectionsPagerAdapter(FragmentManager fm, String[] mWallpaperInfo) {
        super(fm);
        this.mWallpaperInfo = mWallpaperInfo;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new WallpaperFragment();
        Bundle args = new Bundle();
        Log.d("getItem", "section number "+ (i));
        args.putInt(WallpaperFragment.ARG_SECTION_NUMBER, i);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return mWallpaperInfo.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mWallpaperInfo[position];
    }

}
