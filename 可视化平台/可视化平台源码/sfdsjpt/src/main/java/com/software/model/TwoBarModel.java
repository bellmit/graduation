package com.software.model;

import lombok.Data;

import java.util.List;

@Data
public class TwoBarModel {
    private String name1;
    private String name2;

    private List<K2vModel> k2vModelList;
}
