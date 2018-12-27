package info.tiamed.MoeWallpaper.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface ApiService {
    @GET("photos")
    Call<List<sourceData>> getData(@Query("client_id") String client_id, @Query("page") int page, @Query("per_page") int per, @Query("order_by") String order);

    @GET("search/photos")
    Call<searchData> searchData(@Query("client_id") String client_id, @Query("page") int page, @Query("per_page") int per, @Query("query") String query);
}
