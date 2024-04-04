package ru.mirea.galiullinas.mireaproject.ui.web;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WebViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public WebViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Это Web-фрагмент");
    }

    public LiveData<String> getText() {return mText;}
}