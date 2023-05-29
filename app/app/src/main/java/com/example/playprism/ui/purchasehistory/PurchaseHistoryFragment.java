package com.example.playprism.ui.purchasehistory;

import static com.example.playprism.bl.services.RequestManager.makeGetRequest;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.playprism.R;
import com.example.playprism.bl.adapters.GiveawaysAdapter;
import com.example.playprism.bl.adapters.HistoryPurchaseAdapter;
import com.example.playprism.bl.models.UserData;
import com.example.playprism.bl.responses.GiveawaysResponse;
import com.example.playprism.bl.services.RequestManager;
import com.example.playprism.bl.services.ResponseCallback;
import com.example.playprism.bl.util.JsonParser;
import com.example.playprism.databinding.FragmentPurchaseHistoryBinding;
import com.example.playprism.bl.models.PurchasedItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PurchaseHistoryFragment extends Fragment {

    private FragmentPurchaseHistoryBinding binding;

    private List<PurchasedItem> purchaseHistoryResponseItems;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        BottomNavigationView navView = getActivity().findViewById(R.id.nav_view);
        navView.setVisibility(View.VISIBLE);
        navView.getMenu().getItem(0).setChecked(true);

        PurchaseHistoryViewModel giveawaysViewModel = new ViewModelProvider(this).get(PurchaseHistoryViewModel.class);

        binding = FragmentPurchaseHistoryBinding.inflate(inflater, container, false);

        binding.backIcon.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.navigation_profile);
        });

        showPurchaseHistory();

        return binding.getRoot();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void showPurchaseHistory() {

        UserData user = JsonParser.getUser(getContext());
        String id = user.getUserId();

        String url = RequestManager.BASE_URL + "users/" + id + "/history";

        makeGetRequest(getContext(), url, new ResponseCallback() {
            @Override
            public void onResponse(String response) {
                try {
                    purchaseHistoryResponseItems = JsonParser.getPurchasedItems(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.i("History", purchaseHistoryResponseItems.toString());

                // Initialize and set the adapter here
                HistoryPurchaseAdapter adapter = new HistoryPurchaseAdapter(getContext(), purchaseHistoryResponseItems);
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
