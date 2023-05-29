package com.example.playprism.bl.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playprism.R;
import com.example.playprism.bl.models.GiveawaysItem;
import com.example.playprism.bl.models.Product;
import com.example.playprism.bl.models.UserData;
import com.example.playprism.bl.responses.GiveawaysResponse;
import com.example.playprism.bl.util.DateConverter;
import com.example.playprism.bl.util.JsonParser;
import com.example.playprism.ui.giveaways.GiveawayStatus;
import com.example.playprism.ui.giveaways.GiveawaysItemFragment;
import com.google.android.material.imageview.ShapeableImageView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.TimeZone;

public class GiveawaysAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<GiveawaysResponse> giveawaysResponses;

    public GiveawaysAdapter(Context context, List<GiveawaysResponse> giveawaysResponses) {
        this.context = context;
        this.giveawaysResponses = giveawaysResponses;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        viewHolder = getViewHolder(parent, inflater);

        return viewHolder;
    }

    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View viewItem = inflater.inflate(R.layout.giveaways_item, parent, false);
        viewHolder = new GiveawayItemViewHolder(viewItem);
        return viewHolder;
    }

    @SuppressLint({"SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        GiveawayItemViewHolder giveawayItemViewHolder = (GiveawayItemViewHolder) holder;

        GiveawaysResponse giveawaysItem = giveawaysResponses.get(position);

        Product product = giveawaysItem.getProduct();

        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        // String endDateString = dateFormat.format(giveawaysItem.getExpirationDate());

        Button button = giveawayItemViewHolder.takePartButton;

        StringBuilder genresString = new StringBuilder();
        List<String> genres = product.getGenres();

        for (int i = 0; i < genres.size(); i++) {
            genresString.append(genres.get(i));
            if (i != genres.size() - 1) {
                genresString.append(", ");
            }
        }

        String input = giveawaysItem.getExpirationDate();
        String output = DateConverter.formatDate(input);

        GiveawayStatus status;

        if (giveawaysItem.getWinner() == null) {
            status = GiveawayStatus.NOT_FINISHED_YOU_NOT_SUBSCRIBED;
        } else {
            if (Objects.equals(giveawaysItem.getWinner().getId(), JsonParser.getUser(context).getUserId())) {
                status = GiveawayStatus.FINISHED_YOU_WIN;
            } else {
                status = GiveawayStatus.FINISHED_YOU_NOT_WIN;
            }
        }

        if (status == GiveawayStatus.FINISHED_YOU_NOT_WIN) {
            button.setBackground(ContextCompat.getDrawable(context, R.drawable.giveaway_finished_button));
            button.setText("Розіграш завершено");
        } else if (status == GiveawayStatus.FINISHED_YOU_WIN) {
            button.setBackground(ContextCompat.getDrawable(context, R.drawable.win_button));
            button.setText("Перемога! Забрати приз");
        } else if (status == GiveawayStatus.NOT_FINISHED_YOU_SUBSCRIBED) {
            button.setBackground(ContextCompat.getDrawable(context, R.drawable.participating_button));
            button.setText("Беру участь " + output);
        } else if (status == GiveawayStatus.NOT_FINISHED_YOU_NOT_SUBSCRIBED) {
            button.setBackground(ContextCompat.getDrawable(context, R.drawable.participate_button));
            button.setText("Брати участь " + output);
        }

        giveawayItemViewHolder.takePartButton.setOnClickListener(v -> {
                    NavController navController = Navigation.findNavController(v);
                    GiveawaysItemFragment.setGiveawayId(giveawaysResponses.get(position));
                    navController.navigate(R.id.navigation_giveaways_item_profile);
                }
        );

        List<String> images = new ArrayList<>();
        images.add("giveaways_item_photo_cyberpunk");
        images.add("giveaways_item_photo_resident_evil");
        images.add("giveaways_item_photo_skyrim");
        images.add("giveaways_item_photo_the_witcher");
        Random random = new Random();
        int randomNumber = random.nextInt(4);
        int imageId = context.getResources().getIdentifier(images.get(randomNumber), "drawable", context.getPackageName());

        giveawayItemViewHolder.titleTextView.setText(product.getName());
        giveawayItemViewHolder.categoryTextView.setText(genresString.toString());
        giveawayItemViewHolder.imageView.setImageDrawable(ContextCompat.getDrawable(context, imageId));
    }

    @Override
    public int getItemCount() {
        return giveawaysResponses.size();
    }

    public void addItem(GiveawaysResponse giveawaysItem) {
        giveawaysResponses.add(giveawaysItem);
        notifyItemInserted(giveawaysResponses.size() - 1);
    }

    protected static class GiveawayItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleTextView;
        private final TextView categoryTextView;
        private final Button takePartButton;
        private final ShapeableImageView imageView;

        public GiveawayItemViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            categoryTextView = itemView.findViewById(R.id.giveawayCategoryTextView);
            takePartButton = itemView.findViewById(R.id.button_participate);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}


