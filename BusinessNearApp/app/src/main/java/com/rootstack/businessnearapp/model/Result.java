package com.rootstack.businessnearapp.model;


import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.rootstack.businessnearapp.R;
import com.squareup.picasso.Picasso;

import org.json.*;
import java.util.*;

import io.realm.RealmObject;


public class Result extends RealmObject {

    private String cell;
    private String email;
    private String gender;
    private Name name;
    private Picture picture;

    public Result() {
    }

    public Result(String cell, String email, String gender, Name name, Picture picture) {
        this.cell = cell;
        this.email = email;
        this.gender = gender;
        this.name = name;
        this.picture = picture;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}