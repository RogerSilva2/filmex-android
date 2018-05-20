package br.com.infinitytechnology.filmex.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ProductionCompany implements Parcelable {

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private Integer id;

    @SerializedName("logo_path")
    private String logoPath;

    @SerializedName("origin_country")
    private String originCountry;

    public ProductionCompany() {
    }

    private ProductionCompany(Parcel in) {
        setName(in.readString());
        setId(in.readInt());
        setLogoPath(in.readString());
        setOriginCountry(in.readString());
    }

    public static final Creator<ProductionCompany> CREATOR = new Creator<ProductionCompany>() {
        @Override
        public ProductionCompany createFromParcel(Parcel in) {
            return new ProductionCompany(in);
        }

        @Override
        public ProductionCompany[] newArray(int size) {
            return new ProductionCompany[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    @Override
    public String toString() {
        return "ProductionCompany{" +
                "name='" + getName() + '\'' +
                ", id=" + getId() +
                ", logoPath='" + getLogoPath() + '\'' +
                ", originCountry='" + getOriginCountry() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductionCompany that = (ProductionCompany) o;

        return getId().equals(that.getId());
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
        dest.writeString(getName());
        dest.writeInt(getId());
        dest.writeString(getLogoPath());
        dest.writeString(getOriginCountry());
    }
}