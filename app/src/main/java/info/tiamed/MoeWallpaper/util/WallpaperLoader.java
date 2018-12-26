package info.tiamed.MoeWallpaper.util;

import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import info.tiamed.MoeWallpaper.R;

public class WallpaperLoader extends AsyncTask<Integer, Void, Boolean> {
    BitmapFactory.Options mOptions;
    ProgressDialog mDialog;
    private Context mContext;
    private ArrayList<Integer> sWallpapers;

    public WallpaperLoader(Context mContext, ArrayList<Integer> sWallpapers) {
        mOptions = new BitmapFactory.Options();
        mOptions.inDither = false;
        mOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
        this.mContext = mContext;
        this.sWallpapers = sWallpapers;
    }

    @Override
    protected Boolean doInBackground(Integer... params) {
        try {
            Bitmap b = BitmapFactory.decodeResource(mContext.getResources(),
                    sWallpapers.get(params[0]), mOptions);

            WallpaperManager wallpaperManager = WallpaperManager.getInstance(mContext);
            try {
                wallpaperManager.setBitmap(b);
            } catch (IOException e) {
                throw new NullPointerException();
            }
            b.recycle();
            return true;
        } catch (OutOfMemoryError e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean success) {
        mDialog.dismiss();
        Toast.makeText(mContext, mContext.getString(R.string.applied), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPreExecute() {
        mDialog = ProgressDialog.show(mContext, null, mContext.getString(R.string.applying));
    }
}
