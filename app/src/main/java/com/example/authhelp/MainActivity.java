package com.example.authhelp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.authhelp.fragment.LoginFragment;
import com.example.authhelp.fragment.MainFragment;
import com.example.authhelp.fragment.RegistFragment;
import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.container, LoginFragment.newInstance())
                    .commit();
        }else{
            getSupportFragmentManager().beginTransaction().replace(R.id.container, MainFragment.newInstance())
                    .commit();
        }

        }








}
