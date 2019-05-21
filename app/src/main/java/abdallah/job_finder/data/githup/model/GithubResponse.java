package abdallah.job_finder.data.githup.model;

import com.google.gson.annotations.SerializedName;


public class GithubResponse {


    @SerializedName("company_logo")
    private String companyLogo;
    @SerializedName("how_to_apply")
    private String howToApply;
    @SerializedName("description")
    private String description;
    @SerializedName("title")
    private String title;
    @SerializedName("location")
    private String location;
    @SerializedName("company_url")
    private String companyUrl;
    @SerializedName("company")
    private String company;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("url")
    private String url;
    @SerializedName("type")
    private String type;
    @SerializedName("id")
    private String id;

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getHowToApply() {
        return howToApply;
    }

    public void setHowToApply(String howToApply) {
        this.howToApply = howToApply;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }


}
