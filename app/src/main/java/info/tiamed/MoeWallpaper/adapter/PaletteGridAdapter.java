package info.tiamed.MoeWallpaper.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;
import info.tiamed.MoeWallpaper.R;
import info.tiamed.MoeWallpaper.activity.DetailActivity;

public class PaletteGridAdapter extends RecyclerView.Adapter<PaletteGridAdapter.PaletteGridViewHolder> {

    private static Context context;
    private OnItemClickListener mOnItemClickListener = null;
    private ArrayList<String> urls_thumb;
    private ArrayList<String> urls;
    private ArrayList<String> titles;

    public PaletteGridAdapter(Context context, ArrayList<String> urls_thumb, ArrayList<String> urls, ArrayList<String> titles) {
        this.context = context;
        this.urls = urls;
        this.urls_thumb = urls_thumb;
        this.titles = titles;
    }

    public static Context getcontext() {
        return context;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public PaletteGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_palette, parent, false);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    Log.d("PaletteGrid on create", "item clicked :"+ v.getTag());
                    if (v.getTag() != null) {
                        int position = (int) v.getTag();
                        Intent detailIntent = new Intent(context, DetailActivity.class);
                        detailIntent.putExtra("pos", position)
                                .putExtra("urls", urls)
                                .putExtra("titles", titles);
                        itemView.getContext().startActivity(detailIntent);
                    }
                }
            }
        });
        return new PaletteGridViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PaletteGridViewHolder holder, final int position) {
        holder.itemView.setTag(position);
        if (urls_thumb.size() != 0) {
            Glide.with(holder.mIvPic.getContext()).load(urls_thumb.get(position)).into(holder.mIvPic);
            holder.mTvTitle.setText(titles.get(position));
        }

        if (holder.mIvPic.getDrawable() != null) {
            setPalette(holder, position);
        }

    }

    private int generateTransparentColor(float percent, int rgb) {
        int red = Color.red(rgb);
        int green = Color.green(rgb);
        int blue = Color.blue(rgb);
        int alpha = Color.alpha(rgb);
        alpha = (int) (percent * alpha);
        return Color.argb(alpha, red, green, blue);
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    private void setPalette(final PaletteGridViewHolder holder, final int position) {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) holder.mIvPic.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch swatch = palette.getDarkMutedSwatch();
                if (swatch == null) {
                    swatch = palette.getDarkVibrantSwatch();
                }
                if (swatch == null) {
                    swatch = palette.getLightMutedSwatch();
                }
                if (swatch == null) {
                    swatch = palette.getLightVibrantSwatch();
                }
                if (swatch == null) {
                    swatch = palette.getMutedSwatch();
                }
                if (swatch == null) {
                    swatch = palette.getVibrantSwatch();
                }
                int titleTextColor = swatch.getTitleTextColor();
                int rgb = swatch.getRgb();

                holder.mTvTitle.setTextColor(titleTextColor);
                holder.mTvTitle.setBackgroundColor(generateTransparentColor(0.5f, rgb));

            }
        });
    }

    public void setData(ArrayList<String> urls_thumb, ArrayList<String> urls, ArrayList<String> titles) {
        this.urls = urls;
        this.urls_thumb = urls_thumb;
        this.titles = titles;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    class PaletteGridViewHolder extends RecyclerView.ViewHolder {

        ImageView mIvPic;
        TextView mTvTitle;

        public PaletteGridViewHolder(View itemView) {
            super(itemView);
            mIvPic = itemView.findViewById(R.id.ivPic);
            mTvTitle = itemView.findViewById(R.id.tvTitle);
        }
    }

}

