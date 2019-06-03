package com.rootstack.businessnearapp.model;


import org.json.*;
import java.util.*;

import io.realm.RealmObject;

public class Name  extends RealmObject {

    private String first;
    private String last;
    private String title;

    public Name() {
    }

    public Name(String first, String last, String title) {
        this.first = first;
        this.last = last;
        this.title = title;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}