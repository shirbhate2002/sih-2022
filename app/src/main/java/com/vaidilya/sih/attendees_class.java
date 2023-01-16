package com.vaidilya.sih;

import android.graphics.Bitmap;

public class attendees_class {

    String name;
    Bitmap pro_img;

    public attendees_class(String name, Bitmap pro_img) {

        this.name=name;
        this.pro_img=pro_img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getPro_img() {
        return pro_img;
    }

    public void setPro_img(Bitmap pro_img) {
        this.pro_img = pro_img;
    }
}
