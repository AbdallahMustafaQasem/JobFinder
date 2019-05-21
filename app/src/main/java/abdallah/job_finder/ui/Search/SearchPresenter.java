package abdallah.job_finder.ui.Search;

import android.content.Context;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import abdallah.job_finder.converters.ResponseConverters;
import abdallah.job_finder.data.General;
import abdallah.job_finder.data.githup.GithubService;
import abdallah.job_finder.data.githup.model.GithubResponse;
import abdallah.job_finder.data.govservice.SearchGovService;
import abdallah.job_finder.data.govservice.model.SearchGovResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchPresenter {

    private final SearchView searchView;
    private Context context;


    private List<General> results = new ArrayList<>();


    public SearchPresenter(SearchView searchView, Context context) {
        this.searchView = searchView;
        this.context = context;

    }

    public void getData() {
        results.clear();
        searchView.startLoading();
        getGitHubData();
    }

    private void getGitHubData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GithubService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        GithubService githubService = retrofit.create(GithubService.class);
        githubService.getData().enqueue(new Callback<List<GithubResponse>>() {
            @Override
            public void onResponse(Call<List<GithubResponse>> call, Response<List<GithubResponse>> response) {

                convertGitHubResponse(response.body());
                getGovData();
            }

            @Override
            public void onFailure(Call<List<GithubResponse>> call, Throwable t) {

                getGovData();

            }
        });
    }

    private void getGovData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SearchGovService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        SearchGovService searchGovService = retrofit.create(SearchGovService.class);
        searchGovService.getData().enqueue(new Callback<List<SearchGovResponse>>() {
            @Override
            public void onResponse(Call<List<SearchGovResponse>> call, Response<List<SearchGovResponse>> response) {
                convertGovResponse(response.body());
                searchView.onSuccessResponse(results);
            }

            @Override
            public void onFailure(Call<List<SearchGovResponse>> call, Throwable t) {
                searchView.onSuccessResponse(results);
            }
        });
    }


    private void convertGitHubResponse(List<GithubResponse> body) {
        for (GithubResponse item : body) {
            General result = ResponseConverters.convertGitHubResponseToSearchGeneral(item);
            results.add(result);
        }
    }

    private void convertGovResponse(List<SearchGovResponse> body) {
        for (SearchGovResponse item : body) {
            General result = ResponseConverters.convertSearchGovResponseToSearchGeneral(item);
            results.add(result);
        }
    }

}
