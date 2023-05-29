package com.example.playprism.ui.giveaways;

import static com.example.playprism.bl.services.RequestManager.makeGetRequest;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.example.playprism.R;
import com.example.playprism.bl.adapters.GiveawaysAdapter;
import com.example.playprism.bl.models.GiveawaysItem;
import com.example.playprism.bl.responses.GiveawaysResponse;
import com.example.playprism.bl.services.RequestManager;
import com.example.playprism.bl.services.ResponseCallback;
import com.example.playprism.bl.util.JsonParser;
import com.example.playprism.databinding.FragmentGiveawaysBinding;
import com.example.playprism.bl.models.UserData;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GiveawaysFragment extends Fragment {

    private FragmentGiveawaysBinding binding;
    private List<GiveawaysResponse> giveawaysResponseItems;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        BottomNavigationView navView = getActivity().findViewById(R.id.nav_view);
        navView.setVisibility(View.VISIBLE);

        GiveawaysViewModel giveawaysViewModel =
                new ViewModelProvider(this).get(GiveawaysViewModel.class);

        binding = FragmentGiveawaysBinding.inflate(inflater, container, false);

        showGiveaways();
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private void showGiveaways() {

        UserData user = JsonParser.getUser(getContext());
        String accessToken = user.getAccessToken();

        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("PageInfo.Size", 20);
            jsonParams.put("PageInfo.Number", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String url = RequestManager.BASE_URL + "giveaways";
        String params = "?PageInfo.Size=20&PageInfo.Number=1";
        String fullUrl = url + params;

        makeGetRequest(getContext(), fullUrl, new ResponseCallback() {
            @Override
            public void onResponse(String response) {
                giveawaysResponseItems = JsonParser.getGiveaways(response);
                Log.i("Giveaways", giveawaysResponseItems.toString());

                // Initialize and set the adapter here
                GiveawaysAdapter adapter = new GiveawaysAdapter(getContext(), giveawaysResponseItems);
                RecyclerView recyclerView = binding.recyclerView;
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(adapter);
                recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
            }
            @Override
            public void onError(VolleyError error) {
                Toast.makeText(getActivity(), "Failed to get Giveaways!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}