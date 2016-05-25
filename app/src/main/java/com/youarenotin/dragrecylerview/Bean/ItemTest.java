package com.youarenotin.dragrecylerview.Bean;

import java.io.Serializable;

/**
 * Created by dell on 5/25 0025.
 */
public class ItemTest implements Serializable {
    private int id ;
    private String description;
    private int  imgResId;

    public ItemTest(int id, int imgResId, String description) {
        this.id = id;
        this.imgResId = imgResId;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImgResId() {
        return imgResId;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }
}
