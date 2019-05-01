package br.com.infinitytechnology.filmex.entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseWithProductionCompanies implements Parcelable {

    @SerializedName("page")
    private Integer page;

    @SerializedName("results")
    private List<ProductionCompany> results;

    @SerializedName("total_results")
    private Integer totalResults;

    @SerializedName("total_pages")
    private Integer totalPages;

    private ResponseWithProductionCompanies(Parcel in) {
        setPage(in.readInt());
        setResults(new ArrayList<ProductionCompany>());
        in.readList(getResults(), ProductionCompany.class.getClassLoader());
        setTotalResults(in.readInt());
        setTotalPages(in.readInt());
    }

    public static final Creator<ResponseWithProductionCompanies> CREATOR =
            new Creator<ResponseWithProductionCompanies>() {
        @Override
        public ResponseWithProductionCompanies createFromParcel(Parcel in) {
            return new ResponseWithProductionCompanies(in);
        }

        @Override
        public ResponseWithProductionCompanies[] newArray(int size) {
            return new ResponseWithProductionCompanies[size];
        }
    };

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<ProductionCompany> getResults() {
        return results;
    }

    public void setResults(List<ProductionCompany> results) {
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
        return "ResponseWithProductionCompanies{" +
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

        ResponseWithProductionCompanies responseWithProductionCompanies =
                (ResponseWithProductionCompanies) o;

        return getPage().equals(responseWithProductionCompanies.getPage());
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
