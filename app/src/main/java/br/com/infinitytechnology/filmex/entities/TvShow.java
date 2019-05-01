package br.com.infinitytechnology.filmex.entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TvShow implements Parcelable {

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("created_by")
    private List<CreatedBy> createdBy;

    @SerializedName("episode_run_time")
    private List<Integer> episodeRunTime;

    @SerializedName("first_air_date")
    private String firstAirDate;

    @SerializedName("genre_ids")
    private List<Integer> genreIds;

    @SerializedName("genres")
    private List<Genre> genres;

    @SerializedName("homepage")
    private String homepage;

    @SerializedName("id")
    private Integer id;

    @SerializedName("in_production")
    private Boolean inProduction;

    @SerializedName("languages")
    private List<String> languages;

    @SerializedName("last_air_date")
    private String lastAirDate;

    @SerializedName("name")
    private String name;

    @SerializedName("networks")
    private List<Network> networks;

    @SerializedName("number_of_episodes")
    private Integer numberOfEpisodes;

    @SerializedName("number_of_seasons")
    private Integer numberOfSeasons;

    @SerializedName("origin_country")
    private List<String> originCountry;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("original_name")
    private String originalName;

    @SerializedName("overview")
    private String overview;

    @SerializedName("popularity")
    private Double popularity;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("production_companies")
    private List<ProductionCompany> productionCompanies;

    @SerializedName("seasons")
    private List<Season> seasons;

    @SerializedName("status")
    private String status;

    @SerializedName("type")
    private String type;

    @SerializedName("vote_average")
    private Double voteAverage;

    @SerializedName("vote_count")
    private Integer voteCount;

    private TvShow(Parcel in) {
        setBackdropPath(in.readString());
        setCreatedBy(new ArrayList<CreatedBy>());
        in.readList(getCreatedBy(), CreatedBy.class.getClassLoader());
        setEpisodeRunTime(new ArrayList<Integer>());
        in.readList(getEpisodeRunTime(), Integer.class.getClassLoader());
        setFirstAirDate(in.readString());
        setGenreIds(new ArrayList<Integer>());
        in.readList(getGenreIds(), Integer.class.getClassLoader());
        setGenres(new ArrayList<Genre>());
        in.readList(getGenres(), Genre.class.getClassLoader());
        setHomepage(in.readString());
        setId(in.readInt());
        setInProduction((Boolean) in.readValue(Boolean.class.getClassLoader()));
        setLanguages(new ArrayList<String>());
        in.readList(getLanguages(), String.class.getClassLoader());
        setLastAirDate(in.readString());
        setName(in.readString());
        setNetworks(new ArrayList<Network>());
        in.readList(getNetworks(), Network.class.getClassLoader());
        setNumberOfEpisodes(in.readInt());
        setNumberOfSeasons(in.readInt());
        setOriginCountry(new ArrayList<String>());
        in.readList(getOriginCountry(), String.class.getClassLoader());
        setOriginalLanguage(in.readString());
        setOriginalName(in.readString());
        setOverview(in.readString());
        setPopularity(in.readDouble());
        setPosterPath(in.readString());
        setProductionCompanies(new ArrayList<ProductionCompany>());
        in.readList(getProductionCompanies(), ProductionCompany.class.getClassLoader());
        setSeasons(new ArrayList<Season>());
        in.readList(getSeasons(), Season.class.getClassLoader());
        setStatus(in.readString());
        setType(in.readString());
        setVoteAverage(in.readDouble());
        setVoteCount(in.readInt());
    }

    public static final Creator<TvShow> CREATOR = new Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel in) {
            return new TvShow(in);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public List<CreatedBy> getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(List<CreatedBy> createdBy) {
        this.createdBy = createdBy;
    }

    public List<Integer> getEpisodeRunTime() {
        return episodeRunTime;
    }

    public void setEpisodeRunTime(List<Integer> episodeRunTime) {
        this.episodeRunTime = episodeRunTime;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getInProduction() {
        return inProduction;
    }

    public void setInProduction(Boolean inProduction) {
        this.inProduction = inProduction;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public String getLastAirDate() {
        return lastAirDate;
    }

    public void setLastAirDate(String lastAirDate) {
        this.lastAirDate = lastAirDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Network> getNetworks() {
        return networks;
    }

    public void setNetworks(List<Network> networks) {
        this.networks = networks;
    }

    public Integer getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(Integer numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public Integer getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(Integer numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public List<String> getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(List<String> originCountry) {
        this.originCountry = originCountry;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public List<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<ProductionCompany> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    @Override
    @NonNull
    public String toString() {
        return "TvShow{" +
                "backdropPath='" + getBackdropPath() + '\'' +
                ", createdBy=" + getCreatedBy() +
                ", episodeRunTime=" + getEpisodeRunTime() +
                ", firstAirDate='" + getFirstAirDate() + '\'' +
                ", genreIds=" + getGenreIds() +
                ", genres=" + getGenres() +
                ", homepage='" + getHomepage() + '\'' +
                ", id=" + getId() +
                ", inProduction=" + getInProduction() +
                ", languages=" + getLanguages() +
                ", lastAirDate='" + getLastAirDate() + '\'' +
                ", name='" + getName() + '\'' +
                ", networks=" + getNetworks() +
                ", numberOfEpisodes=" + getNumberOfEpisodes() +
                ", numberOfSeasons=" + getNumberOfSeasons() +
                ", originCountry=" + getOriginCountry() +
                ", originalLanguage='" + getOriginalLanguage() + '\'' +
                ", originalName='" + getOriginalName() + '\'' +
                ", overview='" + getOverview() + '\'' +
                ", popularity=" + getPopularity() +
                ", posterPath='" + getPosterPath() + '\'' +
                ", productionCompanies=" + getProductionCompanies() +
                ", seasons=" + getSeasons() +
                ", status='" + getStatus() + '\'' +
                ", type='" + getType() + '\'' +
                ", voteAverage=" + getVoteAverage() +
                ", voteCount=" + getVoteCount() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TvShow tvShow = (TvShow) o;

        return getId().equals(tvShow.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getBackdropPath());
        dest.writeList(getCreatedBy());
        dest.writeList(getEpisodeRunTime());
        dest.writeString(getFirstAirDate());
        dest.writeList(getGenreIds());
        dest.writeList(getGenres());
        dest.writeString(getHomepage());
        dest.writeInt(getId());
        dest.writeValue(getInProduction());
        dest.writeList(getLanguages());
        dest.writeString(getLastAirDate());
        dest.writeString(getName());
        dest.writeList(getNetworks());
        dest.writeInt(getNumberOfEpisodes());
        dest.writeInt(getNumberOfSeasons());
        dest.writeList(getOriginCountry());
        dest.writeString(getOriginalLanguage());
        dest.writeString(getOriginalName());
        dest.writeString(getOverview());
        dest.writeDouble(getPopularity());
        dest.writeString(getPosterPath());
        dest.writeList(getProductionCompanies());
        dest.writeList(getSeasons());
        dest.writeString(getStatus());
        dest.writeString(getType());
        dest.writeDouble(getVoteAverage());
        dest.writeInt(getVoteCount());
    }
}