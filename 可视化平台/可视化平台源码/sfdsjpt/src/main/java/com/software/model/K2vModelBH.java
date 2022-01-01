package com.software.model;

import lombok.Data;

@Data
public class K2vModelBH {
    private String name1;
    private String name2;
    private int value;

    public K2vModelBH(String name1, String name2, int value) {
        this.name1 = name1;
        this.name2 = name2;
        this.value = value;
    }
}
