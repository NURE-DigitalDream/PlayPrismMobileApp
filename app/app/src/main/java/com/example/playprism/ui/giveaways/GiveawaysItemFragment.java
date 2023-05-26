package com.example.playprism.ui.giveaways;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.playprism.R;
import com.example.playprism.databinding.FragmentGiveawaysItemProfileBinding;
import com.example.playprism.models.GiveawaysItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class GiveawaysItemFragment extends Fragment {
    private FragmentGiveawaysItemProfileBinding binding;
    private View root;
    private static GiveawaysItem giveaway;

    @SuppressLint({"ResourceType", "SetTextI18n"})
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        BottomNavigationView navView = getActivity().findViewById(R.id.nav_view);
        navView.setVisibility(View.VISIBLE);
        navView.getMenu().getItem(1).setChecked(true);

        binding = FragmentGiveawaysItemProfileBinding.inflate(inflater, container, false);
        binding.backIcon.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.navigation_giveaways);
        });

        binding.bigTitle.setText(giveaway.getTitle());
        binding.minorTitle.setText(giveaway.getTitle());
        binding.categoryTextView.setText(giveaway.getCategory());
        binding.productionYearTextView.setText(giveaway.getYearOfRelease());
        binding.companyNameTextView.setText(giveaway.getDeveloperCompany());
        binding.genresTextView.setText(giveaway.getGenres());

        if (giveaway.getStatus() == GiveawayStatus.FINISHED_YOU_NOT_WIN) {
            binding.giveawayEndDateTextView.setText("Розіграш завершено");
            binding.dateTimeBarFinishedNotWin.setVisibility(View.VISIBLE);
            binding.timeLeftConstraintLayout.setVisibility(View.VISIBLE);
            binding.daysTextView.setText("00");
            binding.hoursTextView.setText("00");
            binding.minutesTextView.setText("00");
            binding.secondsTextView.setText("00");
            binding.timeLeftTextView.setVisibility(View.VISIBLE);
            binding.anotherDayTextView.setVisibility(View.VISIBLE);
            binding.winnerTextView.setVisibility(View.VISIBLE);
            binding.winnerImageView.setVisibility(View.VISIBLE);
            binding.winnerNameTextView.setVisibility(View.VISIBLE);

        }

        else if (giveaway.getStatus() == GiveawayStatus.FINISHED_YOU_WIN) {
            binding.giveawayEndDateTextView.setText("Розіграш завершено");
            binding.dateTimeBarFinished.setVisibility(View.VISIBLE);
            binding.winningBanner.setVisibility(View.VISIBLE);
            binding.winningText.setVisibility(View.VISIBLE);
            binding.winButton.setVisibility(View.VISIBLE);
        }

        else if (giveaway.getStatus() == GiveawayStatus.NOT_FINISHED_YOU_SUBSCRIBED) {
            binding.giveawayEndDateTextView.setText("Розіграш до 25.05.2023");
            binding.dateTimeBar.setVisibility(View.VISIBLE);
            binding.timeLeftConstraintLayout.setVisibility(View.VISIBLE);
            binding.timeLeftTextView.setVisibility(View.VISIBLE);
            binding.conditionForParticipationTextView.setVisibility(View.VISIBLE);
            binding.buttonSubscribe.setVisibility(View.VISIBLE);
            binding.buttonTakePart.setVisibility(View.VISIBLE);
            binding.buttonSubscribe.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.subscribed_button));
            binding.buttonTakePart.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.subscribed_button));

        }

        else if (giveaway.getStatus() == GiveawayStatus.NOT_FINISHED_YOU_NOT_SUBSCRIBED) {
            binding.giveawayEndDateTextView.setText("Розіграш до 25.05.2023");
            binding.dateTimeBar.setVisibility(View.VISIBLE);
            binding.timeLeftConstraintLayout.setVisibility(View.VISIBLE);
            binding.timeLeftTextView.setVisibility(View.VISIBLE);
            binding.conditionForParticipationTextView.setVisibility(View.VISIBLE);
            binding.buttonSubscribe.setVisibility(View.VISIBLE);
            binding.buttonTakePart.setVisibility(View.VISIBLE);
        }

        root = binding.getRoot();

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public static void setGiveawayId(GiveawaysItem giveaway) {
        GiveawaysItemFragment.giveaway = giveaway;
    }

}
