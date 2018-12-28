package info.tiamed.MoeWallpaper.util;

import android.os.Bundle;
import info.tiamed.MoeWallpaper.data.SearchResults;
import info.tiamed.MoeWallpaper.data.SourceResults;

import java.util.List;

public class HttpUtil {


    Bundle bundle = new Bundle();

    public Bundle search(String query, int page) {
        SearchResults searchResults = new SearchResults(query, page);
        return searchResults.getBundle();
    }

    public Bundle get(int page) {
        SourceResults sourceResults = new SourceResults(page);
        return sourceResults.getBundle();
    }


    public interface updateData<T> {
        void updateData(List<T> data);

        void query();

        Bundle getBundle();
    }
}
