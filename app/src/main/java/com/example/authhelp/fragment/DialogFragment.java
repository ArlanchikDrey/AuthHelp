package com.example.authhelp.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.authhelp.R;

public class DialogFragment extends android.support.v4.app.DialogFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getDialog().setTitle("Add action");

        View view = inflater.inflate(R.layout.dialog_send, container, false);
        Spinner spinner = view.findViewById(R.id.spinner);


        final EditText editText = view.findViewById(R.id.editText4);
        final EditText editText2 = view.findViewById(R.id.editText5);

        Button button = view.findViewById(R.id.button7);

        editText.setVisibility(View.INVISIBLE);
        editText2.setVisibility(View.INVISIBLE);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                editText.setVisibility(View.VISIBLE);
                editText2.setVisibility(View.VISIBLE);
                switch (position){
                    case 1:
                        editText.setHint("куплено материала");
                        editText2.setHint("стоимость за штуку");
                        break;
                    case 2:
                        editText.setHint("продано");
                        editText2.setHint("стоимость за штуку");
                        break;
                    case 3:
                        editText.setHint("Потрачено");
                        editText2.setHint("Заработано");
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }

    public static DialogFragment newInstance() {
        DialogFragment fragment = new DialogFragment();
        return fragment;
    }
}
