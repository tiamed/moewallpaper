package info.tiamed.MoeWallpaper.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import info.tiamed.MoeWallpaper.R;
import info.tiamed.MoeWallpaper.views.PaletteGridAdapter;

public class GalleryActivity extends Activity {

    private RecyclerView mImageRecycler;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        mImageRecycler = findViewById(R.id.gallery_item);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mImageRecycler.setLayoutManager(gridLayoutManager);

        mImageRecycler.setOnTouchListener((v, event) -> {return false; });
        mImageRecycler.setAdapter(new PaletteGridAdapter());
    }
}
