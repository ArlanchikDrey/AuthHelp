package com.example.authhelp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

import com.example.authhelp.Item;
import com.example.authhelp.ItemsForCardView;
import com.example.authhelp.MainActivity;
import com.example.authhelp.R;
import com.example.authhelp.recyclerView.ListPost;
import com.example.authhelp.recyclerView.RecyclerViewAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentState extends Fragment {
    private List<ListPost> arrayList;
    List<String> comIds = new ArrayList<>();
    private String key;
    private RecyclerView recyclerView;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference(); //Ссылка на БД
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //Ссылка на текущего пользователя (чтобы получить ID ользователя)


    private String company_name;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
<<<<<<< HEAD
        recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_state, container, false);
=======

        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_state, container, false);
>>>>>>> dae26ba4f0b9f86f487326e6c20b92982c8d7417
        arrayList = new ArrayList<>();


        Item item = new Item() {
            @Override
            public void onItemClick() {
                ((MainActivity)getActivity()).showDialog();
            }
        };

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(arrayList, item);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mDatabase.child("Users").child(user.getUid()).child("company")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        company_name=dataSnapshot.getValue(String.class);
                        mDatabase.child("Company").child(company_name).child("Post").addValueEventListener(new ValueEventListener() {
                            ListPost postkiki;
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                arrayList.clear();
                                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                                    postkiki= postSnapshot.getValue(ListPost.class);
                                    arrayList.add(postkiki);

                                }

                                recyclerView.getAdapter().notifyDataSetChanged();
                                recyclerView.scrollToPosition(arrayList.size()-1);


                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });




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
