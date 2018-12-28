package info.tiamed.MoeWallpaper.data;

import android.os.Bundle;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import info.tiamed.MoeWallpaper.util.HttpUtil;

public class SearchResults extends Results implements HttpUtil.updateData<SearchData.ResultsBean>, DataRequest.RequestCallback<SearchData.ResultsBean> {
    private final String query;
    private final int page;

    public SearchResults(String query, int page) {
        this.query = query;
        this.page = page;
        EventBus.getDefault().register(this);
        query();
    }

    @Override
    public void onFinish(List<SearchData.ResultsBean> data) {
        updateData(data);
        EventBus.getDefault().post(bundle);
    }

    @Override
    public void onError(String msg) {
        Log.d("HttpUtil", "get failed");
    }

    @Override
    public void updateData(List<SearchData.ResultsBean> data) {
        urls.clear();
        urls_thumb.clear();
        titles.clear();
        data.forEach(datum -> urls_thumb.add(datum.getUrls().getThumb()));
        data.forEach(datum -> urls.add(datum.getUrls().getRegular()));
        data.forEach(datum -> titles.add(datum.getUser().getUsername()));
        setBundle(urls, titles);
    }

    @Override
    public void query() {
        DataRequest dr = new DataRequest();
        dr.searchList(this, query, page);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPostBundle(Bundle bundle) {
    }
}
