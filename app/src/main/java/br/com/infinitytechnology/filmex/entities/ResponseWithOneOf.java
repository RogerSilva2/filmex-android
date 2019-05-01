package br.com.infinitytechnology.filmex.entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseWithOneOf implements Parcelable {

    @SerializedName("page")
    private Integer page;

    @SerializedName("results")
    private List<OneOf> results;

    @SerializedName("total_results")
    private Integer totalResults;

    @SerializedName("total_pages")
    private Integer totalPages;

    private ResponseWithOneOf(Parcel in) {
        setPage(in.readInt());
        setResults(new ArrayList<OneOf>());
        in.readList(getResults(), Movie.class.getClassLoader());
        setTotalResults(in.readInt());
        setTotalPages(in.readInt());
    }

    public static final Parcelable.Creator<ResponseWithOneOf> CREATOR =
            new Parcelable.Creator<ResponseWithOneOf>() {
        @Override
        public ResponseWithOneOf createFromParcel(Parcel in) {
            return new ResponseWithOneOf(in);
        }

        @Override
        public ResponseWithOneOf[] newArray(int size) {
            return new ResponseWithOneOf[size];
        }
    };

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<OneOf> getResults() {
        return results;
    }

    public void setResults(List<OneOf> results) {
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
        return "ResponseWithOneOf{" +
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

        ResponseWithOneOf responseWithOneOf = (ResponseWithOneOf) o;

        return getPage().equals(responseWithOneOf.getPage());
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