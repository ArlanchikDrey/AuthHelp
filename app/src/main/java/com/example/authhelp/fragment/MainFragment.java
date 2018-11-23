package com.example.authhelp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.authhelp.R;

public class MainFragment extends Fragment {
    private FragmentProfile fragmentProfile;
    private FragmentState fragmentState;
    private Fragment active;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ConstraintLayout constraintLayout = (ConstraintLayout) inflater.inflate(R.layout.fragment_main, container, false);
        BottomNavigationView bottomNavigationView = constraintLayout.findViewById(R.id.bottomNavigation);
        initFragments();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.posts:
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .hide(active).show(fragmentState).commit();
                        active = fragmentState;
                        return true;
                    case R.id.profile:
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .hide(active).show(fragmentProfile).commit();
                        active = fragmentProfile;
                        return true;
                }
                return true;
            }
        });

        return constraintLayout;
    }

    public static Fragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;

    }

    private void initFragments(){
        fragmentProfile = new FragmentProfile();
        fragmentState = new FragmentState();
        active = fragmentState;
        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.container2, fragmentProfile).commit();
        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.container2, fragmentState).commit();
    }
}
