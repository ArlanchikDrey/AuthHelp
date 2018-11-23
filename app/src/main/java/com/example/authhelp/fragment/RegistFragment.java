package com.example.authhelp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.authhelp.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

public class RegistFragment extends Fragment {

    Spinner spinner;
    EditText editText;
    Button button;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_regist,container,false);
        spinner=view.findViewById(R.id.spinner_who);
        editText=view.findViewById(R.id.name_company_regist);
        button=view.findViewById(R.id.regist_next);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selected = spinner.getSelectedItem().toString(); //кем является
                String who=editText.getText().toString();// название фирмы
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, MainFragment.newInstance())
                        .commit();
            }
        });

        return view;
    }



    public static Fragment newInstance(){
        RegistFragment fragment=new RegistFragment();
        return  fragment;
    }
}
