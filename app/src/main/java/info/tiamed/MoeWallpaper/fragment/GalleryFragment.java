package info.tiamed.MoeWallpaper.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.github.nukc.LoadMoreWrapper.LoadMoreAdapter;
import com.github.nukc.LoadMoreWrapper.LoadMoreWrapper;
import info.tiamed.MoeWallpaper.R;
import info.tiamed.MoeWallpaper.adapter.PaletteGridAdapter;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private RecyclerView mImageRecycler;
    private PaletteGridAdapter mPaletteGridAdapter;
    private Context mContext;
    ArrayList<String> urls = new ArrayList<String>();
    ArrayList<String> titles = new ArrayList<String>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View galleryView = inflater.inflate(R.layout.fragment_gallery, null);
        EventBus.getDefault().register(this);
        mContext = getActivity();
        mImageRecycler = galleryView.findViewById(R.id.gallery_item);
        if (urls.size() != 0 && titles.size() != 0) {
            mPaletteGridAdapter = new PaletteGridAdapter(getActivity(), urls, titles);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
            mImageRecycler.setLayoutManager(gridLayoutManager);
            mImageRecycler.setOnTouchListener((v, event) -> {
                return false;
            });
            mImageRecycler.setAdapter(mPaletteGridAdapter);
            LoadMoreWrapper.with(mPaletteGridAdapter)
                    .setFooterView(R.layout.view_footer)
                    .setListener(new LoadMoreAdapter.OnLoadMoreListener() {
                        @Override
                        public void onLoadMore(LoadMoreAdapter.Enabled enabled) {
                            EventBus.getDefault().post(1);
                        }
                    })
                    .into(mImageRecycler);
            mPaletteGridAdapter.setOnItemClickListener((View view, int position) -> {
            });
        }

        return galleryView;
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onPostEvent(Bundle bundle) {
        ArrayList<String> urls = bundle.getStringArrayList("urls");
        ArrayList<String> titles = bundle.getStringArrayList("titles");
        Log.e("GalleryFragment", urls.toString());
        if (urls.size() != 0 && titles.size() != 0 && mPaletteGridAdapter != null) {
            mPaletteGridAdapter.setData(urls, titles);
            Log.e("GalleryFragment inside", urls.toString());
            mPaletteGridAdapter.notifyDataSetChanged();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPostEvent(int i) {
    }
}
