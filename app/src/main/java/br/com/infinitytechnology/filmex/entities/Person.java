package br.com.infinitytechnology.filmex.entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Person implements Parcelable {

    @SerializedName("adult")
    private Boolean adult;

    @SerializedName("also_known_as")
    private List<String> alsoKnownAs;

    @SerializedName("biography")
    private String biography;

    @SerializedName("birthday")
    private String birthday;

    @SerializedName("deathday")
    private String deathday;

    @SerializedName("gender")
    private Integer gender;

    @SerializedName("homepage")
    private String homepage;

    @SerializedName("id")
    private Integer id;

    @SerializedName("known_for")
    private List<OneOf> knownFor;

    @SerializedName("imdb_id")
    private String imdbId;

    @SerializedName("name")
    private String name;

    @SerializedName("place_of_birth")
    private String placeOfBirth;

    @SerializedName("popularity")
    private Double popularity;

    @SerializedName("profile_path")
    private String profilePath;

    private Person(Parcel in) {
        setAdult((Boolean) in.readValue(Boolean.class.getClassLoader()));
        setAlsoKnownAs(new ArrayList<String>());
        in.readList(getAlsoKnownAs(), String.class.getClassLoader());
        setBiography(in.readString());
        setBirthday(in.readString());
        setDeathday(in.readString());
        setGender(in.readInt());
        setHomepage(in.readString());
        setId(in.readInt());
        setKnownFor(new ArrayList<OneOf>());
        in.readList(getKnownFor(), OneOf.class.getClassLoader());
        setImdbId(in.readString());
        setName(in.readString());
        setPlaceOfBirth(in.readString());
        setPopularity(in.readDouble());
        setProfilePath(in.readString());
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public List<String> getAlsoKnownAs() {
        return alsoKnownAs;
    }

    public void setAlsoKnownAs(List<String> alsoKnownAs) {
        this.alsoKnownAs = alsoKnownAs;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDeathday() {
        return deathday;
    }

    public void setDeathday(String deathday) {
        this.deathday = deathday;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<OneOf> getKnownFor() {
        return knownFor;
    }

    public void setKnownFor(List<OneOf> knownFor) {
        this.knownFor = knownFor;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    @Override
    @NonNull
    public String toString() {
        return "Person{" +
                "adult=" + getAdult() +
                ", alsoKnownAs=" + getAlsoKnownAs() +
                ", biography='" + getBiography() + '\'' +
                ", birthday='" + getBirthday() + '\'' +
                ", deathday='" + getDeathday() + '\'' +
                ", gender=" + getGender() +
                ", homepage='" + getHomepage() + '\'' +
                ", knownFor=" + getKnownFor() +
                ", id=" + getId() +
                ", imdbId='" + getImdbId() + '\'' +
                ", name='" + getName() + '\'' +
                ", placeOfBirth='" + getPlaceOfBirth() + '\'' +
                ", popularity=" + getPopularity() +
                ", profilePath='" + getProfilePath() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return getId().equals(person.getId());
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
        dest.writeValue(getAdult());
        dest.writeList(getAlsoKnownAs());
        dest.writeString(getBiography());
        dest.writeString(getBirthday());
        dest.writeString(getDeathday());
        dest.writeInt(getGender());
        dest.writeString(getHomepage());
        dest.writeInt(getId());
        dest.writeList(getKnownFor());
        dest.writeString(getImdbId());
        dest.writeString(getName());
        dest.writeString(getPlaceOfBirth());
        dest.writeDouble(getPopularity());
        dest.writeString(getProfilePath());
    }
}