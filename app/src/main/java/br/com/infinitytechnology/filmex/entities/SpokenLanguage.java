package br.com.infinitytechnology.filmex.entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class SpokenLanguage implements Parcelable {

    @SerializedName("iso_639_1")
    private String iso6391;

    @SerializedName("name")
    private String name;

    private SpokenLanguage(Parcel in) {
        setIso6391(in.readString());
        setName(in.readString());
    }

    public static final Creator<SpokenLanguage> CREATOR = new Creator<SpokenLanguage>() {
        @Override
        public SpokenLanguage createFromParcel(Parcel in) {
            return new SpokenLanguage(in);
        }

        @Override
        public SpokenLanguage[] newArray(int size) {
            return new SpokenLanguage[size];
        }
    };

    public String getIso6391() {
        return iso6391;
    }

    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    @NonNull
    public String toString() {
        return "SpokenLanguage{" +
                "iso6391='" + getIso6391() + '\'' +
                ", name='" + getName() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpokenLanguage that = (SpokenLanguage) o;

        return getIso6391().equals(that.getIso6391());
    }

    @Override
    public int hashCode() {
        return getIso6391().hashCode();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getIso6391());
        dest.writeString(getName());
    }
}