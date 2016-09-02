package com.dada.ga.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by ben on 25/03/16.
 */
public class Formation implements Parcelable, Serializable {


    private String id;
    private String libelle;
    private double prix;
    private int note;

    public Formation() {
        id = UUID.randomUUID().toString();
    }

    public Formation(String libelle, double prix, int note) {
        this.libelle = libelle;
        this.prix = prix;
        this.note = note;
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public static Creator<Formation> getCREATOR() {
        return CREATOR;
    }

    protected Formation(Parcel in) {
        id = in.readString();
        libelle = in.readString();
        prix = in.readDouble();
        note = in.readInt();
    }

    public static final Creator<Formation> CREATOR = new Creator<Formation>() {
        @Override
        public Formation createFromParcel(Parcel in) {
            return new Formation(in);
        }

        @Override
        public Formation[] newArray(int size) {
            return new Formation[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(libelle);
        dest.writeDouble(prix);
        dest.writeInt(note);
    }

    @Override
    public String toString() {
        return "Formation{" +
                "id='" + id + '\'' +
                ", libelle='" + libelle + '\'' +
                ", prix=" + prix +
                ", note=" + note +
                '}';
    }
}





