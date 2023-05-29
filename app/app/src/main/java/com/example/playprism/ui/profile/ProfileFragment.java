package com.example.playprism.ui.profile;

import static com.example.playprism.bl.services.RequestManager.makeGetRequest;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.example.playprism.MainActivity;
import com.example.playprism.R;
import com.example.playprism.bl.adapters.HistoryPurchaseAdapter;
import com.example.playprism.bl.models.UserData;
import com.example.playprism.bl.models.UserProfile;
import com.example.playprism.bl.services.RequestManager;
import com.example.playprism.bl.services.ResponseCallback;
import com.example.playprism.bl.util.JsonParser;
import com.example.playprism.databinding.FragmentProfileBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;

public class ProfileFragment extends Fragment {

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

        getUserInfo();


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

    private void getUserInfo(){
        UserData user = JsonParser.getUser(getContext());
        String id = user.getUserId();

        String url = RequestManager.BASE_URL + "users/" + id;

        makeGetRequest(getContext(), url, new ResponseCallback() {
            @Override
            public void onResponse(String response) {
                try {
                    UserProfile userData = JsonParser.getProfile(response);
                    binding.textView.setText(userData.getNickname());
                    binding.emailTextView.setText(userData.getEmail());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onError(VolleyError error) {
                Toast.makeText(getActivity(), "Failed to get Giveaways!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}