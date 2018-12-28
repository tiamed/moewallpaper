package info.tiamed.MoeWallpaper.data;

import android.util.Log;
import info.tiamed.MoeWallpaper.util.HttpUtil;

import java.util.List;

public class SourceResults extends Results implements HttpUtil.updateData<SourceData>, DataRequest.RequestCallback<SourceData> {
    private final int page;

    public SourceResults(int page) {
        this.page = page;
        new Thread(new Runnable() {
            @Override
            public void run() {
                query();
            }
        });
    }

    @Override
    public void onFinish(List<SourceData> data) {
        updateData(data);
        Log.d("HttpUtil", "get success");
    }

    @Override
    public void onError(String msg) {
        Log.d("HttpUtil", "get failed");
    }

    @Override
    public void updateData(List<SourceData> data) {
        urls.clear();
        titles.clear();
        data.forEach(datum -> urls.add(datum.getUrls().getRegular()));
        data.forEach(datum -> titles.add(datum.getUser().getUsername()));
        setBundle(urls, titles);
    }

    @Override
    public void query() {
        DataRequest dr = new DataRequest();
        dr.getWallpaperList(this, page);
    }
}