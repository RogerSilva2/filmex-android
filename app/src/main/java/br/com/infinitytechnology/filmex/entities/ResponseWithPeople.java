package br.com.infinitytechnology.filmex.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseWithPeople implements Parcelable {

    @SerializedName("page")
    private Integer page;

    @SerializedName("results")
    private List<Person> results;

    @SerializedName("total_results")
    private Integer totalResults;

    @SerializedName("total_pages")
    private Integer totalPages;

    public ResponseWithPeople() {
    }

    private ResponseWithPeople(Parcel in) {
        setPage(in.readInt());
        setResults(new ArrayList<Person>());
        in.readList(getResults(), Person.class.getClassLoader());
        setTotalResults(in.readInt());
        setTotalPages(in.readInt());
    }

    public static final Parcelable.Creator<ResponseWithPeople> CREATOR = new Parcelable.Creator<ResponseWithPeople>() {
        @Override
        public ResponseWithPeople createFromParcel(Parcel in) {
            return new ResponseWithPeople(in);
        }

        @Override
        public ResponseWithPeople[] newArray(int size) {
            return new ResponseWithPeople[size];
        }
    };

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Person> getResults() {
        return results;
    }

    public void setResults(List<Person> results) {
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
        return "ResponseWithPeople{" +
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

        ResponseWithPeople responseWithPeople = (ResponseWithPeople) o;

        return getPage().equals(responseWithPeople.getPage());
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