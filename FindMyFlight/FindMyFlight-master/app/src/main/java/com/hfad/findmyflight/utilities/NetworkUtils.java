package com.hfad.findmyflight.utilities;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {


    private static final String format = "json";
    private static final String URL_AUTORITY = "https://raw.githubusercontent.com/";
    private static final String URL_PATH = "RachelBittar/AndroidProjects/master/airports.json";



    public static URL buildUrl(String locationQuery) {
        Uri builtUri = Uri.parse(URL_AUTORITY).buildUpon()
                .appendQueryParameter(URL_PATH, locationQuery)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }


    public static String getResponseFromHttpUrl(URL url) throws IOException {
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
}
