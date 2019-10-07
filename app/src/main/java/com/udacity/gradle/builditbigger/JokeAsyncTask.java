package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.sauravchhabra.jokedisplayer.JokeDisplayActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class JokeAsyncTask extends AsyncTask<Void, Void, String> {

    private static MyApi myApi = null;
    private Context mContext;
    private static final String LOG_TAG = JokeAsyncTask.class.getSimpleName();

    public JokeAsyncTask(Context context) {
        this.mContext = context;
    }

    @Override
    protected String doInBackground(Void... voids) {
        if (myApi == null) { // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://192.168.1.6:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });
            myApi = builder.build();
        }

        try {
            return myApi.getJoke().execute().getData();
        } catch (IOException e) {
            Log.e(LOG_TAG, e.getMessage());
            return "";
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Intent intent = new Intent(mContext, JokeDisplayActivity.class);
        intent.putExtra("joke", result);
        mContext.startActivity(intent);
    }
}
