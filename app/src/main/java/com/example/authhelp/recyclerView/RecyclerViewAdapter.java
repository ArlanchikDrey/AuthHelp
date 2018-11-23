package com.example.authhelp.recyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.authhelp.Item;
import com.example.authhelp.ItemsForCardView;
import com.example.authhelp.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private ArrayList<ItemsForCardView> arrayList;
    private Item item;

    public RecyclerViewAdapter(ArrayList<ItemsForCardView> arrayList, Item item) {
        this.arrayList = arrayList;
        this.item = item;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CardView cardView = (CardView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_view, viewGroup, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        CardView cardView = viewHolder.cardView;
        TextView textView = cardView.findViewById(R.id.textView2);
        TextView textView2 = cardView.findViewById(R.id.textView3);
        TextView textView3 = cardView.findViewById(R.id.textView4);
        TextView textView4 = cardView.findViewById(R.id.textView5);


        textView.setText(arrayList.get(i).getDay());
        textView2.setText(arrayList.get(i).getFirstItem());
        textView3.setText(arrayList.get(i).getSecondItem());
        textView4.setText(arrayList.get(i).getThirdItem());

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.onItemClick();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        public ViewHolder(@NonNull CardView itemView) {
            super(itemView);
            cardView = itemView;
        }
    }
}
