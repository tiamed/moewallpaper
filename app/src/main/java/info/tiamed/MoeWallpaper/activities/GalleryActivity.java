package info.tiamed.MoeWallpaper.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import info.tiamed.MoeWallpaper.R;
import info.tiamed.MoeWallpaper.adapters.PaletteGridAdapter;

public class GalleryActivity extends Activity {

    private RecyclerView mImageRecycler;
    private PaletteGridAdapter mPaletteGridAdapter;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        mImageRecycler = findViewById(R.id.gallery_item);
        mPaletteGridAdapter = new PaletteGridAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mImageRecycler.setLayoutManager(gridLayoutManager);

        mImageRecycler.setOnTouchListener((v, event) -> {return false; });
        mImageRecycler.setAdapter(mPaletteGridAdapter);
        mPaletteGridAdapter.setOnItemClickListener(
                new PaletteGridAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(GalleryActivity.this, position, Toast.LENGTH_LONG).show();

                    }
                }
        );

    }
}
