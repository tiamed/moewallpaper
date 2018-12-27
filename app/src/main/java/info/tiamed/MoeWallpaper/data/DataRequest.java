package info.tiamed.MoeWallpaper.data;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataRequest {
    public static final String baseurl = "https://api.unsplash.com/";
    private final String client_id = "a1999c86fdd271e5641fe7fa033d1c172145a68250d04e763e7f53bd2eb85b02";
    Retrofit retro = new Retrofit.Builder()
            .baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public void getWallpaperList(final RequsetCallback requsetCallback, int page) {
        ApiService mApiService = retro.create(ApiService.class);
        Call<List<SourceData>> mCall = mApiService.getData(client_id, page, 30, "popular");
        get(requsetCallback, mCall);
    }

    public void searchList(final RequsetCallback requsetCallback, String query, int page) {
        ApiService mApiService = retro.create(ApiService.class);
        Call<SearchData> mCall = mApiService.searchData(client_id, page, 30, query);
        search(requsetCallback, mCall);
    }

    private void get(RequsetCallback requsetCallback, Call<List<SourceData>> mCall) {
        mCall.enqueue(new Callback<List<SourceData>>() {
            @Override
            public void onResponse(Call<List<SourceData>> call, Response<List<SourceData>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    requsetCallback.onFinish(response.body(), null);
                } else {
                    requsetCallback.onError("error");
                }
            }

            @Override
            public void onFailure(Call<List<SourceData>> call, Throwable t) {
                Log.e("DataRequest", "Failed");
            }
        });
    }

    private void search(RequsetCallback requsetCallback, Call<SearchData> mCall) {
        mCall.enqueue(new Callback<SearchData>() {
            @Override
            public void onResponse(Call<SearchData> call, Response<SearchData> response) {
                if (response.isSuccessful() && response.body() != null) {
                    requsetCallback.onFinish(null, response.body().getResults());
                } else {
                    requsetCallback.onError("error");
                }
            }

            @Override
            public void onFailure(Call<SearchData> call, Throwable t) {
                Log.e("data request", "failed");
            }
        });

    }

    public interface RequsetCallback<T> {
        void onFinish(List<SourceData> data, List<SearchData.ResultsBean> search);

        void onError(String msg);
    }




}
