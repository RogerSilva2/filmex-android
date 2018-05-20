package br.com.infinitytechnology.filmex.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class OneOf implements Parcelable {

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("adult")
    private Boolean adult;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("genre_ids")
    private List<Integer> genreIds;

    @SerializedName("id")
    private Integer id;

    @SerializedName("media_type")
    private String mediaType;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("title")
    private String title;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("popularity")
    private Double popularity;

    @SerializedName("vote_count")
    private Integer voteCount;

    @SerializedName("video")
    private Boolean video;

    @SerializedName("vote_average")
    private Double voteAverage;

    @SerializedName("first_air_date")
    private String firstAirDate;

    @SerializedName("origin_country")
    private List<String> originCountry;

    @SerializedName("name")
    private String name;

    @SerializedName("original_name")
    private String originalName;

    @SerializedName("profile_path")
    private String profilePath;

    @SerializedName("known_for")
    private List<OneOf> knownFor;

    public OneOf() {
    }

    private OneOf(Parcel in) {
        setPosterPath(in.readString());
        setAdult((Boolean) in.readValue(Boolean.class.getClassLoader()));
        setOverview(in.readString());
        setReleaseDate(in.readString());
        setOriginalTitle(in.readString());
        setGenreIds(new ArrayList<Integer>());
        in.readList(getGenreIds(), Integer.class.getClassLoader());
        setId(in.readInt());
        setMediaType(in.readString());
        setOriginalLanguage(in.readString());
        setTitle(in.readString());
        setBackdropPath(in.readString());
        setPopularity(in.readDouble());
        setVoteCount(in.readInt());
        setVideo((Boolean) in.readValue(Boolean.class.getClassLoader()));
        setVoteAverage(in.readDouble());
        setFirstAirDate(in.readString());
        setOriginCountry(new ArrayList<String>());
        in.readList(getOriginCountry(), String.class.getClassLoader());
        setName(in.readString());
        setOriginalName(in.readString());
        setProfilePath(in.readString());
        setKnownFor(new ArrayList<OneOf>());
        in.readList(getKnownFor(), OneOf.class.getClassLoader());
    }

    public static final Creator<OneOf> CREATOR = new Creator<OneOf>() {
        @Override
        public OneOf createFromParcel(Parcel in) {
            return new OneOf(in);
        }

        @Override
        public OneOf[] newArray(int size) {
            return new OneOf[size];
        }
    };

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
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

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public List<String> getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(List<String> originCountry) {
        this.originCountry = originCountry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public List<OneOf> getKnownFor() {
        return knownFor;
    }

    public void setKnownFor(List<OneOf> knownFor) {
        this.knownFor = knownFor;
    }

    @Override
    public String toString() {
        return "OneOf{" +
                "posterPath='" + getPosterPath() + '\'' +
                ", adult=" + getAdult() +
                ", overview='" + getOverview() + '\'' +
                ", releaseDate='" + getReleaseDate() + '\'' +
                ", originalTitle='" + getOriginalTitle() + '\'' +
                ", genreIds=" + getGenreIds() +
                ", id=" + getId() +
                ", mediaType='" + getMediaType() + '\'' +
                ", originalLanguage='" + getOriginalLanguage() + '\'' +
                ", title='" + getTitle() + '\'' +
                ", backdropPath='" + getBackdropPath() + '\'' +
                ", popularity=" + getPopularity() +
                ", voteCount=" + getVoteCount() +
                ", video=" + getVideo() +
                ", voteAverage=" + getVoteAverage() +
                ", firstAirDate='" + getFirstAirDate() + '\'' +
                ", originCountry=" + getOriginCountry() +
                ", name='" + getName() + '\'' +
                ", originalName='" + getOriginalName() + '\'' +
                ", profilePath='" + getProfilePath() + '\'' +
                ", knownFor=" + getKnownFor() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OneOf oneOf = (OneOf) o;
        return getId().equals(oneOf.getId()) &&
                getMediaType().equals(oneOf.getMediaType());
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 17 + getId().hashCode();
        hash = hash * 31 + getMediaType().hashCode();
        return hash;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getPosterPath());
        dest.writeValue(getAdult());
        dest.writeString(getOverview());
        dest.writeString(getReleaseDate());
        dest.writeString(getOriginalTitle());
        dest.writeList(getGenreIds());
        dest.writeInt(getId());
        dest.writeString(getMediaType());
        dest.writeString(getOriginalLanguage());
        dest.writeString(getTitle());
        dest.writeString(getBackdropPath());
        dest.writeDouble(getPopularity());
        dest.writeInt(getVoteCount());
        dest.writeValue(getVideo());
        dest.writeDouble(getVoteAverage());
        dest.writeString(getFirstAirDate());
        dest.writeList(getOriginCountry());
        dest.writeString(getName());
        dest.writeString(getOriginalName());
        dest.writeString(getProfilePath());
        dest.writeList(getKnownFor());
    }
}