package com.example.authhelp.recyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.authhelp.ItemsForCardView;
import com.example.authhelp.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private List<ListPost> arrayList;

    public RecyclerViewAdapter(List<ListPost> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CardView cardView = (CardView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_view, viewGroup, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final ListPost listPost=arrayList.get(i);
        CardView cardView = viewHolder.cardView;
        TextView textView = cardView.findViewById(R.id.textView2);
        TextView textView2 = cardView.findViewById(R.id.textView3);
        TextView textView3 = cardView.findViewById(R.id.textView4);
        TextView textView4 = cardView.findViewById(R.id.textView5);
        TextView textView5 = cardView.findViewById(R.id.textView6);

        textView.setText(listPost.day);
        textView2.setText(listPost.buyORsell);
        textView3.setText(listPost.finish_price);
        textView4.setText(listPost.number_material);
        textView5.setText(listPost.price_once);

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
