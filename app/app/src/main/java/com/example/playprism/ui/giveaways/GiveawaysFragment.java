package com.example.playprism.ui.giveaways;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playprism.R;
import com.example.playprism.adapters.GiveawaysAdapter;
import com.example.playprism.adapters.HistoryPurchaseAdapter;
import com.example.playprism.databinding.FragmentGiveawaysBinding;
import com.example.playprism.models.GiveawaysItem;
import com.example.playprism.models.PurchasedItem;
import com.example.playprism.ui.util.OnBackPressed;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GiveawaysFragment extends Fragment implements OnBackPressed {

    private FragmentGiveawaysBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        BottomNavigationView navView = getActivity().findViewById(R.id.nav_view);
        navView.setVisibility(View.VISIBLE);

        GiveawaysViewModel giveawaysViewModel =
                new ViewModelProvider(this).get(GiveawaysViewModel.class);

        binding = FragmentGiveawaysBinding.inflate(inflater, container, false);

        // create list:
        List<GiveawaysItem> giveawaysItems = new ArrayList<>();
        giveawaysItems.add(new GiveawaysItem("1","title 1", "Ключ", null));
        giveawaysItems.add(new GiveawaysItem("2","title 2", "Ключ", null));
        giveawaysItems.add(new GiveawaysItem("3","title 3", "Ключ", null));
        giveawaysItems.add(new GiveawaysItem("4","title 4", "Ключ", null));
        giveawaysItems.add(new GiveawaysItem("5","title 5", "Ключ", null));
        giveawaysItems.add(new GiveawaysItem("6","title 6", "Ключ", null));
        giveawaysItems.add(new GiveawaysItem("7","title 7", "Ключ", null));
        giveawaysItems.add(new GiveawaysItem("8","title 8", "Ключ", null));
        giveawaysItems.add(new GiveawaysItem("9","title 9", "Ключ", null));
        giveawaysItems.add(new GiveawaysItem("10","title 10", "Ключ", null));
        giveawaysItems.add(new GiveawaysItem("11","title 11", "Ключ", null));

        // define the adapter:
        GiveawaysAdapter adapter = new GiveawaysAdapter(this.getContext(), giveawaysItems);

        // set the RecyclerView:
        RecyclerView recyclerView = binding.recyclerView;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL));


        return binding.getRoot();
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