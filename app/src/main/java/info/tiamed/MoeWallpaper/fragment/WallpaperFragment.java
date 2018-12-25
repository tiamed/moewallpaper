package info.tiamed.MoeWallpaper.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import info.tiamed.MoeWallpaper.util.getRes;

public class WallpaperFragment extends Fragment {
    public static final String ARG_SECTION_NUMBER = "section_number";
    private static Context mContext;
    private static ArrayList<Integer> sWallpapers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mContext = getActivity();
        getRes getres = new getRes(mContext);
        sWallpapers = getres.getmWallpapers();
        Bundle args = getArguments();
        LinearLayout holder = new LinearLayout(mContext);
        holder.setLayoutParams(new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        ImageView img = new ImageView(mContext);
        img.setLayoutParams(new ViewGroup.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        img.setImageResource(sWallpapers.get(args.getInt(ARG_SECTION_NUMBER)));
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.addView(img);
        return holder;
    }
}