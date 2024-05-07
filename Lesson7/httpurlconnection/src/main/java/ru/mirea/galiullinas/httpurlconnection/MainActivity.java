package ru.mirea.galiullinas.httpurlconnection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ContentInfo;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import ru.mirea.galiullinas.httpurlconnection.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager connectivityManager =
                        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = null;
                if (connectivityManager != null) {
                    networkInfo = connectivityManager.getActiveNetworkInfo();
                }

                if (networkInfo != null && networkInfo.isConnected()) {
                    new DownloadPageTask().execute("https://ipinfo.io/json");

                }
            }
        });
    }

    private class DownloadPageTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            binding.loadText.setText("Загружаем...");
        }
        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadIpInfo(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        }
        @Override
        protected void onPostExecute(String result) {
            Log.d(MainActivity.class.getSimpleName(), result);
            binding.loadText.setText("Готово!");
            // ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
            //       android.R.layout.simple_list_item_1)
            Log.d(TAG, "Result" + result);
            try {
                JSONObject obj = new JSONObject(result);
                if (obj.has("current_weather")) {
                    JSONObject weatherInfo = obj.getJSONObject("current_weather");
                    binding.weatherText.setText("ПОГОДА\nТемпература: " + weatherInfo.getString("temperature") + "\n" +
                            "Скорость ветра: " + weatherInfo.getString("windspeed"));
                }
                else {
                    Iterator<String> iterator = obj.keys();
                    String key = iterator.next();
                    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
                    while (iterator.hasNext()) {
                        key = iterator.next();

                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("Name", key.toLowerCase());
                        hashMap.put("Value", obj.getString(key));

                        arrayList.add(hashMap);
                    }

                    SimpleAdapter mHistory =
                            new SimpleAdapter(MainActivity.this, arrayList, android.R.layout.simple_list_item_2,
                                    new String[] {"Name", "Value"},
                                    new int[] {android.R.id.text1, android.R.id.text2});
                    binding.listView.setAdapter(mHistory);

                    String latitude = obj.getString("loc").split(",")[0];
                    String longitude = obj.getString("loc").split(",")[1];
                    String path = String.format("https://api.open-meteo.com/v1/forecast?latitude=%s&longitude=%s&current_weather=true", latitude, longitude);
                    new DownloadPageTask().execute(path);
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }


        }
        private String downloadIpInfo(String address) throws IOException {
            InputStream inputStream = null;
            String data = "";
            try {
                URL url = new URL(address);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(100000);
                connection.setConnectTimeout(100000);
                connection.setRequestMethod("GET");
                connection.setInstanceFollowRedirects(true);
                connection.setUseCaches(false);
                connection.setDoInput(true);
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) { // 200 OK
                    inputStream = connection.getInputStream();
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    int read = 0;
                    while ((read = inputStream.read()) != -1) {
                        bos.write(read); }
                    bos.close();
                    data = bos.toString();
                } else {
                    data = connection.getResponseMessage()+". Error Code: " + responseCode;
                }
                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
            return data;
        }
    }
}