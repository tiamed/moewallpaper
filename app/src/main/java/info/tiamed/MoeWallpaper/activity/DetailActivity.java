package info.tiamed.MoeWallpaper.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import info.tiamed.MoeWallpaper.R;
import info.tiamed.MoeWallpaper.adapter.SectionsPagerAdapter;
import info.tiamed.MoeWallpaper.util.WallpaperLoader;

@SuppressLint("ParserError")
public class DetailActivity extends AppCompatActivity {

    private static final int MENU_APPLY = Menu.FIRST;
    private static Context context;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    ArrayList<String> urls = new ArrayList<>();
    ArrayList<String> titles = new ArrayList<>();
    private int mCurrentFragment;
    private int position;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        this.position = getIntent().getIntExtra("pos", 0);
        this.urls = getIntent().getStringArrayListExtra("urls");
        this.titles = getIntent().getStringArrayListExtra("titles");
        Log.d("DetailActivity", "extra value position: " + position);
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this)
                .transparentNavigationBar()
                .init();
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        context = this;
        if (urls != null && urls.size() != 0) {
            Log.d("detail activity", urls.toString());
            mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), urls, titles);
            mViewPager = findViewById(R.id.pager);
            mViewPager.setAdapter(mSectionsPagerAdapter);
            mViewPager.addOnPageChangeListener(new SimpleOnPageChangeListener() {
                public void onPageSelected(int position) {
                    mCurrentFragment = position;
                }
            });
            mViewPager.setCurrentItem(position);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    mSectionsPagerAdapter.notifyDataSetChanged();
                }
            });
        }

    }

    @OnClick(R.id.fab)
    public void onClick() {
        new WallpaperLoader(context, urls).execute(mCurrentFragment);
        Log.d("FAB", "clicked");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }
}