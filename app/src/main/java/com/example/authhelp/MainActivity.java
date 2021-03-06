package com.example.authhelp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.authhelp.fragment.DialogFragment;
import com.example.authhelp.fragment.LoginFragment;
import com.example.authhelp.fragment.MainFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.container, LoginFragment.newInstance())
                    .commit();
       }else{
           getSupportFragmentManager().beginTransaction().replace(R.id.container, MainFragment.newInstance())
                  .commit();
        }
//        getSupportFragmentManager().beginTransaction().replace(R.id.container, MainFragment.newInstance())
//                    .commit();
        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.add:
                showDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showDialog() {
        DialogFragment dialogFragment = new DialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "tag");
    }
}
