package info.tiamed.MoeWallpaper.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.nukc.LoadMoreWrapper.LoadMoreAdapter;
import com.github.nukc.LoadMoreWrapper.LoadMoreWrapper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import info.tiamed.MoeWallpaper.R;
import info.tiamed.MoeWallpaper.adapter.PaletteGridAdapter;
import info.tiamed.MoeWallpaper.util.HttpUtil;

public class GalleryFragment extends Fragment {

    static int page = 1;
    ArrayList<String> urls = new ArrayList<String>();
    ArrayList<String> titles = new ArrayList<String>();
    ArrayList<String> urls_thumb = new ArrayList<String>();
    HttpUtil util = new HttpUtil();
    private RecyclerView mImageRecycler;
    private PaletteGridAdapter mPaletteGridAdapter;
    private Context mContext;
    private String tag = "";
    private String query = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View galleryView = inflater.inflate(R.layout.fragment_gallery, null);
        EventBus.getDefault().register(this);
        this.tag = getArguments().getString("tag");
        this.query = getArguments().getString("query");
        mContext = getActivity();
        mImageRecycler = galleryView.findViewById(R.id.gallery_item);
        mPaletteGridAdapter = new PaletteGridAdapter(getActivity(), urls_thumb, urls, titles);
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
                        page += 1;
                        if (tag == "get") {
                            util.get(page);
                        } else {
                            util.search(query, page);
                        }
                    }
                })
                .into(mImageRecycler);
        mPaletteGridAdapter.setOnItemClickListener((View view, int position) -> {
        });

        return galleryView;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPostBundle(Bundle bundle) {
        if (bundle != null && bundle.toString() != "Bundle[{}]") {
            ArrayList<String> urls_thumb_ = bundle.getStringArrayList("urls_thumb");
            ArrayList<String> urls_ = bundle.getStringArrayList("urls");
            ArrayList<String> titles_ = bundle.getStringArrayList("titles");
            String tag_ = bundle.getString("tag");
            if (urls != urls_ && tag == tag_) {
                urls.addAll(urls_);
                urls_thumb.addAll(urls_thumb_);
                titles.addAll(titles_);
                if (urls.size() != 0 && titles.size() != 0 && mPaletteGridAdapter != null) {
                    mPaletteGridAdapter.setData(urls_thumb, urls, titles);
                    mPaletteGridAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPostEvent(int i) {
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onQuery(String query) {
        this.query = query;
    }

    public void clear() {
        urls.clear();
        urls_thumb.clear();
        titles.clear();
    }
}
