package info.tiamed.MoeWallpaper.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener;
import info.tiamed.MoeWallpaper.R;
import info.tiamed.MoeWallpaper.utils.getRes;
import info.tiamed.MoeWallpaper.adapters.SectionsPagerAdapter;
import info.tiamed.MoeWallpaper.utils.WallpaperLoader;

@SuppressLint("ParserError")
public class DetailActivity extends FragmentActivity {

    private static final int MENU_APPLY = Menu.FIRST;
    private static Context mContext;
    private static ArrayList<Integer> mWallpapers;
    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    String[] mWallpaperInfo;
    private int mCurrentFragment;
    private int position;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        position = getIntent().getIntExtra("pos", 0);
        Log.d("DetailActivity", "extra value position: " + position);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getRes getRes = new getRes(this);
        this.mWallpapers = getRes.getmWallpapers();
        this.mWallpaperInfo = getRes.getmWallpaperInfo();
        mContext = this;
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), this.mWallpaperInfo);
        mViewPager = findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new SimpleOnPageChangeListener() {
            public void onPageSelected(int position) {
                mCurrentFragment = position;
            }
        });
        mViewPager.setCurrentItem(position);

        mSectionsPagerAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_APPLY:
                new WallpaperLoader(mContext, mWallpapers).execute(mCurrentFragment);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, MENU_APPLY, 0, R.string.action_apply)
                .setIcon(R.drawable.format_paint_white)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        return super.onCreateOptionsMenu(menu);
    }


}