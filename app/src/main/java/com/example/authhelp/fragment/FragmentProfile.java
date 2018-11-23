package com.example.authhelp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.authhelp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 */
public class FragmentProfile extends Fragment {
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference(); //Ссылка на БД
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //Ссылка на текущего пользователя (чтобы получить ID ользователя)

    private String company_name;

    private EditText finish_price,price_once,number_material,buy_sell;
    private Button button;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_profile,container,false);

       button=view.findViewById(R.id.button3);
       finish_price=view.findViewById(R.id.finish_price);
       price_once=view.findViewById(R.id.price_once);
       number_material=view.findViewById(R.id.number_material);
       buy_sell=view.findViewById(R.id.buy_sell);


       mDatabase.child("Users").child(user.getUid()).child("company")
               .addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
              company_name=dataSnapshot.getValue(String.class);
              }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });


       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String key= mDatabase.child("Company").child(company_name).child("Post").push().getKey();


               Calendar calendar=Calendar.getInstance();
               SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM HH:mm:ss");
               String date_time=simpleDateFormat.format(calendar.getTime());
               mDatabase.child("Company").child(company_name).child("Post").child(key).child("day")
                       .setValue(date_time);

               mDatabase.child("Company").child(company_name).child("Post").child(key).child("buyORsell")
                       .setValue(buy_sell.getText().toString());
               mDatabase.child("Company").child(company_name).child("Post").child(key).child("number_material")
                       .setValue(number_material.getText().toString());
               mDatabase.child("Company").child(company_name).child("Post").child(key).child("price_once")
                       .setValue(price_once.getText().toString());

               String s1=number_material.getText().toString();
               String s2=price_once.getText().toString();
               int sum=Integer.parseInt(s1)*Integer.parseInt(s2);
               finish_price.setText(String.valueOf(sum));
               mDatabase.child("Company").child(company_name).child("Post").child(key).child("finish_price")
                       .setValue(String.valueOf(sum));


           }
       });
        return view;
    }

    public static FragmentProfile newInstance() {
        FragmentProfile fragment = new FragmentProfile();
        return fragment;
    }
}
