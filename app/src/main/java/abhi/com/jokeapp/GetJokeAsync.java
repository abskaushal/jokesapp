package abhi.com.jokeapp;

import android.content.Context;
import android.os.AsyncTask;

import com.abhishek.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by Abhishek on 15-May-16.
 */
public class GetJokeAsync extends AsyncTask<Void, Void, String> {
    private MyApi myApiService = null;
    private IAsyncListener mListener;
    private static final String URL = "https://YOUR_APP_ID.appspot.com/_ah/api/";

    public GetJokeAsync(IAsyncListener listener) {

        if (listener == null) {
            throw new IllegalArgumentException("Null arguments passed");
        }
        mListener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mListener.onAsyncStart();
    }

    @Override
    protected String doInBackground(Void... params) {
        if (myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl(URL)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            myApiService = builder.build();
        }


        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        mListener.onAsyncFinish(result);
    }
}
