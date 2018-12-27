package info.tiamed.MoeWallpaper.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import info.tiamed.MoeWallpaper.R;
import info.tiamed.MoeWallpaper.adapter.PaletteGridAdapter;
import info.tiamed.MoeWallpaper.data.sourceData;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

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
        View loadMore = galleryView.findViewById(R.id.load_more);
        mPaletteGridAdapter = new PaletteGridAdapter(getActivity(), urls, titles);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        mImageRecycler.setLayoutManager(gridLayoutManager);
        mImageRecycler.setOnTouchListener((v, event) -> {return false; });
        mImageRecycler.setAdapter(mPaletteGridAdapter);
//        LoadMoreWrapper.with(mPaletteGridAdapter)
//                .setFooterView(loadMore)
//                .setListener(new LoadMoreAdapter.OnLoadMoreListener() {
//                    @Override
//                    public void onLoadMore(LoadMoreAdapter.Enabled enabled) {
//
//                    }
//                    })
//                            .into(mImageRecycler);
        mPaletteGridAdapter.setOnItemClickListener((View view, int position) -> {
            Toast.makeText(getActivity(), position, Toast.LENGTH_LONG).show();
        });


        return galleryView;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPostEvent(List<sourceData> data) {
        data.forEach(sourceData -> urls.add(sourceData.getUrls().getRegular()));
        data.forEach(sourceData -> titles.add(sourceData.getUser().getUsername()));
        mPaletteGridAdapter.notifyDataSetChanged();
    }
}
