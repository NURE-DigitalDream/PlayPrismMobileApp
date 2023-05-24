package com.example.playprism.adapters;

import static java.text.DateFormat.getDateInstance;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playprism.R;
import com.example.playprism.models.PurchasedItem;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class HistoryPurchaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<PurchasedItem> purchasedItems;

    public HistoryPurchaseAdapter(Context context, List<PurchasedItem> purchasedItems) {
        this.context = context;
        this.purchasedItems = purchasedItems;
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
        View viewItem = inflater.inflate(R.layout.purchase_history_item, parent, false);
        viewHolder = new HistoryPurchaseItemViewHolder(viewItem);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HistoryPurchaseItemViewHolder historyPurchaseItemViewHolder = (HistoryPurchaseItemViewHolder) holder;

        String title = purchasedItems.get(position).getTitle();
        Date purchaseDate = purchasedItems.get(position).getPurchaseDate();
        float price = purchasedItems.get(position).getPrice();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String purchaseDateStr = dateFormat.format(purchaseDate);

        historyPurchaseItemViewHolder.titleTextView.setText(title);
        historyPurchaseItemViewHolder.purchaseDateTextView.setText(purchaseDateStr);
        historyPurchaseItemViewHolder.priceTextView.setText(price + "₴");
    }

    @Override
    public int getItemCount() {
        return purchasedItems.size();
    }

    // add item to list:
    public void addItem(PurchasedItem purchasedItem) {
        purchasedItems.add(purchasedItem);
        notifyItemInserted(purchasedItems.size() - 1);
    }

    protected static class HistoryPurchaseItemViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView purchaseDateTextView;
        private TextView priceTextView;

        public HistoryPurchaseItemViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            purchaseDateTextView = itemView.findViewById(R.id.purchaseDateTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
        }
    }
}
