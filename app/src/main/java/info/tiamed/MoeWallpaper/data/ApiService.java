package info.tiamed.MoeWallpaper.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("photos")
    Call<List<sourceData>> getData(@Query("client_id") String client_id, @Query("page") int page);
}
