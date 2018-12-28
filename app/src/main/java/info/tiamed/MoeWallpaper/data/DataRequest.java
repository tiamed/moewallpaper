package info.tiamed.MoeWallpaper.data;

import android.util.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class DataRequest {
    public static final String baseurl = "https://api.unsplash.com/";
    private final String client_id = "a1999c86fdd271e5641fe7fa033d1c172145a68250d04e763e7f53bd2eb85b02";
    Retrofit retro = new Retrofit.Builder()
            .baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public void getWallpaperList(final RequestCallback requestCallback, int page) {
        ApiService mApiService = retro.create(ApiService.class);
        Call<List<SourceData>> mCall = mApiService.getData(client_id, page, 30, "popular");
        get(requestCallback, mCall);
    }

    public void searchList(final RequestCallback requestCallback, String query, int page) {
        ApiService mApiService = retro.create(ApiService.class);
        Call<SearchData> mCall = mApiService.searchData(client_id, page, 30, query);
        search(requestCallback, mCall);
    }

    private void get(RequestCallback requestCallback, Call<List<SourceData>> mCall) {
        mCall.enqueue(new Callback<List<SourceData>>() {
            @Override
            public void onResponse(Call<List<SourceData>> call, Response<List<SourceData>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    requestCallback.onFinish(response.body());
                } else {
                    requestCallback.onError("error");
                }
            }

            @Override
            public void onFailure(Call<List<SourceData>> call, Throwable t) {
                Log.e("DataRequest", "Failed");
            }
        });
    }

    private void search(RequestCallback requestCallback, Call<SearchData> mCall) {
        mCall.enqueue(new Callback<SearchData>() {
            @Override
            public void onResponse(Call<SearchData> call, Response<SearchData> response) {
                if (response.isSuccessful() && response.body() != null) {
                    requestCallback.onFinish(response.body().getResults());
                } else {
                    requestCallback.onError("error");
                }
            }

            @Override
            public void onFailure(Call<SearchData> call, Throwable t) {
                Log.e("data request", "failed");
            }
        });

    }

    public interface RequestCallback<T> {
        void onFinish(List<T> data);

        void onError(String msg);
    }




}
