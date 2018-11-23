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
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
<<<<<<< HEAD
    private List<ListPost> arrayList;

    public RecyclerViewAdapter(List<ListPost> arrayList) {
=======
    private ArrayList<ItemsForCardView> arrayList;
    private Item item;

    public RecyclerViewAdapter(ArrayList<ItemsForCardView> arrayList, Item item) {
>>>>>>> dae26ba4f0b9f86f487326e6c20b92982c8d7417
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
        final ListPost listPost=arrayList.get(i);
        CardView cardView = viewHolder.cardView;
        TextView textView = cardView.findViewById(R.id.textView2);
        TextView textView2 = cardView.findViewById(R.id.textView3);
        TextView textView3 = cardView.findViewById(R.id.textView4);
        TextView textView4 = cardView.findViewById(R.id.textView5);
<<<<<<< HEAD
        TextView textView5 = cardView.findViewById(R.id.textView6);

        textView.setText(listPost.day);
        textView2.setText(listPost.buyORsell);
        textView3.setText(listPost.finish_price);
        textView4.setText(listPost.number_material);
        textView5.setText(listPost.price_once);

=======


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
>>>>>>> dae26ba4f0b9f86f487326e6c20b92982c8d7417
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
