package com.dada.ga.adapters;

import android.graphics.drawable.Drawable;

/**
 * Created by steeve on 3/10/16.
 */
public class CardItem {

    String title;
    Drawable image;
    int order;

    // Empty Constructor
    public CardItem() {

    }

    // Constructor
    public CardItem(String title, Drawable image, int order) {
        super();
        this.title = title;
        this.image = image;
        this.order = order;
    }


    // Getter and Setter Method
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public int getOrder() {
        return order;
    }


}
