package com.hfad.findmyflight;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public final class Utils {


    public static final String LOG_TAG = Utils.class.getSimpleName();


    public static List<Airlines> fetchAirlinesData(URL requestUrl) {

      //  URL url = buildUrl(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(requestUrl);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        List<Airlines> airlines= extractFeatureFromJson(jsonResponse);

        return airlines;
    }


    public static List<Route> fetchRouteData(URL requestUrl) {

        //  URL url = buildUrl(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(requestUrl);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        List<Route> routes= extractRouteFromJson(jsonResponse);

        return routes;
    }




    public static List<Airport> fetchAirportData(URL requestUrl, URL requestUrl1) {

        //  URL url = buildUrl(requestUrl);

        String jsonResponse = null;
        String jsonResponse1 = null;
        List<Airport> airports0= new ArrayList<Airport>();
        List<Airport> airports1= new ArrayList<Airport>();


        try {
            jsonResponse = makeHttpRequest(requestUrl);
            jsonResponse1 = makeHttpRequest(requestUrl1);

        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        airports0= extractAirportFeatureFromJson(jsonResponse);
        airports1= extractAirportFeatureFromJson(jsonResponse1);


        List<Airport> union = new ArrayList<Airport>(airports0);
        union.addAll(airports1);



        return union;
    }
    /**
     * Returns new URL object from the given string URL.
     */
    public static URL buildUrl(String githubSearchQuery) {
        Uri builtUri = Uri.parse(githubSearchQuery).buildUpon()
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }


    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }



    private static List<Airlines> extractFeatureFromJson(String airlinesJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(airlinesJSON)) {
            return null;
        }

        List<Airlines> airlines = new ArrayList<>();

        try {

            JSONArray array=new JSONArray(airlinesJSON);

            for (int i=0; i<array.length(); i++) {

                JSONObject firstFeature = array.getJSONObject(i);

                String name = firstFeature.getString("Name");
                String code2 = firstFeature.getString("2 Digit Code");
                String code3 = firstFeature.getString("3 Digit Code");
                String country = firstFeature.getString("Country");

                Airlines airline = new Airlines(name, code2, code3,country);
                airlines.add(airline);

            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the airlines JSON results", e);
        }

        return airlines;
    }



    private static List<Route> extractRouteFromJson(String routeJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(routeJSON)) {
            return null;
        }

        List<Route> routes = new ArrayList<>();

        try {

            JSONArray array=new JSONArray(routeJSON);

            for (int i=0; i<array.length(); i++) {

                JSONObject firstFeature = array.getJSONObject(i);

                String id = firstFeature.getString("Airline Id");
                String from = firstFeature.getString("Origin");
                String to = firstFeature.getString("Destination");

                Route route = new Route(id, from, to);
                routes.add(route);

            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the airlines JSON results", e);
        }

        return routes;
    }







    private static List<Airport> extractAirportFeatureFromJson(String airportJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(airportJSON)) {
            return null;
        }

        List<Airport> airports = new ArrayList<>();

        try {

            JSONArray array=new JSONArray(airportJSON);

            for (int i=0; i<array.length(); i++) {

                JSONObject firstFeature = array.getJSONObject(i);

                String name = firstFeature.getString("Name");
                String city = firstFeature.getString("City");
                String contry = firstFeature.getString("Country");
                String iata3 = firstFeature.getString("IATA 3");
                String lat = firstFeature.getString("Latitute");
                String lon = firstFeature.getString("Longitude");

                Airport airport = new Airport(name, city, contry,iata3,lat,lon);
                airports.add(airport);

            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the airlines JSON results", e);
        }

        return airports;
    }



}
