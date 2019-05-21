package abdallah.job_finder.data.govservice.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class SearchGovResponse {


    @SerializedName("url")
    private String url;
    @SerializedName("locations")
    private List<String> locations;
    @SerializedName("end_date")
    private String endDate;
    @SerializedName("start_date")
    private String startDate;
    @SerializedName("maximum")
    private int maximum;
    @SerializedName("minimum")
    private int minimum;
    @SerializedName("rate_interval_code")
    private String rateIntervalCode;
    @SerializedName("organization_name")
    private String organizationName;
    @SerializedName("position_title")
    private String positionTitle;
    @SerializedName("id")
    private String id;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public String getRateIntervalCode() {
        return rateIntervalCode;
    }

    public void setRateIntervalCode(String rateIntervalCode) {
        this.rateIntervalCode = rateIntervalCode;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getPositionTitle() {
        return positionTitle;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
