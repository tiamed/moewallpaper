package info.tiamed.MoeWallpaper.activity;

import android.content.Intent;
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import info.tiamed.MoeWallpaper.R;
import info.tiamed.MoeWallpaper.fragment.MainFragment;
import info.tiamed.MoeWallpaper.util.HttpUtil;
import info.tiamed.MoeWallpaper.util.InternetConnection;

public class MainActivity extends AppCompatActivity {


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
    HttpUtil util = new HttpUtil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this)
                .transparentNavigationBar()
                .init();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        util.get(1);
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
                    Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                    intent.putExtra("query", query);
                    startActivity(intent);
                }
                return true;
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
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPostBundle(Bundle bundle) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPostEvent(int i) {
        page += 1;
    }
}
