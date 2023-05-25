package com.example.playprism.ui.purchasehistory;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playprism.adapters.HistoryPurchaseAdapter;
import com.example.playprism.databinding.FragmentPurchaseHistoryBinding;
import com.example.playprism.models.PurchasedItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PurchaseHistoryFragment extends Fragment {

    private FragmentPurchaseHistoryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PurchaseHistoryViewModel giveawaysViewModel =
                new ViewModelProvider(this).get(PurchaseHistoryViewModel.class);

        binding = FragmentPurchaseHistoryBinding.inflate(inflater, container, false);

        // create list:
        List<PurchasedItem> purchasedItems = new ArrayList<>();
        purchasedItems.add(new PurchasedItem("title 1", Calendar.getInstance().getTime(), 1750));
        purchasedItems.add(new PurchasedItem("title 2", Calendar.getInstance().getTime(), 1750));
        purchasedItems.add(new PurchasedItem("title 3", Calendar.getInstance().getTime(), 1750));
        purchasedItems.add(new PurchasedItem("title 4", Calendar.getInstance().getTime(), 1750));
        purchasedItems.add(new PurchasedItem("title 5", Calendar.getInstance().getTime(), 1750));
        purchasedItems.add(new PurchasedItem("title 6", Calendar.getInstance().getTime(), 1750));
        purchasedItems.add(new PurchasedItem("title 7", Calendar.getInstance().getTime(), 1750));
        purchasedItems.add(new PurchasedItem("title 8", Calendar.getInstance().getTime(), 1750));
        purchasedItems.add(new PurchasedItem("title 9", Calendar.getInstance().getTime(), 1750));
        purchasedItems.add(new PurchasedItem("title 10", Calendar.getInstance().getTime(), 1750));
        purchasedItems.add(new PurchasedItem("title 11", Calendar.getInstance().getTime(), 1750));
        purchasedItems.add(new PurchasedItem("title 12", Calendar.getInstance().getTime(), 1750));
        purchasedItems.add(new PurchasedItem("title 13", Calendar.getInstance().getTime(), 1750));
        purchasedItems.add(new PurchasedItem("title 14", Calendar.getInstance().getTime(), 1750));


        binding.backIcon.setOnClickListener(v -> getActivity().onBackPressed());

        // define the adapter:
        HistoryPurchaseAdapter adapter = new HistoryPurchaseAdapter(this.getContext(), purchasedItems);

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
}
