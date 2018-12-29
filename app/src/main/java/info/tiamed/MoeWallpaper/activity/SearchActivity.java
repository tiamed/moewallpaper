package info.tiamed.MoeWallpaper.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.gyf.barlibrary.ImmersionBar;
import info.tiamed.MoeWallpaper.R;
import info.tiamed.MoeWallpaper.fragment.GalleryFragment;
import info.tiamed.MoeWallpaper.util.HttpUtil;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    @BindView(R.id.toolbar_search)
    Toolbar toolbar;
    @BindString(R.string.results)
    String results;
    ArrayList<String> urls = new ArrayList<>();
    ArrayList<String> titles = new ArrayList<>();
    private HttpUtil util = new HttpUtil();
    private Bundle bundle = new Bundle();
    private String query = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ImmersionBar.with(this)
                .transparentNavigationBar()
                .init();
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        query = getIntent().getStringExtra("query");
        EventBus.getDefault().post(query);
        util.search(query, 1);
        toolbar.setTitle(results + query);
        GalleryFragment searchFragment = new GalleryFragment();
        bundle.putString("tag", "search");
        bundle.putString("query", query);
        searchFragment.setArguments(bundle);
        replaceFragment(searchFragment);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
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
