package com.app.java2patrick;

import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;
import library.WebConnection;
import library.PostDisplay;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

    Context context;
    Boolean connected = false;
    WebView webView;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mainview);

        context = this;

        //Add search handler
        Button searchButton = (Button) findViewById(R.id.searchButton);
        mEditText = (EditText) findViewById(R.id.searchField);

        searchButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                getPost(mEditText.getText().toString());
            }
        });

        //Detect Network Connection
        connected = WebConnection.getConnectionStatus(context);
        if(connected) {
            Log.i("NETWORK CONNECTION", WebConnection.getConnectionType(context));
        }

    }

    private void getPost(String tag) {
        String baseURL = "https://public-api.wordpress.com/rest/v1/sites/everydaygamers.com/posts/?number=5&search=";
        String baseURLWithTag = baseURL + tag;

        URL finalURL;
        try{
            finalURL = new URL(baseURLWithTag);
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
                for (int i = 0; i < 5; i++) {
                    final JSONObject results = json.getJSONArray("posts").getJSONObject(i);

                    String title = results.getString("title");
                    String url = results.getString("short_URL");

                    int resId = getResources().getIdentifier("game_title" + i, "id", getPackageName());
                    TextView postTitle = (TextView) findViewById(resId);

                    int resId2 = getResources().getIdentifier("game_url" + i, "id", getPackageName());
                    TextView postUrl = (TextView) findViewById(resId2);

                    postTitle.setText(title);
                    postUrl.setText(url);

//                    Button openButton = (Button) findViewById(R.id.button1);
//                    openButton.setOnClickListener(new OnClickListener() {
//
//                        @Override
//                        public void onClick(View v) {
//                            Intent browserIntent = null;
//                            try {
//                                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(results.getString("short_URL")));
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                            startActivity(browserIntent);
//                        }
//                    });
                }

            } catch (JSONException e) {
                Log.e("JSON", e.getLocalizedMessage());
            }
        }
    }
}