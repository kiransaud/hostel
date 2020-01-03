package com.kiran.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class hostelStore extends AppCompatActivity{

    databaseHelper databaseHelper;
    EditText username, password;
    CheckBox rememberme;
    Button login, signupButton;
    TextView forgotpassword;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostel_store);

databaseHelper=new databaseHelper (this);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.loginButton);
        rememberme = findViewById(R.id.remember_me);
        forgotpassword = findViewById(R.id.forgotpassword);

        login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernamevalue = username.getText().toString();
                String passwordvalue = password.getText().toString();
                if (databaseHelper.isLoginvalid (usernamevalue, passwordvalue)) {
                    startActivity(new Intent(hostelStore.this,Savehostels.class));
                    finish();
                } else {
                    Toast.makeText(hostelStore.this,
                            "Login failure Username:" + usernamevalue,
                            Toast.LENGTH_SHORT).show();

                }
            }
        });
        signupButton = findViewById(R.id.signupButton);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(hostelStore.this, Owner_Register.class);
                startActivity(intent);
            }
        });



    }

}