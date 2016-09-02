package com.dada.ga.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * Created by steeve on 3/20/16.
 */
public class SchoolItem implements Parcelable, Serializable {

    private String id;
    private int note = 0;
    private List<String> pictures;
    private String photocouverture;
    private String description;
    private String town;
    private int category;
    private String quarter;
    private String phone;
    private String email;
    private String statut;
    private double prixinscription;
    private Boolean publish;
    private List<Formation> formations;
    private String libelle;


    public SchoolItem() {
        id = UUID.randomUUID().toString();
    }


    protected SchoolItem(Parcel in) {
        id = in.readString();
        note = in.readInt();
        pictures = in.createStringArrayList();
        photocouverture = in.readString();
        description = in.readString();
        town = in.readString();
        category = in.readInt();
        quarter = in.readString();
        phone = in.readString();
        email = in.readString();
        statut = in.readString();
        prixinscription = in.readDouble();
        formations = in.createTypedArrayList(Formation.CREATOR);
        libelle = in.readString();
    }

    public static final Creator<SchoolItem> CREATOR = new Creator<SchoolItem>() {
        @Override
        public SchoolItem createFromParcel(Parcel in) {
            return new SchoolItem(in);
        }

        @Override
        public SchoolItem[] newArray(int size) {
            return new SchoolItem[size];
        }
    };

    public SchoolItem(int note, List<String> pictures, String photocouverture, String description, String town, int category, String quarter, String phone, String email, String statut, double prixinscription, Boolean publish, List<Formation> formations, String libelle) {
        this.note = note;
        this.pictures = pictures;
        this.photocouverture = photocouverture;
        this.description = description;
        this.town = town;
        this.category = category;
        this.quarter = quarter;
        this.phone = phone;
        this.email = email;
        this.statut = statut;
        this.prixinscription = prixinscription;
        this.publish = publish;
        this.formations = formations;
        this.libelle = libelle;
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public String getPhotocouverture() {
        return photocouverture;
    }

    public void setPhotocouverture(String photocouverture) {
        this.photocouverture = photocouverture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public double getPrixinscription() {
        return prixinscription;
    }

    public void setPrixinscription(double prixinscription) {
        this.prixinscription = prixinscription;
    }

    public Boolean getPublish() {
        return publish;
    }

    public void setPublish(Boolean publish) {
        this.publish = publish;
    }

    public List<Formation> getFormations() {
        return formations;
    }

    public void setFormations(List<Formation> formations) {
        this.formations = formations;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeInt(note);
        dest.writeStringList(pictures);
        dest.writeString(photocouverture);
        dest.writeString(description);
        dest.writeString(town);
        dest.writeInt(category);
        dest.writeString(quarter);
        dest.writeString(phone);
        dest.writeString(email);
        dest.writeString(statut);
        dest.writeDouble(prixinscription);
        dest.writeTypedList(formations);
        dest.writeString(libelle);
    }


    @Override
    public String toString() {
        return "SchoolItem{" +
                "id='" + id + '\'' +
                ", note=" + note +
                ", pictures=" + pictures +
                ", photocouverture='" + photocouverture + '\'' +
                ", description='" + description + '\'' +
                ", town='" + town + '\'' +
                ", category=" + category +
                ", quarter='" + quarter + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", statut='" + statut + '\'' +
                ", prixinscription=" + prixinscription +
                ", publish=" + publish +
                ", formations=" + formations +
                ", libelle='" + libelle + '\'' +
                '}';
    }
}
