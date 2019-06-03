package com.rootstack.businessnearapp.model;


import org.json.*;
import java.util.*;


public class UserRequest{

    private Info info;
    private ArrayList<Result> results;

    public UserRequest() {
    }

    public UserRequest(Info info, ArrayList<Result> results) {
        this.info = info;
        this.results = results;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }
}