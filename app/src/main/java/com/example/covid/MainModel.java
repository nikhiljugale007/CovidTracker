package com.example.covid;

public  class MainModel {

        Integer symptLogo;
        String symptName ;

        public MainModel(Integer symptLogo , String symptName){
            this.symptLogo = symptLogo;
            this.symptName = symptName;
        }

    public Integer getSymptLogo() {
        return symptLogo;
    }


    public String getSymptName() {
        return symptName;
    }

}
