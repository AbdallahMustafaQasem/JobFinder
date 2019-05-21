package abdallah.job_finder.ui.Search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import java.util.ArrayList;
import java.util.List;

import abdallah.job_finder.R;
import abdallah.job_finder.data.General;
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


    private PlaceAutocompleteFragment autocompleteFragment;

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


        resultAdapter = new ResultAdapter(this, itemList);
        recyclerView.setAdapter(resultAdapter);

        searchView.setOnClickListener(v -> {
            searchView.onActionViewExpanded();

        });

        searchView.setOnCloseListener(new android.support.v7.widget.SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {

                return false;
            }
        });

        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                query = s;



                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);


        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName());
                location = place.getName().toString();
                //setQuery(query, location);

            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });

        autocompleteFragment.getView().findViewById(R.id.place_autocomplete_clear_button)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        autocompleteFragment.setText("");
                        location = null;
                        view.setVisibility(View.GONE);
                        //setQuery(query, location);

                    }
                });



        searchPresenter.getData();
    }

    @Override
    public void startLoading() {
        loadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopLoading() {
        loadingIndicator.setVisibility(View.GONE);
    }

    @Override
    public void onSuccessResponse(List<General> body) {

        Log.e("  onSuccessResponse", "  onSuccessResponse" + body.size());
        stopLoading();
        itemList.clear();
        itemList.addAll(body);
        resultAdapter.notifyDataSetChanged();

    }


    @Override
    public void onErrorResponse() {
        //   noResultsContainer.setVisibility(View.VISIBLE);

    }
}
