package ru.mirea.galiullinas.mireaproject.ui.fileWork;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ru.mirea.galiullinas.mireaproject.MainActivity;
import ru.mirea.galiullinas.mireaproject.R;
import ru.mirea.galiullinas.mireaproject.databinding.FragmentFileWorkBinding;
import ru.mirea.galiullinas.mireaproject.databinding.FragmentWorkerBinding;

public class FileWorkFragment extends Fragment {

    private FragmentFileWorkBinding binding;
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
                String encryptedFileText = getEncrypted(fileText).toString();

                showDialog(fileName, encryptedFileText);
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

        myDialog.show();
    }

    private byte[] getEncrypted(String text) {
        try {
            byte[] cipherText = Base64.encode(text.getBytes(), Base64.DEFAULT);

            return cipherText;
        } catch (Exception e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        return "".getBytes();
    }

    private String getDecrypted(String content) {
        try {
            String cipherText = new String(Base64.decode(content, Base64.DEFAULT));

            return cipherText;
        } catch (Exception e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        return "";
    }
}