package com.event.horizon.sciencequiz.Exam;

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
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Horizon on 12/29/2017.
 */

public class QueryUtils {


    private static String LOG_TAG=QueryUtils.class.getSimpleName();
    public QueryUtils() {
        super();

    }

    public static List<ExamFormat> fetchExamData(String requestUrl,int grade) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s
        List<ExamFormat> earthquakes = extractFeatureFromJson(jsonResponse,grade);

        // Return the list of {@link Earthquake}s
        return earthquakes;
    }



    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }






    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static List<ExamFormat> extractFeatureFromJson(String examJSON,int grade) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(examJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding earthquakes to
        List<ExamFormat> examFormatss = new ArrayList<>();

        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {


            // Create a JSONObject from the JSON response string
            //JSONObject baseJsonResponse = new JSONObject(examJSON);

            JSONArray gradeArray=new JSONArray(examJSON);
           // gradeArray=gradeArray.getJSONArray(0);
            JSONArray examArray=gradeArray.getJSONArray(grade);


            for (int i = 0; i < examArray.length(); i++) {

                // Get a single earthquake at position i within the list of earthquakes
                JSONObject currentExam = examArray.getJSONObject(i);



                String question=currentExam.getString("question");

                String answer=currentExam.getString("answer");

                String option1=currentExam.getString("option1");
                String option2=currentExam.getString("option2");
                String option3=currentExam.getString("option3");
                String option4=currentExam.getString("option4");


                String search=currentExam.getString("search");
                if(search!=null){
                    search=getImageSearch(search);
                }


                ExamFormat examFormat = new ExamFormat(question,answer,option1,option2,option3,option4,search);



                // Add the new {@link Earthquake} to the list of earthquakes.
                examFormatss.add(examFormat);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return examFormatss;
    }

    private static String getImageSearch(String search){
        URL url=null;
        String jsonResponse = null;

        url=createImageUrl(search);
        try {
            jsonResponse = makeHttpRequest(url);
        }
        catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        return extractImageFromJson(jsonResponse);

    }

    private static URL createImageUrl(String search){
        URL url=null;
        Uri.Builder builder = new Uri.Builder();
        try {
            builder.scheme("https")
                    .authority(ExamActivity.PIXABAY_AUTHORITY)
                    .appendPath("api")
                    .appendQueryParameter("key",ExamActivity.PIXABAY_BASE_KEY)
                    .appendQueryParameter("q",search);
            url=new URL(builder.toString());
            Log.d(LOG_TAG, "the URL is "+url);
        } catch (Exception e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }


        return url;
    }

    private static String extractImageFromJson(String jsonString){
        String search=null;
        if (TextUtils.isEmpty(jsonString)) {
            return null;
        }

        try {
         JSONObject pixabay= new JSONObject(jsonString);
         JSONArray hits=pixabay.getJSONArray("hits");
         JSONObject hit1=hits.getJSONObject(0);
         search=hit1.getString("webformatURL");
         Log.d(LOG_TAG,"search is "+search);



        }
        catch (Exception e){
            Log.e(LOG_TAG,"GOT AN EXCEPTION BRO");
        }
            return search;
    }


}
