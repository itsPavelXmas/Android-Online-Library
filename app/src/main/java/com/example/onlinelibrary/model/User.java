package com.example.onlinelibrary.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User implements Parcelable {
    @NonNull
    private String fullName;
    @NonNull
    private String userName;
    @PrimaryKey
    @NonNull
    private String email;
    @NonNull
    private String phoneNumber;
    @NonNull
    private String password;
    @NonNull
    private Boolean newsletter;

    public User(String fullName, String userName, String email, String phoneNumber, String password, Boolean newsletter) {
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.newsletter = newsletter;
    }

    protected User(Parcel in) {
        fullName = in.readString();
        userName = in.readString();
        email = in.readString();
        phoneNumber = in.readString();
        password = in.readString();
        byte tmpNewsletter = in.readByte();
        newsletter = tmpNewsletter == 0 ? null : tmpNewsletter == 1;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getNewsletter() {
        return newsletter;
    }

    public void setNewsletter(Boolean newsletter) {
        this.newsletter = newsletter;
    }

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", newsletter=" + newsletter +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fullName);
        dest.writeString(userName);
        dest.writeString(email);
        dest.writeString(phoneNumber);
        dest.writeString(password);
        dest.writeByte((byte) (newsletter == null ? 0 : newsletter ? 1 : 2));
    }
}
