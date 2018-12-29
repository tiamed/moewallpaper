package info.tiamed.MoeWallpaper.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.airbnb.lottie.LottieAnimationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import info.tiamed.MoeWallpaper.R;

public class WebviewFragment extends Fragment {
    public static final String URL = "javascript:(function() { " +
            "var list = document.getElementById(\"app\").childNodes[0];\n" +
            "for (i in [...Array(6).keys()]) {\n" +
            "    list.childNodes[0].remove()\n" +
            "}\n" +
            "list.childNodes[0].childNodes[0].remove();\n" +
            "list.childNodes[0].childNodes[0].style.marginTop = '1rem';})()";
    private static final String TAG = "Webview";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_webview, null);
        WebView myWebview = view.findViewById(R.id.webview);
        LottieAnimationView lottie = view.findViewById(R.id.animation_view);
        myWebview.getSettings().setJavaScriptEnabled(true);
        myWebview.setVisibility(View.INVISIBLE);

        myWebview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                myWebview.loadUrl(URL);
                myWebview.setVisibility(View.VISIBLE);
                lottie.setVisibility(View.GONE);
                Log.d(TAG, "lottie gone");
            }
        });
        myWebview.loadUrl("https://unsplash.com");

        return view;
    }
}
