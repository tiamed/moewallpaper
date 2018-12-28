package info.tiamed.MoeWallpaper.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import info.tiamed.MoeWallpaper.R;
import info.tiamed.MoeWallpaper.fragment.GalleryFragment;
import info.tiamed.MoeWallpaper.util.HttpUtil;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    ArrayList<String> urls = new ArrayList<>();
    ArrayList<String> titles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        EventBus.getDefault().register(this);
        String query = getIntent().getStringExtra("query");
        Bundle bundle = new HttpUtil().search(query, 1);
        GalleryFragment galleryFragment = new GalleryFragment();
        galleryFragment.setArguments(bundle);
        replaceFragment(galleryFragment);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.search_layout, fragment);
        transaction.commit();
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onPostEvent(Bundle bundle) {
    }
}
