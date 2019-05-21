package abdallah.job_finder.ui.Search;

import java.util.List;

import abdallah.job_finder.data.General;

public interface SearchView {


    void setupView();

    void startLoading();

    void stopLoading();

    void onSuccessResponse(List<General> body);

    void onErrorResponse();


}
