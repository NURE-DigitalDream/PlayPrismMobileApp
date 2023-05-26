package com.example.playprism.ui.profile;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.playprism.MainActivity;
import com.example.playprism.R;
import com.example.playprism.databinding.FragmentProfileBinding;
import com.example.playprism.ui.util.OnBackPressed;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileFragment extends Fragment implements OnBackPressed {

    private FragmentProfileBinding binding;

    private View root;

    @SuppressLint("ResourceType")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        BottomNavigationView navView = getActivity().findViewById(R.id.nav_view);
        navView.setVisibility(View.VISIBLE);

        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        binding.buttonSignOut.setOnClickListener(v -> signOut());
        binding.buttonPurchaseHistory.setOnClickListener(v -> showPurchaseHistory());

        root = binding.getRoot();



        return binding.getRoot();
    }

    private void signOut() {
        NavController navController = getNavController();
        if (navController == null) {
            return;
        }

        BottomNavigationView navView = getActivity().findViewById(R.id.nav_view);
        navView.setVisibility(View.GONE);
        navController.navigate(R.id.navigation_login);
    }

    private NavController getNavController() {
        MainActivity mainActivity = (MainActivity) getActivity();

        if (mainActivity == null) {
            return null;
        }

        return Navigation.findNavController(mainActivity, R.id.nav_host_fragment_activity_main);
    }

    private void showPurchaseHistory() {

        NavController navController = getNavController();
        if (navController == null) {
            return;
        }
        navController.navigate(R.id.navigation_purchase_history);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onBackPressed() {

    }
}