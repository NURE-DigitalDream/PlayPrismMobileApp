package com.example.playprism.ui.giveaways;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.playprism.databinding.FragmentGiveawaysBinding;

public class GiveawaysFragment extends Fragment {

    private FragmentGiveawaysBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GiveawaysViewModel giveawaysViewModel =
                new ViewModelProvider(this).get(GiveawaysViewModel.class);

        binding = FragmentGiveawaysBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
        giveawaysViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}