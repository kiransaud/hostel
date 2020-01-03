package com.kiran.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class splash extends AppCompatActivity {

    Button registerHostel,searchHostel;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        searchHostel=findViewById(R.id.search);
        registerHostel=findViewById(R.id.register);
        searchHostel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(splash.this,hostellistactivity.class);
                startActivity(intent);
            }
        });
        registerHostel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(splash.this, hostelStore.class);
                startActivity(intent);
            }
        });


    }
}
