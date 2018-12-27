package info.tiamed.MoeWallpaper.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;

public class WallpaperFragment extends Fragment {
    public static final String ARG_SECTION_NUMBER = "section_number";
    private static Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mContext = getActivity();
        Bundle args = getArguments();
        int position = args.getInt(ARG_SECTION_NUMBER);
        ArrayList<String> urls = args.getStringArrayList("urls");
        LinearLayout holder = new LinearLayout(mContext);
        holder.setLayoutParams(new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        ImageView img = new ImageView(mContext);
        img.setLayoutParams(new ViewGroup.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        holder.addView(img);
        Glide.with(img.getContext()).load(urls.get(position)).into(img);
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return holder;
    }
}