package com.fixit.auto.fixit;

/**
 * Created by madsngh on 22-03-2016.
 */
public class product_singledesc {
    String myadress;
    String mydistance;
    String mycost;
    String myservicecenter;

    public product_singledesc(String myadress, String mydistance, String mycost, String myservicecenter) {
        this.myadress = myadress;
        this.mydistance = mydistance;
        this.mycost = mycost;
        this.myservicecenter = myservicecenter;
    }

    public product_singledesc() {
        this.myadress = "";
        this.mydistance = "";
        this.mycost = "";
        this.myservicecenter = "";
    }

    public String getMyadress() {
        return myadress;
    }

    public void setMyadress(String myadress) {
        this.myadress = myadress;
    }

    public String getMydistance() {
        return mydistance;
    }

    public void setMydistance(String mydistance) {
        this.mydistance = mydistance;
    }

    public String getMycost() {
        return mycost;
    }

    public void setMycost(String mycost) {
        this.mycost = mycost;
    }

    public String getMyservicecenter() {
        return myservicecenter;
    }

    public void setMyservicecenter(String myservicecenter) {
        this.myservicecenter = myservicecenter;
    }
}
