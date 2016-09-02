package com.dada.ga.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ben on 21/03/16.
 */
public class Scholarship implements Parcelable{


    private String images;
    private String organisationName;
    private String libelleScholarship;
    private String descriptionScholarship;
    private String emailScholarship;
    private int  phneScholarship;
    private String webadressScholship;


    public Scholarship() {
    }



    public Scholarship(String images,String organisationName,String libelleScholarship,String descriptionScholarship,String emailScholarship,int phneScholarship, String webadressScholship) {
        this.images = images;
        this.organisationName = organisationName;
        this.libelleScholarship = libelleScholarship;
        this.descriptionScholarship = descriptionScholarship;
        this.emailScholarship = emailScholarship;
        this.phneScholarship = phneScholarship;
        this.webadressScholship = webadressScholship;
    }


    protected Scholarship(Parcel in) {
        images = in.readString();
        organisationName = in.readString();
        libelleScholarship = in.readString();
        webadressScholship = in.readString();
        descriptionScholarship = in.readString();
        emailScholarship = in.readString();
        phneScholarship = in.readInt();
    }




    public static final Creator<Scholarship> CREATOR = new Creator<Scholarship>() {

        @Override
        public Scholarship createFromParcel(Parcel in) {
            return new Scholarship(in);
        }


        @Override
        public Scholarship[] newArray(int size) {
            return new Scholarship[size];
        }
    };


    public String getImages() {
        return images;
    }


    public void setImages(String images) {
        this.images = images;
    }


    public String getOrganisationName() {
        return organisationName;
    }


    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }


    public String getLibelleScholarship() {
        return libelleScholarship;
    }


    public void setLibelleScholarship(String libelleScholarship) {
        this.libelleScholarship = libelleScholarship;
    }


    public String getWebadressScholship() {
        return webadressScholship;
    }


    public void setWebadressScholship(String webadressScholship) {
        this.webadressScholship = webadressScholship;
    }


    public String getDescriptionScholarship() {
        return descriptionScholarship;
    }


    public void setDescriptionScholarship(String descriptionScholarship) {
        this.descriptionScholarship = descriptionScholarship;
    }


    public String getEmailScholarship() {
        return emailScholarship;
    }

    public void setEmailScholarship(String emailScholarship) {
        this.emailScholarship = emailScholarship;
    }


    public int getPhneScholarship() {
        return phneScholarship;
    }


    public void setPhneScholarship(int phneScholarship) {
        this.phneScholarship = phneScholarship;
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(images);
        dest.writeString(organisationName);
        dest.writeString(libelleScholarship);
        dest.writeString(webadressScholship);
        dest.writeString(descriptionScholarship);
        dest.writeString(emailScholarship);
        dest.writeInt(phneScholarship);
    }

}
