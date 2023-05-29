package com.example.playprism.ui.login;

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
import com.example.playprism.bl.services.RequestManager;
import com.example.playprism.bl.services.ResponseCallback;
import com.example.playprism.databinding.FragmentLoginBinding;
import com.example.playprism.ui.giveaways.GiveawaysFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);

        root = binding.getRoot();

        root.findViewById(R.id.button_login).setOnClickListener(v -> login());
        root.findViewById(R.id.button_signUp).setOnClickListener(v -> signUp());

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

    private void login() {

        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("email", binding.etEmail.getText().toString());
            jsonParams.put("password", binding.etPassword.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String url = RequestManager.BASE_URL + "account/login";

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
                Toast.makeText(getActivity(), "Authorization not allowed, please register or check login and password and retry!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void signUp() {

        NavController navController = getNavController();
        if (navController == null) {
            return;
        }
        navController.navigate(R.id.navigation_signUp);

    }
}