package com.example.lab3_20200334_iot.pojos;

import java.util.ArrayList;

public class RootPojo {
    private ArrayList<Result> results;
    private Info info;

    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}
