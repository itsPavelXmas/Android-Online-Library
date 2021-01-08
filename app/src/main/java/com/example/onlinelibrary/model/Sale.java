package com.example.onlinelibrary.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;


import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(tableName = "sales", foreignKeys = @ForeignKey(entity = User.class, parentColumns = "email", childColumns = "userEmail"),
        indices = {@Index(value={"userEmail"})})
public class Sale implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Integer sale_id;
    @Nullable
    private String bookName;
    @NonNull
    private String authorName;
    @NonNull
    private Double bookPrice;
    @NonNull
    private Integer bookCondition;
    @Nullable
    private String paymentMethod;
    @NonNull
    private Date endingDate;
    @NonNull
    private Boolean isAuthorSigned;
    @NonNull
    private String userEmail;

    @Ignore
    public Sale(){

    }


    public Sale(@NonNull Integer sale_id, @NonNull String bookName, @NonNull String authorName, @NonNull Double bookPrice, @NonNull Integer bookCondition, @NonNull String paymentMethod, @NonNull Date endingDate, @NonNull Boolean isAuthorSigned, @NonNull String userEmail) {
        this.sale_id = sale_id;
        this.bookName = bookName;
        this.authorName=authorName;
        this.bookPrice = bookPrice;
        this.bookCondition = bookCondition;
        this.paymentMethod = paymentMethod;
        this.endingDate = endingDate;
        this.isAuthorSigned = isAuthorSigned;
        this.userEmail = userEmail;
    }


    protected Sale(Parcel in) {
        if (in.readByte() == 0) {
            sale_id = null;
        } else {
            sale_id = in.readInt();
        }
        bookName = in.readString();
        authorName = in.readString();
        if (in.readByte() == 0) {
            bookPrice = null;
        } else {
            bookPrice = in.readDouble();
        }
        if (in.readByte() == 0) {
            bookCondition = null;
        } else {
            bookCondition = in.readInt();
        }
        paymentMethod = in.readString();
        byte tmpIsAuthorSigned = in.readByte();
        isAuthorSigned = tmpIsAuthorSigned == 0 ? null : tmpIsAuthorSigned == 1;
        userEmail = in.readString();
    }

    public static final Creator<Sale> CREATOR = new Creator<Sale>() {
        @Override
        public Sale createFromParcel(Parcel in) {
            return new Sale(in);
        }

        @Override
        public Sale[] newArray(int size) {
            return new Sale[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if(sale_id==null){
            dest.writeByte((byte) 0);
        }else{
            dest.writeByte((byte) 1);
            dest.writeInt(sale_id);
        }

        dest.writeString(bookName);
        if (bookPrice == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(bookPrice);
        }
        if (bookCondition == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(bookCondition);
        }
        dest.writeString(paymentMethod);
        if(endingDate == null){
            dest.writeByte((byte) 0);
        }else{
            dest.writeByte((byte) 1);
            dest.writeString(new SimpleDateFormat("dd/MM/yyyy").format(endingDate));
        }
        dest.writeByte((byte) (isAuthorSigned == null ? 0 : isAuthorSigned ? 1 : 2));

        dest.writeString(userEmail);
    }

    @NonNull
    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(@NonNull String authorName) {
        this.authorName = authorName;
    }

    @NonNull
    public Integer getSale_id() {
        return sale_id;
    }

    public void setSale_id(@NonNull Integer sale_id) {
        this.sale_id = sale_id;
    }

    @NonNull
    public String getBookName() {
        return bookName;
    }

    public void setBookName(@NonNull String bookName) {
        this.bookName = bookName;
    }

    @NonNull
    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(@NonNull Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    @NonNull
    public Integer getBookCondition() {
        return bookCondition;
    }

    public void setBookCondition(@NonNull Integer bookCondition) {
        this.bookCondition = bookCondition;
    }

    @NonNull
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(@NonNull String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @NonNull
    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(@NonNull Date endingDate) {
        this.endingDate = endingDate;
    }

    @NonNull
    public Boolean getAuthorSigned() {
        return isAuthorSigned;
    }

    public void setAuthorSigned(@NonNull Boolean authorSigned) {
        isAuthorSigned = authorSigned;
    }

    @NonNull
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail( String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "sale_id=" + sale_id +
                ", bookName='" + bookName + '\'' +
                ", bookPrice=" + bookPrice +
                ", bookCondition=" + bookCondition +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", endingDate=" + endingDate +
                ", isAuthorSigned=" + isAuthorSigned +
                ", userMail='" + userEmail + '\'' +
                '}';
    }



}
