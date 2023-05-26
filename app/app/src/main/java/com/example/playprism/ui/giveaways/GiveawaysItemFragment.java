package com.example.playprism.ui.giveaways;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.playprism.R;
import com.example.playprism.databinding.FragmentGiveawaysItemProfileBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class GiveawaysItemFragment extends Fragment {
    private FragmentGiveawaysItemProfileBinding binding;
    private View root;
    private static String giveawayId;

    @SuppressLint("ResourceType")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        BottomNavigationView navView = getActivity().findViewById(R.id.nav_view);
        navView.setVisibility(View.VISIBLE);
        navView.getMenu().getItem(1).setChecked(true);

        binding = FragmentGiveawaysItemProfileBinding.inflate(inflater, container, false);

        binding.bigTitle.setText("Giveaway " + giveawayId);
        binding.backIcon.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.navigation_giveaways);
        });



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
