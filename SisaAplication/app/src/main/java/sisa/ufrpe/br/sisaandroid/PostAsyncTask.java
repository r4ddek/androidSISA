package sisa.ufrpe.br.sisaandroid;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class PostAsyncTask extends AsyncTask<JSONObject, Void, String> {

    String url;
    JSONObject jsonObject;
    OkHttpClient client;
    MediaType JSON;
    String postResponse;
    public ResultadoAsync delegate = null;



    public PostAsyncTask(String url, JSONObject jSON) {
        this.url = url;
        this.jsonObject = jSON;
    }

    private String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    @Override
    protected String doInBackground(JSONObject... params) {
        client = new OkHttpClient();
        JSON = MediaType.parse("application/json; charset=utf-8");
        try {
            postResponse = post(url, jsonObject.toString());
            Log.d("DEVOLVEU: ", postResponse);
        }catch (Exception e){
            e.printStackTrace();
        }
        return postResponse;
    }

    @Override
    protected void onPostExecute(String postResponse) {
        System.out.println(postResponse);
        delegate.processFinish(postResponse);
    }

}