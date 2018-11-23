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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegistFragment extends Fragment {

    private Spinner spinner;
    private EditText editText;
    private Button button;
    private FirebaseUser user;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference(); //Ссылка на БД
    private FirebaseAuth auth;

    private String key_company;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_regist, container, false);
        spinner = view.findViewById(R.id.spinner_who);
        editText = view.findViewById(R.id.name_company_regist);
        button = view.findViewById(R.id.regist_next);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selected = spinner.getSelectedItem().toString(); //кем является

                if(selected.equals("Владелец")){
                    String who=editText.getText().toString();// название фирмы
                    mDatabase.child("Company").child(who).child("Владелец").setValue(user.getUid());
                    mDatabase.child("Users").child(user.getUid()).child("company").setValue(who);
                }else if(selected.equals("Сотрудник")){
                    final String who2=editText.getText().toString();// название фирмы
                    mDatabase.child("Users").child(user.getUid()).child("company").setValue(who2);
                    mDatabase.child("Company").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.hasChild(who2)){
                                mDatabase.child("Company").child(who2).child("Сотрудники")
                                        .child(user.getUid()).setValue(user.getDisplayName());
                            }else{
                                Toast.makeText(getActivity(), "Нет", Toast.LENGTH_SHORT).show();
                            }
                        }


                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }


                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, MainFragment.newInstance())
                        .commit();
            }
        });

        return view;
    }


    public static Fragment newInstance() {
        RegistFragment fragment = new RegistFragment();
        return fragment;
    }
}
