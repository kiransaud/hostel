package com.kiran.splash;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaCodec;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Owner_Register extends AppCompatActivity {
    TextView link_to_login;
    EditText username, password, email, address, contact, hostelid;
    Button register, cancel;
    RadioGroup gender;
    databaseHelper databaseHelper;
    SharedPreferences sharedPreferences;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        link_to_login = findViewById(R.id.link_to_login);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner__register);
        sharedPreferences = getSharedPreferences("ownerinfo", 0);
        databaseHelper = new databaseHelper(this);
        Log.i("Lifecycle", "oncreate");

        username = findViewById(R.id.username_reg);
        password = findViewById(R.id.password_reg);
        email = findViewById(R.id.email_reg);
        address = findViewById(R.id.address_reg);
        contact = findViewById(R.id.contact_reg);
        hostelid = findViewById(R.id.hostelpan);
        register = findViewById(R.id.register_reg);
        cancel = findViewById(R.id.cancel_reg);
        link_to_login = findViewById(R.id.link_to_login);
        gender = findViewById(R.id.gender);
        link_to_login.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                startActivity (new Intent (Owner_Register.this,hostelStore.class));
            }
        });
        cancel.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                startActivity (new Intent (Owner_Register.this,splash.class));
            }
        });

        register.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                String usernamevalue = username.getText().toString();
                String passswordvalue = password.getText().toString();
                String emailvalue = email.getText().toString();
                String addressvalue = address.getText().toString();
                String contactvalue = contact.getText().toString();
                String hostelidvalue = hostelid.getText().toString();
                RadioButton checkButton = findViewById(gender.getCheckedRadioButtonId());
                String gendervalue = checkButton.getText().toString();
                ContentValues contentValues=new ContentValues ();
                contentValues.put ("username",usernamevalue);
                contentValues.put ("password",passswordvalue);
                contentValues.put ("email",emailvalue);
                contentValues.put ("address",addressvalue);
                contentValues.put ("contact",contactvalue);
                contentValues.put ("hostelid",hostelidvalue);
                contentValues.put ("gender",gendervalue);
                databaseHelper.insertUser (contentValues);
                if(isFieldEmpty (username)&&isEmailvalid (email)){
                    Toast.makeText (Owner_Register.this,"success",Toast.LENGTH_SHORT);
                }
               if(Checkno (contact.getText ().toString ())){

               }else
                   Toast.makeText (getApplicationContext (),"plese enter number",Toast.LENGTH_SHORT).show ();
            }
        });
    }
    public  Boolean Checkno(String sn){
        Boolean check=false;
        String check_no="\\d*\\.?\\d+";
        CharSequence inputstr=sn;
        Pattern pte= Pattern.compile (check_no,Pattern.CASE_INSENSITIVE);
        Matcher matcher=pte.matcher (inputstr);
        if(matcher.matches ()){
            check =true;
        }
        return  check;
    }
    public  Boolean isFieldEmpty(EditText view){
        String value=view.getText ().toString ();
        if(value.length ()>0){
            return  false;
        }
        else
            view.setError ("enter value");
        return  true;
    }
    public  Boolean isEmailvalid(EditText view) {
        if (!isFieldEmpty (view)) {
            String value = view.getText ().toString ();
            if (Patterns.EMAIL_ADDRESS.matcher (value).matches ()) {
                return true;

            } else
                view.setError ("invalidemail");
            return false;
        }
        return  false;
    }
}
