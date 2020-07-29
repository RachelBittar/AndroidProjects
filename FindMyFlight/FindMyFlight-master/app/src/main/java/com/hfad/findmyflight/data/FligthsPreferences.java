package com.hfad.findmyflight.data;

import android.content.Context;

public class FligthsPreferences {

    public static final String from_City = "city_name";
    public static final String to_City =   "city_name";
    public static final String from_Airport = "airport_name";
    public static final String to__Airport  = "airport_name";
    private static final String DEFAULT_AIRPORT_LOCATION = "AAA";

    public static String getDefaultLocation(Context context) {
        /** This will be implemented in a future lesson **/
        return DEFAULT_AIRPORT_LOCATION;
    }
}
