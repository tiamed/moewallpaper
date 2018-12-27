package info.tiamed.MoeWallpaper.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("photos")
    Call<List<SourceData>> getData(@Query("client_id") String client_id, @Query("page") int page, @Query("per_page") int per, @Query("order_by") String order);

    @GET("search/photos")
    Call<SearchData> searchData(@Query("client_id") String client_id, @Query("page") int page, @Query("per_page") int per, @Query("query") String query);
}
