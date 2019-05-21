package abdallah.job_finder.data.govservice;

import java.util.List;


import abdallah.job_finder.data.govservice.model.SearchGovResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchGovService {

    String BASE_URL = "https://jobs.search.gov/jobs/";

    @GET("search.json")
    Call<List<SearchGovResponse>> getData(@Query("query") String jobName);

}
