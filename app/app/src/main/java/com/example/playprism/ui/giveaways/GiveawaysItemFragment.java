package com.example.playprism.ui.giveaways;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.playprism.databinding.FragmentGiveawaysItemProfileBinding;

public class GiveawaysItemFragment extends Fragment {
    private FragmentGiveawaysItemProfileBinding binding;
    private View root;
    private static String giveawayId;

    @SuppressLint("ResourceType")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentGiveawaysItemProfileBinding.inflate(inflater, container, false);

        binding.bigTitle.setText("Giveaway " + giveawayId);

        root = binding.getRoot();

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public static void setGiveawayId(String giveawayId) {
        GiveawaysItemFragment.giveawayId = giveawayId;
    }

}
