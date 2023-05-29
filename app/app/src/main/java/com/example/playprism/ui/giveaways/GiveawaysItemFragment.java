package com.example.playprism.ui.giveaways;

import android.annotation.SuppressLint;

import android.content.Context;

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
import com.example.playprism.bl.models.Product;
import com.example.playprism.bl.responses.GiveawaysResponse;
import com.example.playprism.bl.util.DateConverter;
import com.example.playprism.bl.util.JsonParser;
import com.example.playprism.databinding.FragmentGiveawaysItemProfileBinding;
import com.example.playprism.bl.models.GiveawaysItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class GiveawaysItemFragment extends Fragment {
    private FragmentGiveawaysItemProfileBinding binding;

    private View root;

    private static GiveawaysResponse giveaway;

    private CountDownTimer countDownTimer;

    private long timeLeftInMillis;

    private boolean timerRunning;

    private Date resEndDate;


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

        Context context = this.getContext();

        Product product = giveaway.getProduct();

        List<String> images = new ArrayList<>();
        images.add("giveaways_item_photo_cyberpunk");
        images.add("giveaways_item_photo_resident_evil");
        images.add("giveaways_item_photo_skyrim");
        images.add("giveaways_item_photo_the_witcher");
        Random random = new Random();
        int randomNumber = random.nextInt(4);
        int imageId = context.getResources().getIdentifier(images.get(randomNumber), "drawable", context.getPackageName());

        String productName = product.getName();


        String endDateString = DateConverter.formatDate(giveaway.getExpirationDate());
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            resEndDate = format.parse(endDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String responseYearOfRelease = product.getReleaseDate();
        String yearOfRelease = DateConverter.formatDate(responseYearOfRelease);

        StringBuilder genresString = new StringBuilder();
        List<String> genres = product.getGenres();

        for (int i = 0; i < genres.size(); i++) {
            genresString.append(genres.get(i));
            if (i != genres.size() - 1) {
                genresString.append(", ");
            }
        }

        GiveawayStatus status;

        if (giveaway.getWinner() == null) {
            status = GiveawayStatus.NOT_FINISHED_YOU_NOT_SUBSCRIBED;
        } else {
            if (Objects.equals(giveaway.getWinner().getId(), JsonParser.getUser(context).getUserId())) {
                status = GiveawayStatus.FINISHED_YOU_WIN;
            } else {
                status = GiveawayStatus.FINISHED_YOU_NOT_WIN;
            }
        }

        binding.bigTitle.setText(product.getName());
        binding.cardView.setImageDrawable(ContextCompat.getDrawable(context ,imageId));
        binding.minorTitle.setText(productName);
        binding.categoryTextView.setText("Ключ");
        binding.productionYearTextView.setText(yearOfRelease);
        binding.companyNameTextView.setText(genresString);
        binding.genresTextView.setText(product.getShortDescription());

        if (status == GiveawayStatus.FINISHED_YOU_NOT_WIN) {
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
            binding.winnerNameTextView.setText(giveaway.getWinner().getNickname());
        }

        else if (status == GiveawayStatus.FINISHED_YOU_WIN) {
            binding.giveawayEndDateTextView.setText("Розіграш завершено");
            binding.dateTimeBar.setVisibility(View.VISIBLE);
            binding.dateTimeBar.setProgress(100, true);
            binding.winningBanner.setVisibility(View.VISIBLE);
            binding.winningText.setVisibility(View.VISIBLE);
            binding.winButton.setVisibility(View.VISIBLE);
        }

        else if (status == GiveawayStatus.NOT_FINISHED_YOU_SUBSCRIBED) {
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

            startTimer(resEndDate);
        }

        else if (status == GiveawayStatus.NOT_FINISHED_YOU_NOT_SUBSCRIBED) {
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

            startTimer(resEndDate);
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

    public static void setGiveawayId(GiveawaysResponse giveaway) {
        GiveawaysItemFragment.giveaway = giveaway;
    }

    private void updateCountDownText() {
        long timeLeftInMillis = resEndDate.getTime() - System.currentTimeMillis();

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