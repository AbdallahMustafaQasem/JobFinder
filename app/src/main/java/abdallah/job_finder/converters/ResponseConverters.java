package abdallah.job_finder.converters;

import java.util.Arrays;

import abdallah.job_finder.data.General;
import abdallah.job_finder.data.githup.model.GithubResponse;
import abdallah.job_finder.data.govservice.model.SearchGovResponse;


public class ResponseConverters {


    public static General convertGitHubResponseToSearchGeneral(GithubResponse response) {


        return new General() {
            @Override
            public String getCompanyName() {
                return response.getCompany();
            }

            @Override
            public String getJobTitle() {
                return response.getTitle();
            }

            @Override
            public String getLogo() {
                return response.getCompanyLogo();
            }

            @Override
            public String getLocation() {
                return response.getLocation();
            }

            @Override
            public String getPostDate() {
                return response.getCreatedAt();
            }

            @Override
            public String getProvider() {
                return "GitHub";
            }

            @Override
            public String getUrl() {
                return response.getUrl();
            }
        };
    }


    public static General convertSearchGovResponseToSearchGeneral(SearchGovResponse response) {


        return new General() {
            @Override
            public String getCompanyName() {
                return response.getOrganizationName();
            }

            @Override
            public String getJobTitle() {
                return response.getPositionTitle();
            }

            @Override
            public String getLogo() {
                return null;
            }

            @Override
            public String getLocation() {
                return Arrays.toString(response.getLocations().toArray());
            }

            @Override
            public String getPostDate() {
                return response.getStartDate();
            }

            @Override
            public String getProvider() {
                return "Search Gov";
            }

            @Override
            public String getUrl() {
                return response.getUrl();
            }
        };
    }


}
