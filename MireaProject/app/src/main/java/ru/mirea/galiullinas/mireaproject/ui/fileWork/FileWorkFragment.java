package ru.mirea.galiullinas.mireaproject.ui.fileWork;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    }
}