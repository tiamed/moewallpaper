package info.tiamed.MoeWallpaper.util;

import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.io.IOException;
import java.util.ArrayList;

import info.tiamed.MoeWallpaper.R;

public class WallpaperLoader extends AsyncTask<Integer, Void, Boolean> {
    BitmapFactory.Options mOptions;
    ProgressDialog mDialog;
    private Context context;
    private ArrayList<String> urls;

    public WallpaperLoader(Context context, ArrayList<String> urls) {
        mOptions = new BitmapFactory.Options();
        mOptions.inDither = false;
        mOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
        this.context = context;
        this.urls = urls;
    }

    @Override
    protected Boolean doInBackground(Integer... params) {
        try {
            Bitmap b = Glide.with(context)
                    .asBitmap()
                    .load(urls.get(params[0]))
                    .apply(new RequestOptions()
                            .format(DecodeFormat.PREFER_ARGB_8888)
                            .override(Target.SIZE_ORIGINAL))
                    .submit().get();
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
            try {
                wallpaperManager.setBitmap(b);
            } catch (IOException e) {
                throw new NullPointerException();
            }
            b.recycle();
            return true;
        } catch (OutOfMemoryError | NullPointerException e) {
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean success) {
        mDialog.dismiss();
        Toast.makeText(context, context.getString(R.string.applied), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPreExecute() {
        mDialog = ProgressDialog.show(context, null, context.getString(R.string.applying));
    }
}
