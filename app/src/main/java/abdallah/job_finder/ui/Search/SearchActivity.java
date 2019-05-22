package abdallah.job_finder.ui.Search;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import abdallah.job_finder.R;
import abdallah.job_finder.data.General;
import abdallah.job_finder.utils.Helper;
import butterknife.BindView;
import butterknife.ButterKnife;


public class SearchActivity extends AppCompatActivity implements SearchView {
    private static final String TAG = SearchActivity.class.getName();


    @BindView(R.id.noResultsContainer)
    LinearLayout noResultsContainer;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.loadingIndicator)
    CardView loadingIndicator;

    @BindView(R.id.searchView)
    android.support.v7.widget.SearchView searchView;


    SearchPresenter searchPresenter;

    private List<General> itemList = new ArrayList<>();
    private ResultAdapter resultAdapter;


    private String location;
    private String query;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        searchPresenter = new SearchPresenter(this, SearchActivity.this);
        setupView();


    }

    @Override
    public void setupView() {

        // setup recyclerView
        resultAdapter = new ResultAdapter(this, itemList);
        recyclerView.setAdapter(resultAdapter);

        searchView.setOnClickListener(v -> {
            searchView.onActionViewExpanded();

        });

        // init place auto complete
        Places.initialize(getApplicationContext(), getApplicationContext().getResources().getString(R.string.PlacesAPI));
        PlacesClient placesClient = Places.createClient(this);
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                searchPresenter.getData(query, place.getName());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });

        searchView.setOnCloseListener(new android.support.v7.widget.SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {

                Helper.hideKeyboard(SearchActivity.this);
                query = null;
                location = null;
                searchPresenter.getData(query, location);
                return false;
            }
        });

        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                query = s;
                Helper.hideKeyboard(SearchActivity.this);
                searchPresenter.getData(query, location);


                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });


        searchPresenter.getData(query, location);


    }

    @Override
    public void startLoading() {
        loadingIndicator.setVisibility(View.VISIBLE);
        noResultsContainer.setVisibility(View.GONE);
    }

    @Override
    public void stopLoading() {
        loadingIndicator.setVisibility(View.GONE);
    }

    @Override
    public void onSuccessResponse(List<General> body) {

        stopLoading();
        itemList.clear();
        itemList.addAll(body);
        resultAdapter.notifyDataSetChanged();

    }


    @Override
    public void onErrorResponse() {
        noResultsContainer.setVisibility(View.VISIBLE);

    }


}
