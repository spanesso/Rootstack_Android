package com.rootstack.businessnearapp.model;

//
//  Info.java
//  Model Generated using http://www.jsoncafe.com/
//  Created on June 2, 2019

import org.json.*;
import java.util.*;


public class Info{

    private int page;
    private int results;
    private String seed;
    private String version;


    public Info() {
    }

    public Info(int page, int results, String seed, String version) {
        this.page = page;
        this.results = results;
        this.seed = seed;
        this.version = version;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}