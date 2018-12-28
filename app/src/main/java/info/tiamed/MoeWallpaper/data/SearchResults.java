package info.tiamed.MoeWallpaper.data;

import android.util.Log;
import info.tiamed.MoeWallpaper.util.HttpUtil;

import java.util.List;

public class SearchResults extends Results implements HttpUtil.updateData<SearchData.ResultsBean>, DataRequest.RequestCallback<SearchData.ResultsBean> {
    private final String query;
    private final int page;

    public SearchResults(String query, int page) {
        this.query = query;
        this.page = page;
        new Thread(new Runnable() {
            @Override
            public void run() {
                query();
            }
        });
    }

    @Override
    public void onFinish(List<SearchData.ResultsBean> data) {
        updateData(data);
        Log.d("HttpUtil", "get success");
    }

    @Override
    public void onError(String msg) {
        Log.d("HttpUtil", "get failed");
    }

    @Override
    public void updateData(List<SearchData.ResultsBean> data) {
        urls.clear();
        titles.clear();
        data.forEach(datum -> urls.add(datum.getUrls().getRegular()));
        data.forEach(datum -> titles.add(datum.getUser().getUsername()));
        setBundle(urls, titles);
    }

    @Override
    public void query() {
        DataRequest dr = new DataRequest();
        dr.searchList(this, query, page);
    }
}
