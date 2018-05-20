package br.com.infinitytechnology.filmex.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class CreatedBy implements Parcelable {

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("gender")
    private Integer gender;

    @SerializedName("profile_path")
    private String profilePath;

    public CreatedBy() {
    }

    private CreatedBy(Parcel in) {
        setId(in.readInt());
        setName(in.readString());
        setGender(in.readInt());
        setProfilePath(in.readString());
    }

    public static final Creator<CreatedBy> CREATOR = new Creator<CreatedBy>() {
        @Override
        public CreatedBy createFromParcel(Parcel in) {
            return new CreatedBy(in);
        }

        @Override
        public CreatedBy[] newArray(int size) {
            return new CreatedBy[size];
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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    @Override
    public String toString() {
        return "CreatedBy{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", gender=" + getGender() +
                ", profilePath='" + getProfilePath() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreatedBy createdBy = (CreatedBy) o;

        return getId().equals(createdBy.getId());
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
        dest.writeInt(getGender());
        dest.writeString(getProfilePath());
    }
}