package com.kansavozer.gulcetorkanbedirhankansavproject;

import android.os.Parcel;
import android.os.Parcelable;

public class Groceries implements Parcelable {
    private String type;
    private String item1;
    private int price1;
    private String imdId1;
    private String item2;
    private int price2;
    private String imdId2;

    public Groceries(String type, String item1, int price1, String imdId1, String item2, int price2, String imdId2) {
        this.type = type;
        this.item1 = item1;
        this.price1 = price1;
        this.imdId1 = imdId1;
        this.item2 = item2;
        this.price2 = price2;
        this.imdId2 = imdId2;
    }

    protected Groceries(Parcel in) {
        type = in.readString();
        item2 = in.readString();
        price1 = in.readInt();
        imdId1 = in.readString();
        item2 = in.readString();
        price2 = in.readInt();
        imdId2 = in.readString();
    }

    public static final Creator<Groceries> CREATOR = new Creator<Groceries>() {
        @Override
        public Groceries createFromParcel(Parcel in) {
            return new Groceries(in);
        }

        @Override
        public Groceries[] newArray(int size) {
            return new Groceries[size];
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getGroceries1() {
        return item1;
    }

    public void setGroceries1(String groceries1) {
        this.item1 = item1;
    }

    public int getPrice1() {
        return price1;
    }

    public void setPrice1(int price1) {
        this.price1 = price1;
    }

    public String getImdId1() {
        return imdId1;
    }

    public void setImdId1(String imdId1) {
        this.imdId1 = imdId1;
    }

    public String getGroceries2() {
        return item2;
    }

    public void setGroceries2(String groceries2) {
        this.item2 = item2;
    }

    public int getPrice2() {
        return price2;
    }

    public void setPrice2(int price2) {
        this.price2 = price2;
    }

    public String getImdId2() {
        return imdId2;
    }

    public void setImdId2(String imdId2) {
        this.imdId2 = imdId2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(type);
        parcel.writeString(item1);
        parcel.writeInt(price1);
        parcel.writeString(imdId1);
        parcel.writeString(item2);
        parcel.writeInt(price2);
        parcel.writeString(imdId2);
    }
}
