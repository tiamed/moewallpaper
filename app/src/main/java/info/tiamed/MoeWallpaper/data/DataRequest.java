package info.tiamed.MoeWallpaper.data;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class DataRequest {
    public static final String baseurl = "https://api.unsplash.com/";
    private final String client_id = "a1999c86fdd271e5641fe7fa033d1c172145a68250d04e763e7f53bd2eb85b02";
    Retrofit retro = new Retrofit.Builder()
            .baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public void getWallpaperList(final RequsetCallback requsetCallback, int page) {
        ApiService mApiService = retro.create(ApiService.class);
        Call<List<sourceData>> mCall = mApiService.getData(client_id, page, 30, "popular");
        get(requsetCallback, mCall);
    }

    public void searchList(final RequsetCallback requsetCallback, String query, int page) {
        ApiService mApiService = retro.create(ApiService.class);
        Call<searchData> mCall = mApiService.searchData(client_id, page, 30, query);
        search(requsetCallback, mCall);
    }

    private void get(RequsetCallback requsetCallback, Call<List<sourceData>> mCall) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response<List<sourceData>> response = mCall.execute();
                    if (response.isSuccessful() && response.body() != null) {
                        requsetCallback.onFinish(response.body(), null);
                    } else {
                        requsetCallback.onError("error");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void search(RequsetCallback requsetCallback, Call<searchData> mCall) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response<searchData> response = mCall.execute();
                    if (response.isSuccessful() && response.body() != null) {
                        requsetCallback.onFinish(null, response.body().getResults());
                    } else {
                        requsetCallback.onError("error");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public interface RequsetCallback<T> {
        void onFinish(List<sourceData> data, List<searchData.ResultsBean> search);

        void onError(String msg);
    }




}
