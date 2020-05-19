package com.example.covid;

public class covidCounty {
    String mCovidCountry , mCases , mTodayCases ,mDeaths , mRecovered , mCritical;

    public covidCounty(String mCovidCountry, String mCases) {
        this.mCovidCountry = mCovidCountry;
        this.mCases = mCases;
        this.mTodayCases = mTodayCases;
        this.mDeaths = mDeaths;
        this.mRecovered = mRecovered;
        this.mCritical = mCritical;
    }

    public void setmCovidCountry(String mCovidCountry) {
        this.mCovidCountry = mCovidCountry;
    }

    public void setmCases(String mCases) {
        this.mCases = mCases;
    }

}
