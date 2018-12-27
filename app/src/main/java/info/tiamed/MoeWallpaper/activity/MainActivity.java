package info.tiamed.MoeWallpaper.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.gyf.barlibrary.ImmersionBar;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import info.tiamed.MoeWallpaper.R;
import info.tiamed.MoeWallpaper.data.DataRequest;
import info.tiamed.MoeWallpaper.data.SearchData;
import info.tiamed.MoeWallpaper.data.SourceData;
import info.tiamed.MoeWallpaper.fragment.GalleryFragment;
import info.tiamed.MoeWallpaper.fragment.MainFragment;
import info.tiamed.MoeWallpaper.util.InternetConnection;

public class MainActivity extends AppCompatActivity implements DataRequest.RequsetCallback<SourceData> {


    @BindView(R.id.search_view)
    MaterialSearchView searchView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindColor(R.color.colorPrimary)
    int svColor;
    @BindColor(R.color.colorPrimaryDark)
    int pdColor;
    int page = 1;
    ArrayList<String> urls = new ArrayList<>();
    ArrayList<String> titles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this)
                .transparentNavigationBar()
                .init();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        getList();

        replaceFragment(new MainFragment());
        initSearchView();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        ImmersionBar.with(this).destroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_layout, fragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        Log.d("Menu", "Creating search view");
        return true;
    }

    public void initSearchView() {
        toolbar.setLogo(null);
        setSupportActionBar(toolbar);
        searchView.setVoiceSearch(false);
        searchView.setTextColor(pdColor);
        searchView.setHintTextColor(pdColor);
        searchView.setBackgroundColor(svColor);
        searchView.setCursorDrawable(R.drawable.color_cursor_white);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (InternetConnection.checkConnection(MainActivity.this)) {
                    replaceFragment(new GalleryFragment());
                    DataRequest dr = new DataRequest();
                    dr.searchList(MainActivity.this, query, page);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
            }

            @Override
            public void onSearchViewClosed() {
                getList();
            }
        });
    }

    public void getList() {
        if (InternetConnection.checkConnection(this)) {
            DataRequest dr = new DataRequest();
            dr.getWallpaperList(this, page);
        }
    }

    @Override
    public void onFinish(List<SourceData> data, List<SearchData.ResultsBean> search) {
        if (data != null) {
            data.forEach(datum -> urls.add(datum.getUrls().getRegular()));
            data.forEach(datum -> titles.add(datum.getUser().getUsername()));
        } else if (search != null) {
            search.forEach(result -> urls.add(result.getUrls().getRegular()));
            search.forEach(result -> titles.add(result.getUser().getUsername()));
        }
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("urls", urls);
        bundle.putStringArrayList("titles", titles);
        EventBus.getDefault().post(bundle);
    }

    @Override
    public void onError(String msg) {
        Log.e("source data", "failed");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPostEvent(Bundle bundle) {
    }
}
