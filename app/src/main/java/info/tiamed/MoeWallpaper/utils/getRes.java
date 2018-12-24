package info.tiamed.MoeWallpaper.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;

import info.tiamed.MoeWallpaper.R;

public class getRes {
    private static String[] mWallpaperInfo;
    private static ArrayList<Integer> mWallpapers = new ArrayList<Integer>();
    private Context mcontext;
    private Resources resources;
    private String packageName;

    public getRes(Context mContext) {
        setMcontext(mContext);
        resources = getMcontext().getResources();
        packageName = ( (Activity) mcontext).getApplication().getPackageName();
        fetchWallpapers(getResources(), getPackageName(), R.array.wallpapers);
        setmWallpaperInfo(getResources().getStringArray(R.array.info));
    }

    public static String[] getmWallpaperInfo() {
        return mWallpaperInfo;
    }

    public static void setmWallpaperInfo(String[] mWallpaperInfo) {
        getRes.mWallpaperInfo = mWallpaperInfo;
    }

    public static ArrayList<Integer> getmWallpapers() {
        return mWallpapers;
    }

    public static void setmWallpapers(ArrayList<Integer> mWallpapers) {
        getRes.mWallpapers = mWallpapers;
    }

    public String getPackageName() {
        return packageName;
    }

    public Resources getResources() {
        return resources;
    }

    public Context getMcontext() {
        return mcontext;
    }

    public void setMcontext(Context mcontext) {
        this.mcontext = mcontext;
    }

    private void fetchWallpapers(Resources resources, String packageName, int list) {
        final String[] extras = resources.getStringArray(list);
        ArrayList<Integer> Wallpapers = new ArrayList<Integer>();
        for (String extra : extras) {
            int res = resources.getIdentifier(extra, "drawable", packageName);
            if (res != 0) {
                Wallpapers.add(res);
            }
        }
        setmWallpapers(Wallpapers);
    }
}
