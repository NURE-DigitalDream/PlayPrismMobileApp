package com.example.playprism.ui.giveaways;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GiveawaysViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public GiveawaysViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is giveaways fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}