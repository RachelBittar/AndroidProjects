package com.hfad.findmyflight.utilities;

import android.content.ContentValues;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;

public class OpenJsonUtils {

    public static String[] getSimpleAirportStringsFromJson(Context context, String airportJsonStr)
            throws JSONException {

        final String OWM_LIST = "list";
        final String OWM_NAME = "NAME";
        final String OWM_CITY = "CITY";
        final String OWM_COUNTRY= "COUNTRY";
        final String OWM_IATA3 = "CODE";
        final String OWM_LAT = "LAT";
        final String OWM_LON= "LOG";

        final String OWM_MESSAGE_CODE = "cod";
        String[] parsedData = null;


        JSONObject dataJson = new JSONObject(airportJsonStr);

        if (dataJson.has(OWM_MESSAGE_CODE)) {
            int errorCode = dataJson.getInt(OWM_MESSAGE_CODE);

            switch (errorCode) {
                case HttpURLConnection.HTTP_OK:
                    break;
                case HttpURLConnection.HTTP_NOT_FOUND:
                    /* Location invalid */
                    return null;
                default:
                    /* Server probably down */
                    return null;
            }
        }


        JSONArray airportArray = dataJson.getJSONArray(OWM_LIST);
        parsedData = new String[airportArray.length()];


        return new String[]{"a", "b"};
    }


    public static ContentValues[] getFullWeatherDataFromJson(Context context, String forecastJsonStr) {
        /** This will be implemented in a future lesson **/
        return null;
    }
}
