package com.example.onlinelibrary;

import android.os.AsyncTask;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class UploadAsync  extends AsyncTask<String,Void,String> {

    private static final String TAG = UploadAsync.class.getSimpleName();


    @Override
    protected String doInBackground(String... strings) {

        String data = null;
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(strings[0]);
            URLConnection connection = url.openConnection();
            if(connection instanceof HttpURLConnection) {
                httpURLConnection = (HttpURLConnection) connection;
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestProperty("Connection","keep-alive");
                httpURLConnection.setRequestProperty("Content-Type","multipart/form-data; boundary=***");
                httpURLConnection.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
                wr.writeBytes("--***" + "\r\n");
                wr.writeBytes("Content-Disposition: form-data; name=\"myfile\";filename=\"CraciunPavel.json\"\r\n");
                wr.writeBytes("\r\n");
                wr.writeBytes(strings[0]);
                wr.writeBytes("\r\n");
                wr.writeBytes("--***__");
                wr.flush();
                wr.close();

                InputStream in = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(in);
                int inputStreamData = inputStreamReader.read();
                while(inputStreamData != -1) {
                    char curr = (char) inputStreamData;
                    inputStreamData = inputStreamReader.read();
                    data+=curr;

                }


            } else {
                throw new Exception("Invalid HTTP Connection");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return data;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
       Log.d("TAG",  result);
    }
}
