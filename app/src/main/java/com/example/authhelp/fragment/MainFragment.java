package com.example.authhelp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.authhelp.R;

public class MainFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ConstraintLayout constraintLayout = (ConstraintLayout) inflater.inflate(R.layout.fragment_main, container, false);
        BottomNavigationView bottomNavigationView = constraintLayout.findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.posts:
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container2, FragmentPost.newInstance())
                                .commit();
                        return true;
                    case R.id.profile:
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container2, FragmentProfile.newInstance())
                                .commit();
                        return true;
                }
                return true
            }
        });
        return constraintLayout;
    }

    public static Fragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;

    }}
