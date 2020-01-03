package com.kiran.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class detailhostelActivity extends AppCompatActivity {
String id;
ImageButton map;
databaseHelper databaseHelper;
TextView hostename,ownername,address,contact,room,fees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_detailhostel);
        id=getIntent ().getStringExtra ("id");
        databaseHelper=new databaseHelper (this);
        hostename=findViewById (R.id.namehostel);
        ownername=findViewById (R.id.nameOwner);
        address=findViewById (R.id.nameaddress);
        contact=findViewById (R.id.namecontact);
        room=findViewById (R.id.nameroom);
        fees=findViewById (R.id.namefees);
        DisplayDetail ();

    }

    public  void DisplayDetail(){
        ownerinfo info=databaseHelper.gethostelInfo (id);
        hostename.setText (info.hostelname);
        ownername.setText (info.ownername);
        address.setText (info.address);
        contact.setText (info.contact);
        room.setText (info.room);
        fees.setText (info.fees);

    }
}
