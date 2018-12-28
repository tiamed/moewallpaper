package info.tiamed.MoeWallpaper.util;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import info.tiamed.MoeWallpaper.data.SearchResults;
import info.tiamed.MoeWallpaper.data.SourceResults;

public class HttpUtil {


    Bundle bundle = new Bundle();

    public Bundle search(String query, int page) {
        SearchResults searchResults = new SearchResults(query, page);
        Log.e("httputil", searchResults.getBundle().toString());
        return searchResults.getBundle();
    }

    public Bundle get(int page) {
        SourceResults sourceResults = new SourceResults(page);
        Log.e("httputil", sourceResults.getBundle().toString());
        return sourceResults.getBundle();
    }

    public Bundle getmore(int page) {
        SourceResults sourceResults = new SourceResults(page);
        Log.e("httputil", sourceResults.getBundle().toString());
        return sourceResults.getBundle();
    }


    public interface updateData<T> {
        void updateData(List<T> data);

        void query();

        Bundle getBundle();
    }
}
