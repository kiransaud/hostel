package com.kiran.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class hostellistactivity extends AppCompatActivity {
LinearLayout container;
databaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_hostellistactivity);
        container=findViewById (R.id.container);
        databaseHelper=new databaseHelper (this);
        populatehostel ();

    }
    public  void populatehostel() {
        ArrayList<ownerinfo> list = databaseHelper.gethostelList ();
        for (final ownerinfo info:list) {
            View view1= LayoutInflater.from (this).inflate (R.layout.itemlayout,null);
            TextView hostelname=view1.findViewById (R.id.hostelname);
            TextView address=view1.findViewById (R.id.address);
            hostelname.setText (info.hostelname);
            address.setText (info.address);
            view1.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    Intent intent=new  Intent (hostellistactivity.this,detailhostelActivity.class);
                    intent.putExtra ("id",info.id);
                    startActivity (intent);
                }
            });
            container.addView (view1);
        }
    }

}
