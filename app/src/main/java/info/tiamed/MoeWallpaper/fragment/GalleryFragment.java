package info.tiamed.MoeWallpaper.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import info.tiamed.MoeWallpaper.R;
import info.tiamed.MoeWallpaper.adapter.PaletteGridAdapter;

public class GalleryFragment extends Fragment {

    private RecyclerView mImageRecycler;
    private PaletteGridAdapter mPaletteGridAdapter;
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View galleryView = inflater.inflate(R.layout.fragment_gallery, null);
        ArrayList<String> urls = new ArrayList<String>();
        if (this.getArguments() != null) {
            urls = this.getArguments().getStringArrayList("urls");
        }
        mContext = getActivity();
        mImageRecycler = galleryView.findViewById(R.id.gallery_item);
        mPaletteGridAdapter = new PaletteGridAdapter(getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        mImageRecycler.setLayoutManager(gridLayoutManager);

        mImageRecycler.setOnTouchListener((v, event) -> {return false; });
        mImageRecycler.setAdapter(mPaletteGridAdapter);
        mPaletteGridAdapter.setOnItemClickListener((View view, int position) -> {
            Toast.makeText(getActivity(), position, Toast.LENGTH_LONG).show();
        });
        return galleryView;
    }
}
