package com.example.employeedetails.ModalClasses;

import android.graphics.Bitmap;

public class DashboardItem {
    String title;
    Bitmap imageBitmap;

    public DashboardItem(String title, Bitmap imageBitmap) {
        this.title = title;
        this.imageBitmap = imageBitmap;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getImageBitmap() {
        return imageBitmap;
    }

    public void setImageBitmap(Bitmap imageBitmap) {
        this.imageBitmap = imageBitmap;
    }
}
