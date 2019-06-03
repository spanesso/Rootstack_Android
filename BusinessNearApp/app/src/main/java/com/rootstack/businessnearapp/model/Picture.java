package com.rootstack.businessnearapp.model;


import org.json.*;
import java.util.*;

import io.realm.RealmObject;


public class Picture  extends RealmObject {

    private String large;
    private String medium;
    private String thumbnail;

    public Picture() {
    }

    public Picture(String large, String medium, String thumbnail) {
        this.large = large;
        this.medium = medium;
        this.thumbnail = thumbnail;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}