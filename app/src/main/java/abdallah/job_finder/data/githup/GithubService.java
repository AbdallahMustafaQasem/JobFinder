package abdallah.job_finder.data.githup;

import java.lang.reflect.Array;
import java.util.List;

import abdallah.job_finder.data.githup.model.GithubResponse;
import retrofit2.Call;
import retrofit2.http.GET;


public interface GithubService {

     String BASE_URL = "https://jobs.github.com/";

    @GET("positions.json")
    Call<List< GithubResponse>> getData();
}
