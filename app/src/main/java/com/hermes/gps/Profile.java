package com.hermes.gps;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class Profile extends AppCompatActivity {

    String SexString="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        TextView sex          = (TextView)findViewById(R.id.sex);
        TextView company      = (TextView)findViewById(R.id.company);
        TextView nationalCode = (TextView)findViewById(R.id.nationalCode);
        TextView mobile       = (TextView)findViewById(R.id.mobile);
        TextView phone        = (TextView)findViewById(R.id.phone);
        TextView date         = (TextView)findViewById(R.id.date);

        final List<RegisterORM> registerORMs = RegisterORM.listAll(RegisterORM.class);
        for(int i = 0; i <registerORMs.size(); i++)
        {
            if(registerORMs.get(i).getsex().equals("male"))
             SexString="مرد";
                else
                   SexString="زن";
            sex.setText("جنسیت:     "+SexString);
            company.setText("نام شرکت:     "+registerORMs.get(i).getcompany());
            nationalCode.setText("کدملی:     "+registerORMs.get(i).getnational_code());
            mobile.setText("موبایل:     "+registerORMs.get(i).getmobile());
            phone.setText("تلفن ثابت:     "+registerORMs.get(i).gettel());
            date.setText("تاریخ ثبت نام:     "+registerORMs.get(i).getDate());
        }

    }

}
