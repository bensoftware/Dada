package com.dada.ga.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by ben on 30/03/16.
 */
public class Competition implements Parcelable{

    private String id_competition;
    private String Libelle_competition;
    private String Description_competition;
    private Date date_competition;
    private int photo_Competition;


    public Competition() {
    }

    public Competition(String id_competition, String libelle_competition, int photo_Competition) {
        this.id_competition = id_competition;
        Libelle_competition = libelle_competition;
       // Description_competition = description_competition;
        this.date_competition = date_competition;
        this.photo_Competition = photo_Competition;
    }

    protected Competition(Parcel in) {
        id_competition = in.readString();
        Libelle_competition = in.readString();
        Description_competition = in.readString();
        photo_Competition = in.readInt();
    }

    public static final Creator<Competition> CREATOR = new Creator<Competition>() {
        @Override
        public Competition createFromParcel(Parcel in) {
            return new Competition(in);
        }

        @Override
        public Competition[] newArray(int size) {
            return new Competition[size];
        }
    };

    public String getId_competition() {
        return id_competition;
    }

    public void setId_competition(String id_competition) {
        this.id_competition = id_competition;
    }

    public String getLibelle_competition() {
        return Libelle_competition;
    }

    public void setLibelle_competition(String libelle_competition) {
        Libelle_competition = libelle_competition;
    }

    public String getDescription_competition() {
        return Description_competition;
    }

    public void setDescription_competition(String description_competition) {
        Description_competition = description_competition;
    }

    public Date getDate_competition() {
        return date_competition;
    }

    public void setDate_competition(Date date_competition) {
        this.date_competition = date_competition;
    }

    public int getPhoto_Competition() {
        return photo_Competition;
    }

    public void setPhoto_Competition(int photo_Competition) {
        this.photo_Competition = photo_Competition;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id_competition);
        dest.writeString(Libelle_competition);
        dest.writeString(Description_competition);
        dest.writeInt(photo_Competition);
    }
}
