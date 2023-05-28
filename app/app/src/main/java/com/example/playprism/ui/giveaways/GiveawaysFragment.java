package com.example.playprism.ui.giveaways;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playprism.R;
import com.example.playprism.bl.adapters.GiveawaysAdapter;
import com.example.playprism.databinding.FragmentGiveawaysBinding;
import com.example.playprism.bl.models.GiveawaysItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GiveawaysFragment extends Fragment {

    private FragmentGiveawaysBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        BottomNavigationView navView = getActivity().findViewById(R.id.nav_view);
        navView.setVisibility(View.VISIBLE);

        GiveawaysViewModel giveawaysViewModel =
                new ViewModelProvider(this).get(GiveawaysViewModel.class);

        binding = FragmentGiveawaysBinding.inflate(inflater, container, false);

        Date startDate = new Date();

        Date endDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.set(2023, 5, 15, 18, 0, 0);
        endDate = cal.getTime();

        Context context = this.getContext();

        // create list:
        List<GiveawaysItem> giveawaysItems = new ArrayList<>();
        giveawaysItems.add(new GiveawaysItem("1", "Cyberpunk 2077", ContextCompat.getDrawable(context, R.drawable.giveaways_item_photo_cyberpunk), startDate, endDate, "Ключ", null, "2020", "CD PROJEKT RED", "Пригоди, Рольові, Екшени, Шутери, Відкритий світ, З сюжетом", GiveawayStatus.FINISHED_YOU_NOT_WIN));
        giveawaysItems.add(new GiveawaysItem("2", "The Witcher: Wild Hunt", ContextCompat.getDrawable(context, R.drawable.giveaways_item_photo_the_witcher), startDate, endDate, "Ключ", null, "2020", "CD PROJEKT RED", "Пригоди, Рольові, Екшени, Шутери, Відкритий світ, З сюжетом", GiveawayStatus.FINISHED_YOU_WIN));
        giveawaysItems.add(new GiveawaysItem("3", "The Elder Skrolls V: Skyrim", ContextCompat.getDrawable(context, R.drawable.giveaways_item_photo_skyrim), startDate, endDate, "Ключ",null, "2020", "CD PROJEKT RED", "Пригоди, Рольові, Екшени, Шутери, Відкритий світ, З сюжетом", GiveawayStatus.NOT_FINISHED_YOU_SUBSCRIBED));
        giveawaysItems.add(new GiveawaysItem("4", "Resident Evil 4", ContextCompat.getDrawable(context, R.drawable.giveaways_item_photo_resident_evil), startDate, endDate, "Ключ",null, "2020", "CD PROJEKT RED", "Пригоди, Рольові, Екшени, Шутери, Відкритий світ, З сюжетом", GiveawayStatus.NOT_FINISHED_YOU_NOT_SUBSCRIBED));

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
}