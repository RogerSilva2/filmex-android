package br.com.infinitytechnology.filmex.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseWithTvShows implements Parcelable {

    @SerializedName("page")
    private Integer page;

    @SerializedName("results")
    private List<TvShow> results;

    @SerializedName("total_results")
    private Integer totalResults;

    @SerializedName("total_pages")
    private Integer totalPages;

    public ResponseWithTvShows() {
    }

    private ResponseWithTvShows(Parcel in) {
        setPage(in.readInt());
        setResults(new ArrayList<TvShow>());
        in.readList(getResults(), TvShow.class.getClassLoader());
        setTotalResults(in.readInt());
        setTotalPages(in.readInt());
    }

    public static final Creator<ResponseWithTvShows> CREATOR = new Creator<ResponseWithTvShows>() {
        @Override
        public ResponseWithTvShows createFromParcel(Parcel in) {
            return new ResponseWithTvShows(in);
        }

        @Override
        public ResponseWithTvShows[] newArray(int size) {
            return new ResponseWithTvShows[size];
        }
    };

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<TvShow> getResults() {
        return results;
    }

    public void setResults(List<TvShow> results) {
        this.results = results;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public String toString() {
        return "ResponseWithTvShows{" +
                "page=" + getPage() +
                ", results=" + getResults() +
                ", totalResults=" + getTotalResults() +
                ", totalPages=" + getTotalPages() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResponseWithTvShows responseWithTvShows = (ResponseWithTvShows) o;

        return getPage().equals(responseWithTvShows.getPage());
    }

    @Override
    public int hashCode() {
        return getPage().hashCode();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getPage());
        dest.writeList(getResults());
        dest.writeInt(getTotalResults());
        dest.writeInt(getTotalPages());
    }
}