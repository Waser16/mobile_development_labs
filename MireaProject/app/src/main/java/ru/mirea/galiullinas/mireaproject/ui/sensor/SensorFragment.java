package ru.mirea.galiullinas.mireaproject.ui.sensor;

import static androidx.core.content.ContextCompat.getSystemService;

import android.app.Service;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.mirea.galiullinas.mireaproject.R;
import ru.mirea.galiullinas.mireaproject.databinding.FragmentMicrophoneBinding;
import ru.mirea.galiullinas.mireaproject.databinding.FragmentSensorBinding;


public class SensorFragment extends Fragment implements SensorEventListener {


    private FragmentSensorBinding binding;

    private SensorManager sensorManager;
    private Sensor sensor;
    public SensorFragment() {
        // Required empty public constructor
    }

    public static SensorFragment newInstance(String param1, String param2) {
        SensorFragment fragment = new SensorFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSensorBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        sensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);



        return view;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            binding.textViewValue.setText("" + event.values[0] + " lux");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onResume() {
        // Register a listener for the sensor.
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause();
        sensorManager.unregisterListener(this, sensor);
    }
}