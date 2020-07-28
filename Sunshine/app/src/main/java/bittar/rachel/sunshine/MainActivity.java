package bittar.rachel.sunshine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import java.net.URL;

import bittar.rachel.sunshine.data.SunshinePreferences;
import bittar.rachel.sunshine.utilities.NetworkUtils;
import bittar.rachel.sunshine.utilities.OpenWeatherJsonUtils;

public class MainActivity extends AppCompatActivity {

    TextView mWeatherTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWeatherTextView = (TextView) findViewById(R.id.tv_weather_data);
        loadWeatherData();

    }

    private void loadWeatherData() {

        String location = SunshinePreferences.getPreferredWeatherLocation(this); //default
        new FetchWeatherTask().execute(location);

    }

    // Create a class that extends AsyncTask to perform network requests
    public class FetchWeatherTask extends AsyncTask<String, Void, String[]> {

        @Override
        protected String[] doInBackground(String... params) {

            if (params.length == 0) {
                return null;
            }

            String location = params[0];
            URL weatherRequestUrl = NetworkUtils.buildUrl(location);  //build url

            try {
                    String jsonWeatherResponse = NetworkUtils.getResponseFromHttpUrl(weatherRequestUrl);
                    String[] simpleJsonWeatherData = OpenWeatherJsonUtils.
                            getSimpleWeatherStringsFromJson(MainActivity.this, jsonWeatherResponse);

                    return simpleJsonWeatherData;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }


        @Override
        protected void onPostExecute(String[] weatherData) {
            if (weatherData != null) {
                /*
                 * Iterate through the array and append the Strings to the TextView. The reason why we add
                 * the "\n\n\n" after the String is to give visual separation between each String in the
                 * TextView. Later, we'll learn about a better way to display lists of data.
                 */
                for (String weatherString : weatherData) {
                    mWeatherTextView.append((weatherString) + "\n\n\n");
                }
            }
        }
    }
}
