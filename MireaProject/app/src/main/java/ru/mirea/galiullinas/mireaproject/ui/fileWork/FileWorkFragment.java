package ru.mirea.galiullinas.mireaproject.ui.fileWork;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import ru.mirea.galiullinas.mireaproject.MainActivity;
import ru.mirea.galiullinas.mireaproject.R;
import ru.mirea.galiullinas.mireaproject.databinding.FragmentFileWorkBinding;
import ru.mirea.galiullinas.mireaproject.databinding.FragmentWorkerBinding;

public class FileWorkFragment extends Fragment {

    private FragmentFileWorkBinding binding;
    private final String TAG = "FileWorkFragment";
    public FileWorkFragment() {
        // Required empty public constructor
    }

    public static FileWorkFragment newInstance(String param1, String param2) {
        FileWorkFragment fragment = new FileWorkFragment();
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
        // return inflater.inflate(R.layout.fragment_file_work, container, false);
        binding = FragmentFileWorkBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        binding.floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(requireContext(), "AOBA", Toast.LENGTH_LONG).show();

                String fileName = binding.editTextDialogFileName.getText().toString();
                String fileText = binding.editTextDialogFileText.getText().toString();
                String encryptedFileText = encodeString(fileText);

                showDialog(fileName, encryptedFileText);
            }
        });

        binding.buttonLoadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileName = binding.editTextDialogFileName.getText().toString();
                String encryptedText = readFromExternalStorage(fileName);
                Log.d(TAG, "encrypted text " + encryptedText);
                String decryptedText = decodeString(encryptedText);
                Log.d(TAG, "decrypted text " + decryptedText);
                binding.editTextDialogFileText.setText(decryptedText);
            }
        });
    }

    private void showDialog(String fileName, String fileText) {
        View dialogBinding = LayoutInflater.from(getActivity()).inflate(R.layout.custom_dialog, null);
        Dialog myDialog = new Dialog(requireContext());
        myDialog.setContentView(dialogBinding);

        TextView textViewFileName = myDialog.findViewById(R.id.textViewConfirmation);
        textViewFileName.append(fileName + "?");
        TextView textViewFileText = myDialog.findViewById(R.id.textViewEncryptedText);
        textViewFileText.setText(fileText);

        Button buttonSave = myDialog.findViewById(R.id.buttonConfirmSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeToExternalStorage(fileName, fileText);
            }
        });

        myDialog.show();
    }

    private String encodeString(String s) {
        byte[] data = new byte[0];

        try {
            data = s.getBytes("UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            String base64Encoded = Base64.encodeToString(data, Base64.DEFAULT);

            return base64Encoded;

        }
    }

    private String decodeString(String encoded) {
        byte[] dataDec = Base64.decode(encoded, Base64.DEFAULT);
        String decodedString = "";
        try {

            decodedString = new String(dataDec, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

        } finally {

            return decodedString;
        }
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
    /* Проверяем внешнее хранилище на доступность чтения */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public String readFromExternalStorage(String fileName) {
        if (!isExternalStorageReadable()) return "Storage is not readable";

        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(path, fileName);
        Log.d("FILEPATH", file.getAbsoluteFile().toString());
        try {
            FileInputStream fileInputStream = new FileInputStream(file.getAbsoluteFile());
            InputStreamReader input = new InputStreamReader(fileInputStream);
            BufferedReader reader = new BufferedReader(input);
            List<String> lines = new ArrayList<String>();
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
//            binding.editTextQuote.setText(String.join("", lines));
            String result = String.join("\n", lines);
            return result;
        }
        catch (Exception e) {
            Log.d(TAG, "Reading error: " + e.getMessage());
        }
        return "Storage is not readable";
    }

    public void writeToExternalStorage(String fileName, String data) {
        if (!isExternalStorageWritable()) return;

        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(path, fileName);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsoluteFile());
            OutputStreamWriter output = new OutputStreamWriter(fileOutputStream);
            output.write(data);
            output.close();
            Toast.makeText(requireContext(), "Запись в файл успешно закончена", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            Log.d(TAG, "Writing error: " + e.getMessage());
        }
    }

}