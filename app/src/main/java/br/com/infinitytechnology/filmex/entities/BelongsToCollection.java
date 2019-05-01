package br.com.infinitytechnology.filmex.entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class BelongsToCollection implements Parcelable {

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("backdrop_path")
    private String backdropPath;

    private BelongsToCollection(Parcel in) {
        setId(in.readInt());
        setName(in.readString());
        setPosterPath(in.readString());
        setBackdropPath(in.readString());
    }

    public static final Creator<BelongsToCollection> CREATOR = new Creator<BelongsToCollection>() {
        @Override
        public BelongsToCollection createFromParcel(Parcel in) {
            return new BelongsToCollection(in);
        }

        @Override
        public BelongsToCollection[] newArray(int size) {
            return new BelongsToCollection[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    @Override
    @NonNull
    public String toString() {
        return "BelongsToCollection{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", posterPath='" + getPosterPath() + '\'' +
                ", backdropPath='" + getBackdropPath() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BelongsToCollection that = (BelongsToCollection) o;

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
        dest.writeInt(getId());
        dest.writeString(getName());
        dest.writeString(getPosterPath());
        dest.writeString(getBackdropPath());
    }
}