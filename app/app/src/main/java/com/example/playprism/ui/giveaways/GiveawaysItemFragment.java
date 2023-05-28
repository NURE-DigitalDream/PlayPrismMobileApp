package com.example.playprism.ui.giveaways;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
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

import java.text.SimpleDateFormat;
import java.util.Date;

public class GiveawaysItemFragment extends Fragment {
    private FragmentGiveawaysItemProfileBinding binding;

    private View root;

    private static GiveawaysItem giveaway;

    private CountDownTimer countDownTimer;

    private long timeLeftInMillis;

    private boolean timerRunning;

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
        binding.cardView.setImageDrawable(giveaway.getImage());
        binding.minorTitle.setText(giveaway.getTitle());
        binding.categoryTextView.setText(giveaway.getCategory());
        binding.productionYearTextView.setText(giveaway.getYearOfRelease());
        binding.companyNameTextView.setText(giveaway.getDeveloperCompany());
        binding.genresTextView.setText(giveaway.getGenres());

        if (giveaway.getStatus() == GiveawayStatus.FINISHED_YOU_NOT_WIN) {
            binding.giveawayEndDateTextView.setText("Розіграш завершено");
            binding.dateTimeBar.setVisibility(View.VISIBLE);
            binding.dateTimeBar.setProgress(100, true);
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
            binding.dateTimeBar.setVisibility(View.VISIBLE);
            binding.dateTimeBar.setProgress(100, true);
            binding.winningBanner.setVisibility(View.VISIBLE);
            binding.winningText.setVisibility(View.VISIBLE);
            binding.winButton.setVisibility(View.VISIBLE);
        }

        else if (giveaway.getStatus() == GiveawayStatus.NOT_FINISHED_YOU_SUBSCRIBED) {
            Date endDate = giveaway.getEndDate();
            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            String endDateString = dateFormat.format(endDate);
            binding.giveawayEndDateTextView.setText("Розіграш до " + endDateString);

            long timeLeftInMillis = giveaway.getEndDate().getTime() - System.currentTimeMillis();
            long giveawayDuration = giveaway.getEndDate().getTime() - giveaway.getStartDate().getTime();
            int progress = (int) ((giveawayDuration - timeLeftInMillis) * 100 / giveawayDuration);

            binding.dateTimeBar.setVisibility(View.VISIBLE);
            binding.dateTimeBar.setProgress(progress, true);
            binding.timeLeftConstraintLayout.setVisibility(View.VISIBLE);
            binding.timeLeftTextView.setVisibility(View.VISIBLE);
            binding.conditionForParticipationTextView.setVisibility(View.VISIBLE);
            binding.buttonSubscribe.setVisibility(View.VISIBLE);
            binding.buttonTakePart.setVisibility(View.VISIBLE);

            binding.buttonSubscribe.setOnClickListener(v -> {
                openBrowserLink(giveaway.getTgLink());
            });

            startTimer(giveaway.getEndDate());
        }

        else if (giveaway.getStatus() == GiveawayStatus.NOT_FINISHED_YOU_NOT_SUBSCRIBED) {
            Date endDate = giveaway.getEndDate();
            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            String endDateString = dateFormat.format(endDate);
            binding.giveawayEndDateTextView.setText("Розіграш до " + endDateString);

            long timeLeftInMillis = giveaway.getEndDate().getTime() - System.currentTimeMillis();
            long giveawayDuration = giveaway.getEndDate().getTime() - giveaway.getStartDate().getTime();
            int progress = (int) ((giveawayDuration - timeLeftInMillis) * 100 / giveawayDuration);

            binding.dateTimeBar.setVisibility(View.VISIBLE);
            binding.dateTimeBar.setProgress(progress, true);
            binding.timeLeftConstraintLayout.setVisibility(View.VISIBLE);
            binding.timeLeftTextView.setVisibility(View.VISIBLE);
            binding.conditionForParticipationTextView.setVisibility(View.VISIBLE);
            binding.buttonSubscribe.setVisibility(View.VISIBLE);
            binding.buttonTakePart.setVisibility(View.VISIBLE);

            binding.buttonSubscribe.setOnClickListener(v -> {
                openBrowserLink(giveaway.getTgLink());
            });

            startTimer(giveaway.getEndDate());
        }

        root = binding.getRoot();

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        stopTimer();
        super.onDestroyView();
        binding = null;
    }

    public static void setGiveawayId(GiveawaysItem giveaway) {
        GiveawaysItemFragment.giveaway = giveaway;
    }

    private void updateCountDownText() {
        long timeLeftInMillis = giveaway.getEndDate().getTime() - System.currentTimeMillis();

        int days = (int) (timeLeftInMillis / 1000) / 86400;
        int hours = (int) ((timeLeftInMillis / 1000) % 86400) / 3600;
        int minutes = (int) (((timeLeftInMillis / 1000) % 86400) % 3600) / 60;
        int seconds = (int) (((timeLeftInMillis / 1000) % 86400) % 3600) % 60;

        binding.daysTextView.setText(String.valueOf(days));
        binding.hoursTextView.setText(String.valueOf(hours));
        binding.minutesTextView.setText(String.valueOf(minutes));
        binding.secondsTextView.setText(String.valueOf(seconds));

        long giveawayDuration = giveaway.getEndDate().getTime() - giveaway.getStartDate().getTime();
        int progress = (int) ((giveawayDuration - timeLeftInMillis) * 100 / giveawayDuration);
        binding.dateTimeBar.setProgress(progress, true);
    }

    private void startTimer(Date endDate) {
        timeLeftInMillis = endDate.getTime() - System.currentTimeMillis();
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
            }
        }.start();

        timerRunning = true;
    }

    private void stopTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    public void openBrowserLink(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

}
