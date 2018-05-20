package br.com.infinitytechnology.filmex.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ProductionCountry implements Parcelable {

    @SerializedName("iso_3166_1")
    private String iso31661;

    @SerializedName("name")
    private String name;

    public ProductionCountry() {
    }

    private ProductionCountry(Parcel in) {
        setIso31661(in.readString());
        setName(in.readString());
    }

    public static final Creator<ProductionCountry> CREATOR = new Creator<ProductionCountry>() {
        @Override
        public ProductionCountry createFromParcel(Parcel in) {
            return new ProductionCountry(in);
        }

        @Override
        public ProductionCountry[] newArray(int size) {
            return new ProductionCountry[size];
        }
    };

    public String getIso31661() {
        return iso31661;
    }

    public void setIso31661(String iso31661) {
        this.iso31661 = iso31661;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProductionCountry{" +
                "iso31661='" + getIso31661() + '\'' +
                ", name='" + getName() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductionCountry that = (ProductionCountry) o;

        return getIso31661().equals(that.getIso31661());
    }

    @Override
    public int hashCode() {
        return getIso31661().hashCode();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getIso31661());
        dest.writeString(getName());
    }
}