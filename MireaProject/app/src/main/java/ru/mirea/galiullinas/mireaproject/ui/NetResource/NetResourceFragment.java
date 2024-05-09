package ru.mirea.galiullinas.mireaproject.ui.NetResource;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import ru.mirea.galiullinas.mireaproject.databinding.FragmentNetResourseBinding;

public class NetResourceFragment extends Fragment {
    private FragmentNetResourseBinding binding;
    private final String TAG = NetResourceFragment.class.getSimpleName();

    public NetResourceFragment() {
        // Required empty public constructor
    }

    public static NetResourceFragment newInstance(String param1, String param2) {
        NetResourceFragment fragment = new NetResourceFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_net_resourse, container, false);
        binding = FragmentNetResourseBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.buttonGetActivity.setOnClickListener(v -> new BoredomActivity().execute());

        return view;
    }


    private class BoredomActivity extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                return getInfo();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d(TAG, result);
            if (result != null) {
                try {
                    JSONObject obj = new JSONObject(result);
                    String activityName = obj.getString("activity");
                    String activityType = obj.getString("type");
                    String activityParticipantsAmt = obj.getString("participants");
                    String activityPrice = obj.getString("price");

                    binding.textViewActivity.setText(activityName);
                    binding.textViewActivityType.setText(activityType);
                    binding.textViewParticipantAmt.setText(activityParticipantsAmt);
                    binding.textViewActivityPrice.setText(activityPrice);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(TAG, "Произошла ошибка парсинга");
                }
            }
            else {
                Log.d(TAG, "JSON ответа пуст");
            }


        }

        protected String getInfo() throws IOException {
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            String data = "";
            try {
                URL url = new URL("https://www.boredapi.com/api/activity/");
                urlConnection = (HttpURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    inputStream = urlConnection.getInputStream();
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    int read = 0;
                    while ((read = inputStream.read()) != -1) {
                        bos.write(read);
                    }
                    bos.close();
                    data = bos.toString();
                }
                else {
                    data = urlConnection.getResponseMessage()+". Error Code: " + urlConnection.getResponseCode();
                }
                urlConnection.disconnect();
            }
            catch (IOException e) {
                e.getMessage();
            }
            finally {
                if (inputStream != null) inputStream.close();
            }
            return data;
        }
    }
}