package info.tiamed.MoeWallpaper.data;

import android.os.Bundle;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import info.tiamed.MoeWallpaper.util.HttpUtil;

public class SourceResults extends Results implements HttpUtil.updateData<SourceData>, DataRequest.RequestCallback<SourceData> {
    private final String tag = "get";
    private final int page;

    public SourceResults(int page) {
        this.page = page;
        EventBus.getDefault().register(this);
        query();
    }

    @Override
    public void onFinish(List<SourceData> data) {
        updateData(data);
        EventBus.getDefault().post(bundle);
    }

    @Override
    public void onError(String msg) {
        Log.d("HttpUtil", "get failed");
    }

    @Override
    public void updateData(List<SourceData> data) {
        urls.clear();
        urls_thumb.clear();
        titles.clear();
        data.forEach(datum -> urls_thumb.add(datum.getUrls().getThumb()));
        data.forEach(datum -> urls.add(datum.getUrls().getRegular()));
        data.forEach(datum -> titles.add(datum.getUser().getUsername()));
        this.setBundle(urls, titles, tag);
    }

    @Override
    public void query() {
        DataRequest dr = new DataRequest();
        dr.getWallpaperList(this, page);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPostBundle(Bundle bundle) {
    }
}