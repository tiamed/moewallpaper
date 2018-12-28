package info.tiamed.MoeWallpaper.activity;

import android.os.Bundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import info.tiamed.MoeWallpaper.R;
import info.tiamed.MoeWallpaper.fragment.GalleryFragment;
import info.tiamed.MoeWallpaper.util.HttpUtil;

public class SearchActivity extends AppCompatActivity {

    ArrayList<String> urls = new ArrayList<>();
    ArrayList<String> titles = new ArrayList<>();
    HttpUtil util = new HttpUtil();
    Bundle bundle = new Bundle();
    String query = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        EventBus.getDefault().register(this);
        query = getIntent().getStringExtra("query");
        EventBus.getDefault().post(query);
        util.search(query, 1);
        GalleryFragment searchFragment = new GalleryFragment();
        bundle.putString("tag", "search");
        bundle.putString("query", query);
        searchFragment.setArguments(bundle);
        replaceFragment(searchFragment);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.finish();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.search_layout, fragment);
        transaction.commit();
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onQuery(String query) {
    }
}
