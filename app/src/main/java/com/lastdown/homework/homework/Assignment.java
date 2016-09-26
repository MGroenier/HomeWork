package com.lastdown.homework.homework;

import java.io.Serializable;

/**
 * Created by Martijn on 22/09/2016.
 */

public class Assignment implements Serializable {

    private long id;
    private String title;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    private Course course;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
