package br.com.infinitytechnology.filmex.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Season implements Parcelable {

    @SerializedName("air_date")
    private String airDate;

    @SerializedName("episode_count")
    private Integer episodeCount;

    @SerializedName("id")
    private Integer id;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("season_number")
    private Integer seasonNumber;

    public Season() {
    }

    private Season(Parcel in) {
        setAirDate(in.readString());
        setEpisodeCount(in.readInt());
        setId(in.readInt());
        setPosterPath(in.readString());
        setSeasonNumber(in.readInt());
    }

    public static final Creator<Season> CREATOR = new Creator<Season>() {
        @Override
        public Season createFromParcel(Parcel in) {
            return new Season(in);
        }

        @Override
        public Season[] newArray(int size) {
            return new Season[size];
        }
    };

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public Integer getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(Integer episodeCount) {
        this.episodeCount = episodeCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Integer getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    @Override
    public String toString() {
        return "Season{" +
                "airDate='" + getAirDate() + '\'' +
                ", episodeCount=" + getEpisodeCount() +
                ", id=" + getId() +
                ", posterPath='" + getPosterPath() + '\'' +
                ", seasonNumber=" + getSeasonNumber() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Season season = (Season) o;

        return getId().equals(season.getId());
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
        dest.writeString(getAirDate());
        dest.writeInt(getEpisodeCount());
        dest.writeInt(getId());
        dest.writeString(getPosterPath());
        dest.writeInt(getSeasonNumber());
    }
}