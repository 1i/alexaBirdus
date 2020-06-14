package com.onei.birdus;


public class Model {

    private String resultNumber;
    private String reference;
    private String date;
    private String commonName;
    private String scientificName;
    private String count;
    private String location;
    private String county;
    private String photo;

    public Model() {
    }

    public Model(String resultNumber, String reference, String date, String commonName, String scientificName, String count, String location, String county, String photo) {
        this.resultNumber = resultNumber;
        this.reference = reference;
        this.date = date;
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.count = count;
        this.location = location;
        this.county = county;
        this.photo = photo;
    }

    public String getResultNumber() {
        return resultNumber;
    }

    public void setResultNumber(String resultNumber) {
        this.resultNumber = resultNumber;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "{" +
                "resultNumber='" + resultNumber + '\'' +
                ", reference='" + reference + '\'' +
                ", date='" + date + '\'' +
                ", commonName='" + commonName + '\'' +
                ", scientificName='" + scientificName + '\'' +
                ", count='" + count + '\'' +
                ", location='" + location + '\'' +
                ", county='" + county + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}