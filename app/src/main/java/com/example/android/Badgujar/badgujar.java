package com.example.android.Badgujar;

public class badgujar {

    private int mGridImageResourceId;
    private String mGridNames;

    public badgujar(int gridImageResourceId, String gridNames) {

        mGridImageResourceId = gridImageResourceId;
        mGridNames = gridNames;
    }

    public int getGridImageResourceId() {
        return mGridImageResourceId;
    }

    public String getGridNames() {
        return mGridNames;
    }
}