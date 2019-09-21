package com.example.marketim.View.LoginActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.example.marketim.Presenter.MainPresenter;
import com.example.marketim.R;
import com.example.marketim.View.MainActivity.MainActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity implements ILogin {

    private Button SignIn;
    private EditText userName,password;
    private Switch KeepMeSigned;
    private MainPresenter presenter;
    public final static String SHARED = "SharedPreference";
    SharedPreferences ref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        Init();
        OnClick();
    }



    private void Init() {

        SignIn = findViewById(R.id.logIn);
        userName = findViewById(R.id.UserName);
        password = findViewById(R.id.Password);
        KeepMeSigned = findViewById(R.id.KeepMeSigned);
        presenter = new MainPresenter(this);
        ref = getSharedPreferences(SHARED, MODE_PRIVATE);
        editor = ref.edit();

        final Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(100);
                    Boolean rememberMe = ref.getBoolean("BENİ HATIRLA",false);
                    Intent intent ;
                    if(rememberMe)
                    {
                        intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

    }

    private void OnClick() {
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.LoginAuth(userName.getText().toString(),password.getText().toString());

            }
        });


        KeepMeSigned.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    // do something when check is selected
                    editor.putBoolean("BENİ HATIRLA",true);
                    editor.commit();

            } else {
                    editor.putBoolean("BENİ HATIRLA",false);
                    editor.commit();

                    //do something when unchecked
                }
            }
        });
    }
    @Override
    public void LogInSuccessfully() {
        if (ref.getBoolean("BENİ HATIRLA",true)){
        editor.putString("Kullanıcı Adı",userName.getText().toString());
        editor.putString("Şifre",password.getText().toString());
        editor.commit();}
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void OnFailure(String Error) {
        userName.setError(Error);
        password.setError(Error);
    }

    @Override
    public void onBackPressed() {
        finish();

    }


}
