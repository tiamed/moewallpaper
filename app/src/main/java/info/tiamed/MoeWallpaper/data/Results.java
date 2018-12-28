package info.tiamed.MoeWallpaper.data;

import android.os.Bundle;

import java.util.ArrayList;

public class Results {
    Bundle bundle = new Bundle();
    ArrayList<String> urls_thumb = new ArrayList<>();
    ArrayList<String> urls = new ArrayList<>();
    ArrayList<String> titles = new ArrayList<>();

    public Bundle getBundle() {
        return this.bundle;
    }

    public void setBundle(ArrayList<String> urls, ArrayList<String> titles) {
        bundle.clear();
        bundle.putStringArrayList("urls_thumb", urls_thumb);
        bundle.putStringArrayList("urls", urls);
        bundle.putStringArrayList("titles", titles);
    }
}
