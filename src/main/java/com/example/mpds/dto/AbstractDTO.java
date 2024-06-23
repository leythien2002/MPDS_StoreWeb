package com.example.mpds.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public abstract class AbstractDTO<T> {
    private int id;
    //truyen T vao day de khi ma tuong tac voi Product hay Invoice thi no se tra ve gia tri list tuong ung.
    private List<T> listResults=new ArrayList<T>();


}
