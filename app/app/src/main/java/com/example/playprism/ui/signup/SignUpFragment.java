package com.example.playprism.ui.signup;

import static com.example.playprism.bl.services.RequestManager.makeRequest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.android.volley.VolleyError;
import com.example.playprism.MainActivity;
import com.example.playprism.R;
import com.example.playprism.bl.services.ResponseCallback;
import com.example.playprism.databinding.FragmentSignUpBinding;
import com.example.playprism.ui.giveaways.GiveawaysItemFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONObject;

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

        binding.buttonSignUp.setOnClickListener(v -> signUp());

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private NavController getNavController() {
        MainActivity mainActivity = (MainActivity) getActivity();

        if (mainActivity == null) {
            return null;
        }

        NavController navController = Navigation.findNavController(mainActivity, R.id.nav_host_fragment_activity_main);
        return navController;
    }

    private void signUp() {
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("email", binding.etEmail.getText().toString());
            jsonParams.put("password", binding.etPassword.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String url = "http://10.0.2.2:5000/api/account/register";

        makeRequest(getContext(), url, jsonParams, new ResponseCallback() {
            @Override
            public void onResponse(String response) {
                NavController navController = getNavController();
                if (navController != null) {
                    navController.navigate(R.id.navigation_giveaways);
                }
            }

            @Override
            public void onError(VolleyError error) {
                Toast.makeText(getActivity(), "Registration not allowed, user with such email already exists.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}