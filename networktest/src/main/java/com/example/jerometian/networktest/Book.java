package com.example.jerometian.networktest;

import java.util.Date;

/**
 * Created by jjtian on 2015/11/24.
 */
public class Book {
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public Date getPublishDateTime() {
        return publishDateTime;
    }

    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setPublishDateTime(Date publishDateTime) {
        this.publishDateTime = publishDateTime;
    }

    private String name;
    private String version;
    private Date publishDateTime;
}
