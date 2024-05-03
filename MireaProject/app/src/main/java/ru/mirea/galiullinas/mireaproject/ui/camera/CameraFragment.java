package ru.mirea.galiullinas.mireaproject.ui.camera;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;

import ru.mirea.galiullinas.mireaproject.R;
import ru.mirea.galiullinas.mireaproject.databinding.FragmentCameraBinding;
import ru.mirea.galiullinas.mireaproject.databinding.FragmentMicrophoneBinding;


public class CameraFragment extends Fragment {

    private FragmentCameraBinding binding;
    private Uri imageUri;
    private boolean isWork = false;
    private int REQUEST_CODE_PERMISSION = 100;
    public CameraFragment() {
        // Required empty public constructor
    }

    public static CameraFragment newInstance(String param1, String param2) {
        CameraFragment fragment = new CameraFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /// return inflater.inflate(R.layout.fragment_camera, container, false);
        binding = FragmentCameraBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int cameraPermissionStatus = ContextCompat.checkSelfPermission(view.getContext(), android.Manifest.permission.CAMERA);
        int storagePermissionStatus = ContextCompat.checkSelfPermission(view.getContext(), android.Manifest.permission.
                WRITE_EXTERNAL_STORAGE);

        if  (cameraPermissionStatus == PackageManager.PERMISSION_GRANTED && storagePermissionStatus
                == PackageManager.PERMISSION_GRANTED)  {
            isWork = true;
        }  else  {
            requestPermissions( new String[] { android.Manifest.permission.CAMERA,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            },  REQUEST_CODE_PERMISSION);
        }

        // Работа с камерой
        ActivityResultCallback<ActivityResult> cameraCallback = new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    binding.imageView.setImageURI(imageUri);
                }
            }
        };
        ActivityResultLauncher<Intent> cameraActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                cameraCallback);
        binding.buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (isWork) {
                    try {
                        File photoFile = createImageFile();
                        String authorities = getContext().getApplicationContext().getPackageName() + ".fileprovider";
                        imageUri = FileProvider.getUriForFile(getActivity(), authorities, photoFile);
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                        cameraActivityResultLauncher.launch(cameraIntent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        // Добавление текстовых данных
        ActivityResultCallback<ActivityResult> textDataCallback = new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    // Toast.makeText(getContext(), data.getStringExtra("name"), Toast.LENGTH_SHORT).show();
                    binding.textViewName.setText(data.getStringExtra("name"));
                    binding.textViewAge.setText(data.getStringExtra("age"));
                    binding.textViewAbout.setText(data.getStringExtra("about"));
                }
            }
        };
        ActivityResultLauncher<Intent> textDataActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                textDataCallback);

        binding.buttonTextData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProfileDataActivity.class);
                textDataActivityResultLauncher.launch(intent);
            }
        });

    }

    private File createImageFile()	throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",	Locale.ENGLISH).format(new Date());
        String imageFileName =	"IMAGE_" + timeStamp + "_";
        File storageDirectory = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName,".jpg",	storageDirectory);
    }
}