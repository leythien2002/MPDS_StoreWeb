package com.example.mpds.dto;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDTO<T> {
    private int id;
    //truyen T vao day de khi ma tuong tac voi Product hay Invoice thi no se tra ve gia tri list tuong ung.
    private List<T> listResults=new ArrayList<T>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<T> getListResults() {
        return listResults;
    }

    public void setListResults(List<T> listResults) {
        this.listResults = listResults;
    }
}
