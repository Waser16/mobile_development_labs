package ru.mirea.galiullinas.mireaproject.ui.sharedPreferences;
import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.mirea.galiullinas.mireaproject.databinding.FragmentProfileBinding;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private Button saveButton;
    private EditText editTextName;
    private EditText editTextAge;
    private EditText editTextAbout;
    private final static String FILE_NAME = "mirea_settings";
    private static final String NAME_KEY = "name";
    private static final String AGE_KEY = "age";
    private static final String ABOUT_KEY = "about";

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        editTextName = binding.editTextName;
        editTextAge = binding.editTextAge;
        editTextAbout = binding.editTextAbout;

        fillProfileFromPrefs();
        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfile();
            }
        });
    }

    private void saveProfile() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(NAME_KEY, editTextName.getText().toString());
        editor.putString(AGE_KEY, editTextAge.getText().toString());
        editor.putString(ABOUT_KEY, editTextAbout.getText().toString());
        editor.apply();
        Toast.makeText(getContext(), "Сохранение данных прошло успешно", Toast.LENGTH_SHORT);
    }

    private void fillProfileFromPrefs() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editTextName.setText(sharedPreferences.getString(NAME_KEY, "unknown"));
        editTextAge.setText(sharedPreferences.getString(AGE_KEY, "unknown"));
        editTextAbout.setText(sharedPreferences.getString(ABOUT_KEY, "unknown"));
    }


}
