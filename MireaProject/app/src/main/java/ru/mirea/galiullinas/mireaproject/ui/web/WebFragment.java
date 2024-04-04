package ru.mirea.galiullinas.mireaproject.ui.web;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewClient;

import ru.mirea.galiullinas.mireaproject.R;
import ru.mirea.galiullinas.mireaproject.databinding.FragmentWebBinding;

public class WebFragment extends Fragment {

    private WebViewModel mViewModel;

    private FragmentWebBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        WebViewModel webViewModel =
                new ViewModelProvider(this).get(WebViewModel.class);

        binding = FragmentWebBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.webView.setWebViewClient(new WebViewClient());
        binding.webView.loadUrl("https://www.google.ru/");
        binding.webView.getSettings().setJavaScriptEnabled(true);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}