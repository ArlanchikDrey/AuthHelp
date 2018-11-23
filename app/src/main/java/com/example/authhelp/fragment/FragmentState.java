package com.example.authhelp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.authhelp.ItemsForCardView;
import com.example.authhelp.R;
import com.example.authhelp.recyclerView.RecyclerViewAdapter;

import java.util.ArrayList;

public class FragmentState extends Fragment {
    private ArrayList<ItemsForCardView> arrayList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_state, container, false);
        arrayList = new ArrayList<>();

        ItemsForCardView itemsForCardView = new ItemsForCardView("today"
        , "buy material: 23", "value for one element", "itigo");
        arrayList.add(itemsForCardView);

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(arrayList);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return recyclerView;
    }

    public static FragmentState newInstance() {

        //Bundle args = new Bundle();
        //args.putString("key", state);
        FragmentState fragment = new FragmentState();
       // fragment.setArguments(args);
        return fragment;
    }
}
