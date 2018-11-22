package com.example.authhelp;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class StartActivity extends AppCompatActivity {

    SignInButton btn_sing;
    FirebaseAuth mAuth; //Данные об авторизации
    private FirebaseUser user; //Данные о пользователи
    private DatabaseReference databaseReference ; //Ссылка на БД
    GoogleSignInClient mGoogleClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        btn_sing=findViewById(R.id.btn_sign);

        mAuth=FirebaseAuth.getInstance();
        btn_sing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleClient.getSignInIntent();
                startActivityForResult(signInIntent, 101);
            }
        });

        //Настройки Входа В Аккаунт Google
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleClient= GoogleSignIn.getClient(this,gso);
    }

    //все связанное с гугл
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Результат, возвращенный при запуске Intent из GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 101) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Вход в Google был успешным, аутентификация с Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Ошибка входа в Google

            }}}//конец метода activresult

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) { // Успешный вход, обновление пользовательского
                            //проверка на наличие данных о пользователе в базе
                            user=mAuth.getCurrentUser();
                            final  String user_id= mAuth.getCurrentUser().getUid();
                            databaseReference= FirebaseDatabase.getInstance().getReference("Users");
                            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.hasChild(user_id)){

                                    }else{
                                        String name_google=user.getDisplayName();
                                        Uri ava=user.getPhotoUrl();
                                        Map<String,String> usermap =new HashMap<>();
                                        usermap.put("Avatar", ava.toString());
                                        usermap.put("name", name_google);
                                        usermap.put("family", "");
                                        databaseReference.child(user_id).setValue(usermap);

                                    }

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                            //переходим в главный экран
                            Intent intent=new Intent(StartActivity.this,OsnovnoeActivity.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(getApplicationContext(),
                                    "Успешный вход",Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "Не удалось войти",Toast.LENGTH_SHORT).show();
                        }
                    }});
    }

    //---------------------------------------------------------------------------------
}
