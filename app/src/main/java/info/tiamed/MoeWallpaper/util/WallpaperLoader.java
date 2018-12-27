package info.tiamed.MoeWallpaper.util;

import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import info.tiamed.MoeWallpaper.R;

import java.io.IOException;
import java.util.ArrayList;

public class WallpaperLoader extends AsyncTask<Integer, Void, Boolean> {
    BitmapFactory.Options mOptions;
    ProgressDialog mDialog;
    private Context mContext;
    private ArrayList<String> urls;

    public WallpaperLoader(Context mContext, ArrayList<String> urls) {
        mOptions = new BitmapFactory.Options();
        mOptions.inDither = false;
        mOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
        this.mContext = mContext;
        this.urls = urls;
    }

    @Override
    protected Boolean doInBackground(Integer... params) {
        try {
            Bitmap b = Glide.with(mContext).asBitmap().load(urls.get(params[0])).submit().get();
            b = b.copy(Bitmap.Config.ARGB_8888, true);
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
        } catch (Exception e) {
            e.printStackTrace();
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
