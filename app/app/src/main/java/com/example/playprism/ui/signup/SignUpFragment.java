package com.example.playprism.ui.signup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.playprism.R;
import com.example.playprism.databinding.FragmentSignUpBinding;
import com.example.playprism.ui.giveaways.GiveawaysItemFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SignUpFragment extends Fragment {

    private FragmentSignUpBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSignUpBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        BottomNavigationView navView = getActivity().findViewById(R.id.nav_view);
        navView.setVisibility(View.GONE);

        binding.buttonLater.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.navigation_login);
        });

        binding.buttonSignUp.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.navigation_giveaways);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}