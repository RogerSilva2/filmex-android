package br.com.infinitytechnology.filmex.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Movie implements Parcelable {

    @SerializedName("adult")
    private Boolean adult;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("belongs_to_collection")
    private BelongsToCollection belongsToCollection;

    @SerializedName("budget")
    private Integer budget;

    @SerializedName("genre_ids")
    private List<Integer> genreIds;

    @SerializedName("genres")
    private List<Genre> genres;

    @SerializedName("homepage")
    private String homepage;

    @SerializedName("id")
    private Integer id;

    @SerializedName("imdb_id")
    private String imdbId;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("overview")
    private String overview;

    @SerializedName("popularity")
    private Double popularity;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("production_companies")
    private List<ProductionCompany> productionCompanies;

    @SerializedName("production_countries")
    private List<ProductionCountry> productionCountries;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("revenue")
    private Integer revenue;

    @SerializedName("runtime")
    private Integer runtime;

    @SerializedName("spoken_languages")
    private List<SpokenLanguage> spokenLanguages;

    @SerializedName("status")
    private String status;

    @SerializedName("tagline")
    private String tagline;

    @SerializedName("title")
    private String title;

    @SerializedName("video")
    private Boolean video;

    @SerializedName("vote_average")
    private Double voteAverage;

    @SerializedName("vote_count")
    private Integer voteCount;

    public Movie() {
    }

    private Movie(Parcel in) {
        setAdult((Boolean) in.readValue(Boolean.class.getClassLoader()));
        setBackdropPath(in.readString());
        setBelongsToCollection(in.<BelongsToCollection>readParcelable(BelongsToCollection.class.getClassLoader()));
        setBudget(in.readInt());
        setGenreIds(new ArrayList<Integer>());
        in.readList(getGenreIds(), Integer.class.getClassLoader());
        setGenres(new ArrayList<Genre>());
        in.readList(getGenres(), Genre.class.getClassLoader());
        setHomepage(in.readString());
        setId(in.readInt());
        setImdbId(in.readString());
        setOriginalLanguage(in.readString());
        setOriginalTitle(in.readString());
        setOverview(in.readString());
        setPopularity(in.readDouble());
        setPosterPath(in.readString());
        setProductionCompanies(new ArrayList<ProductionCompany>());
        in.readList(getProductionCompanies(), ProductionCompany.class.getClassLoader());
        setProductionCountries(new ArrayList<ProductionCountry>());
        in.readList(getProductionCountries(), ProductionCountry.class.getClassLoader());
        setReleaseDate(in.readString());
        setRevenue(in.readInt());
        setRuntime(in.readInt());
        setSpokenLanguages(new ArrayList<SpokenLanguage>());
        in.readList(getSpokenLanguages(), SpokenLanguage.class.getClassLoader());
        setStatus(in.readString());
        setTagline(in.readString());
        setTitle(in.readString());
        setVideo((Boolean) in.readValue(Boolean.class.getClassLoader()));
        setVoteAverage(in.readDouble());
        setVoteCount(in.readInt());
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public BelongsToCollection getBelongsToCollection() {
        return belongsToCollection;
    }

    public void setBelongsToCollection(BelongsToCollection belongsToCollection) {
        this.belongsToCollection = belongsToCollection;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
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

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
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

    public List<ProductionCountry> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List<ProductionCountry> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public List<SpokenLanguage> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(List<SpokenLanguage> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
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
    public String toString() {
        return "Movie{" +
                "adult=" + getAdult() +
                ", backdropPath='" + getBackdropPath() + '\'' +
                ", belongsToCollection=" + getBelongsToCollection() +
                ", budget=" + getBudget() +
                ", genreIds=" + getGenreIds() +
                ", genres=" + getGenres() +
                ", homepage='" + getHomepage() + '\'' +
                ", id=" + getId() +
                ", imdbId='" + getImdbId() + '\'' +
                ", originalLanguage='" + getOriginalLanguage() + '\'' +
                ", originalTitle='" + getOriginalTitle() + '\'' +
                ", overview='" + getOverview() + '\'' +
                ", popularity=" + getPopularity() +
                ", posterPath='" + getPosterPath() + '\'' +
                ", productionCompanies=" + getProductionCompanies() +
                ", productionCountries=" + getProductionCountries() +
                ", releaseDate='" + getReleaseDate() + '\'' +
                ", revenue=" + getRevenue() +
                ", runtime=" + getRuntime() +
                ", spokenLanguages=" + getSpokenLanguages() +
                ", status='" + getStatus() + '\'' +
                ", tagline='" + getTagline() + '\'' +
                ", title='" + getTitle() + '\'' +
                ", video=" + getVideo() +
                ", voteAverage=" + getVoteAverage() +
                ", voteCount=" + getVoteCount() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        return getId().equals(movie.getId());
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
        dest.writeValue(getAdult());
        dest.writeString(getBackdropPath());
        dest.writeParcelable(getBelongsToCollection(), flags);
        dest.writeInt(getBudget());
        dest.writeList(getGenreIds());
        dest.writeList(getGenres());
        dest.writeString(getHomepage());
        dest.writeInt(getId());
        dest.writeString(getImdbId());
        dest.writeString(getOriginalLanguage());
        dest.writeString(getOriginalTitle());
        dest.writeString(getOverview());
        dest.writeDouble(getPopularity());
        dest.writeString(getPosterPath());
        dest.writeList(getProductionCompanies());
        dest.writeList(getProductionCountries());
        dest.writeString(getReleaseDate());
        dest.writeInt(getRevenue());
        dest.writeInt(getRuntime());
        dest.writeList(getSpokenLanguages());
        dest.writeString(getStatus());
        dest.writeString(getTagline());
        dest.writeString(getTitle());
        dest.writeValue(getVideo());
        dest.writeDouble(getVoteAverage());
        dest.writeInt(getVoteCount());
    }
}