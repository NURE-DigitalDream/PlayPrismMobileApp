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
import com.example.playprism.ui.giveaways.GiveawayStatus;
import com.example.playprism.ui.giveaways.GiveawaysItemFragment;
import com.google.android.material.imageview.ShapeableImageView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GiveawaysAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<GiveawaysItem> giveawaysItems;

    public GiveawaysAdapter(Context context, List<GiveawaysItem> giveawaysItems) {
        this.context = context;
        this.giveawaysItems = giveawaysItems;
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

        GiveawaysItem giveawaysItem = giveawaysItems.get(position);

        String title = giveawaysItem.getTitle();
        String category = giveawaysItem.getCategory();

        GiveawayStatus status = giveawaysItem.getStatus();

        Date endDate = giveawaysItem.getEndDate();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String endDateString = dateFormat.format(endDate);

        Button button = giveawayItemViewHolder.takePartButton;
        if (status == GiveawayStatus.FINISHED_YOU_NOT_WIN) {
            button.setBackground(ContextCompat.getDrawable(context, R.drawable.giveaway_finished_button));
            button.setText("Розіграш завершено");
        } else if (status == GiveawayStatus.FINISHED_YOU_WIN) {
            button.setBackground(ContextCompat.getDrawable(context, R.drawable.win_button));
            button.setText("Перемога! Забрати приз");
        } else if (status == GiveawayStatus.NOT_FINISHED_YOU_SUBSCRIBED) {
            button.setBackground(ContextCompat.getDrawable(context, R.drawable.participating_button));
            button.setText("Беру участь " + endDateString);
        } else if (status == GiveawayStatus.NOT_FINISHED_YOU_NOT_SUBSCRIBED) {
            button.setBackground(ContextCompat.getDrawable(context, R.drawable.participate_button));
            button.setText("Брати участь " + endDateString);
        }

        giveawayItemViewHolder.takePartButton.setOnClickListener(v -> {
                    NavController navController = Navigation.findNavController(v);
                    GiveawaysItemFragment.setGiveawayId(giveawaysItems.get(position));
                    navController.navigate(R.id.navigation_giveaways_item_profile);
                }
        );

        giveawayItemViewHolder.titleTextView.setText(title);
        giveawayItemViewHolder.categoryTextView.setText(category);

        giveawayItemViewHolder.imageView.setImageDrawable(giveawaysItem.getImage());
    }

    @Override
    public int getItemCount() {
        return giveawaysItems.size();
    }

    // add item to list:
    public void addItem(GiveawaysItem giveawaysItem) {
        giveawaysItems.add(giveawaysItem);
        notifyItemInserted(giveawaysItems.size() - 1);
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


