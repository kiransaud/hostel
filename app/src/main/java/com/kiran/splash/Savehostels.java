package com.kiran.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Savehostels extends AppCompatActivity {
databaseHelper databaseHelper;
EditText hostelname,ownername,address,hostelcontact,fees;
Button save,cancel;
RadioGroup hroom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savehostels);
        databaseHelper=new databaseHelper (this);
        hostelname=findViewById (R.id.hname);
        hostelcontact=findViewById (R.id.hostelcontact);
        ownername=findViewById (R.id.hostel_Owner_name);
        address=findViewById (R.id.haddress);
        fees=findViewById (R.id.hostelfees);
        hroom=findViewById (R.id.rooms_available);
        databaseHelper=new databaseHelper (this);
save=findViewById (R.id.save);
cancel=findViewById (R.id.cancel_save);
cancel.setOnClickListener (new View.OnClickListener () {
    @Override
    public void onClick(View view) {
        startActivity (new Intent (Savehostels.this,hostelStore.class));
    }
});
save.setOnClickListener (new View.OnClickListener () {
    @Override
    public void onClick(View view) {
        RadioButton roombutton=findViewById (hroom.getCheckedRadioButtonId ());
        String hostelnamevalue=hostelname.getText ().toString ();
        String ownernamevalue=ownername.getText ().toString ();
        String addressvalue=address.getText ().toString ();
        String contact=hostelcontact.getText ().toString ();
        String feesvalue=fees.getText ().toString ();
        String room=roombutton.getText ().toString ();
        ContentValues contentValues=new ContentValues ();
        contentValues.put ("hostelname",hostelnamevalue);
        contentValues.put ("ownername",ownernamevalue);
        contentValues.put ("address",addressvalue);
        contentValues.put ("contact",contact);
        contentValues.put ("fees",feesvalue);
        contentValues.put ("room",room);
        databaseHelper.insertUser (contentValues);
        Toast.makeText (Savehostels.this,"hostel detail saved",Toast.LENGTH_SHORT).show ();
        startActivity (new Intent (Savehostels.this,splash.class));


    }
});

    }
}
