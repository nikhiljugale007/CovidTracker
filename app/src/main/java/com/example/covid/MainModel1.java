package com.example.covid;

public  class MainModel1 {

    Integer prevLogo;
    String prevName ;

    public MainModel1(Integer symptLogo , String symptName){
        this.prevLogo = symptLogo;
        this.prevName = symptName;
    }

    public Integer getPrevLogo() {
        return prevLogo;
    }

    public String getPrevName() {
        return prevName;
    }
}
