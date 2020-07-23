package com.hfad.findmyflight;

public class Airport {

    private String  Name;
    private String City;
    private String Country;
    private String IATA3;
    private String Latitute;
    private String Longitude;


    public Airport(String name, String city, String country, String IATA3, String latitute, String longitude) {
        Name = name;
        City = city;
        Country = country;
        this.IATA3 = IATA3;
        Latitute = latitute;
        Longitude = longitude;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getIATA3() {
        return IATA3;
    }

    public void setIATA3(String IATA3) {
        this.IATA3 = IATA3;
    }

    public String getLatitute() {
        return Latitute;
    }

    public void setLatitute(String latitute) {
        Latitute = latitute;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }



}
