package com.dada.ga.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ben on 03/04/16.
 */
public class User  implements Parcelable{

    private String email;
    private String phonenumber;
    private int   role;
    private boolean  status;
    private String fullname;


    public User()  {
    }

    public User(String email, String phonenumber, int role, boolean status, String fullname) {
        this.email = email;
        this.phonenumber = phonenumber;
        this.role = role;
        this.status = status;
        this.fullname = fullname;
    }


    protected User(Parcel in) {
        email = in.readString();
        phonenumber = in.readString();
        role = in.readInt();
        status = in.readByte() != 0;
        fullname = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(phonenumber);
        dest.writeInt(role);
        dest.writeByte((byte) (status ? 1 : 0));
        dest.writeString(fullname);
    }

}
