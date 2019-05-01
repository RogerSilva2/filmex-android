package br.com.infinitytechnology.filmex.entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseWithKeywords implements Parcelable {

    @SerializedName("page")
    private Integer page;

    @SerializedName("results")
    private List<Keyword> results;

    @SerializedName("total_results")
    private Integer totalResults;

    @SerializedName("total_pages")
    private Integer totalPages;

    private ResponseWithKeywords(Parcel in) {
        setPage(in.readInt());
        setResults(new ArrayList<Keyword>());
        in.readList(getResults(), Keyword.class.getClassLoader());
        setTotalResults(in.readInt());
        setTotalPages(in.readInt());
    }

    public static final Creator<ResponseWithKeywords> CREATOR =
            new Creator<ResponseWithKeywords>() {
        @Override
        public ResponseWithKeywords createFromParcel(Parcel in) {
            return new ResponseWithKeywords(in);
        }

        @Override
        public ResponseWithKeywords[] newArray(int size) {
            return new ResponseWithKeywords[size];
        }
    };

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Keyword> getResults() {
        return results;
    }

    public void setResults(List<Keyword> results) {
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
    @NonNull
    public String toString() {
        return "ResponseWithKeywords{" +
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

        ResponseWithKeywords responseWithKeywords = (ResponseWithKeywords) o;

        return getPage().equals(responseWithKeywords.getPage());
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
