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

import java.util.ArrayList;

import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;
import info.tiamed.MoeWallpaper.R;
import info.tiamed.MoeWallpaper.activity.DetailActivity;
import info.tiamed.MoeWallpaper.util.getRes;

public class PaletteGridAdapter extends RecyclerView.Adapter<PaletteGridAdapter.PaletteGridViewHolder> {

    private static Context mcontext;
    ArrayList<Integer> mWallpapers = new ArrayList<Integer>();
    String[] mWallpaperInfo;
    private getRes getres;
    private OnItemClickListener mOnItemClickListener = null;

    public PaletteGridAdapter(Context mContext) {
        setMcontext(mContext);
        this.getres =  new getRes(getMcontext());
        mWallpapers = getres.getmWallpapers();
        mWallpaperInfo = getRes.getmWallpaperInfo();
    }

    public static Context getMcontext() {
        return mcontext;
    }

    public static void setMcontext(Context mcontext) {
        PaletteGridAdapter.mcontext = mcontext;
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
                    //注意这里使用getTag方法获取position
                    Log.d("PaletteGrid on create", "item clicked :"+ v.getTag());
                    int position = (int) v.getTag();
                    Intent detailIntent = new Intent(itemView.getContext(), DetailActivity.class);
                    detailIntent.putExtra("pos", position);
                    itemView.getContext().startActivity(detailIntent);
                }
            }
        });
        return new PaletteGridViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PaletteGridViewHolder holder, final int position) {
        holder.mIvPic.setImageResource(mWallpapers.get(position));
        BitmapDrawable bitmapDrawable = (BitmapDrawable) holder.mIvPic.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        holder.itemView.setTag(position);

        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                // 对于一张图片，它可能分析不出来暗、亮色，返回值为空，我这里采取的方案是当获取不到色调样品，则获取其他色调样品。
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

                holder.mTvTitle.setText(mWallpaperInfo[position]);
                holder.mTvTitle.setTextColor(titleTextColor);
                holder.mTvTitle.setBackgroundColor(generateTransparentColor(0.5f, rgb));

            }
        });
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
        return mWallpapers.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    class PaletteGridViewHolder extends RecyclerView.ViewHolder {

        ImageView mIvPic;
        TextView mTvTitle;

        public PaletteGridViewHolder(View itemView) {
            super(itemView);
            mIvPic = (ImageView) itemView.findViewById(R.id.ivPic);
            mTvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        }
    }
}

