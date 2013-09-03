package com.app.java2patrick;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;
import library.WebConnection;
import android.os.AsyncTask;
import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import org.json.JSONArray;

public class MainActivity extends Activity {

    private EditText mEditText;
    Boolean connected = false;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainview);

        getPost();
    }

    private void getPost() {
        String baseURL = "http://api.tumblr.com/v2/blog/www.theappmob.com/posts?api_key=zoxEpyAIr35TvVt6P6XYGrN5WiENeOrkqP8sECu5XvuIzR5cRP&tag=review";

        URL finalURL;
        try{
            finalURL = new URL(baseURL);
            PostRequest pr = new PostRequest();
            pr.execute(finalURL);
        } catch (MalformedURLException e) {
            Log.e("BAD URL", "ERROR");
            finalURL = null;
        }
    }

    private class PostRequest extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            String response = "";
            for(URL url: urls) {
                response = WebConnection.getURLStringResponse(url);
            }

            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("URL RESPONSE", result);
            try{
                JSONObject json = new JSONObject(result);
                final JSONArray results = json.getJSONObject("response").getJSONArray("posts");


            } catch (JSONException e) {
                Log.e("JSON", e.getLocalizedMessage());
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
