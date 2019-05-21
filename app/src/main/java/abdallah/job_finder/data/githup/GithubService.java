package abdallah.job_finder.data.githup;


import java.util.List;
import abdallah.job_finder.data.githup.model.GithubResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface GithubService {

     String BASE_URL = "https://jobs.github.com/";

    @GET("positions.json")
    Call<List<GithubResponse>> getData(@Query("description") String jobName, @Query("location") String location);

}
