package com.lastdown.homework.homework;

import java.io.Serializable;

/**
 * Created by Martijn on 22/09/2016.
 */

public class Assignment implements Serializable {

    private String title;

    public Assignment(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
