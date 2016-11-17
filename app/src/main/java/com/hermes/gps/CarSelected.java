package com.hermes.gps;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class CarSelected extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_selected);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        TextView deviceVersion          = (TextView)findViewById(R.id.deviceVersion);
        TextView deviceType             = (TextView)findViewById(R.id.deviceType);
        TextView driverNumber           = (TextView)findViewById(R.id.driverNumber);
        TextView driver                 = (TextView)findViewById(R.id.driver);
        TextView deviceNumber           = (TextView)findViewById(R.id.deviceNumber);
        TextView owner                  = (TextView)findViewById(R.id.owner);
        TextView plate                  = (TextView)findViewById(R.id.plate);
        TextView carName                = (TextView)findViewById(R.id.carName);

        final List<ProfileORM> profileORMs = ProfileORM.listAll(ProfileORM.class);
        for(int i = 0; i <profileORMs.size(); i++)
        {
            deviceVersion.setText("ورژن دستگاه:     "+profileORMs.get(i).getDevice_version());
            deviceType.setText("نوع دستگاه:     "+profileORMs.get(i).getDevice_type());
            driverNumber.setText("شماره راننده:     "+profileORMs.get(i).getDevice_version());
            driver.setText("نام راننده:     "+profileORMs.get(i).getDriver_name());
            deviceNumber.setText("شماره دستگاه:     "+profileORMs.get(i).getDriver_mobile_phone());
            owner.setText("مالک دستگاه:     "+profileORMs.get(i).getOwner());
            plate.setText("شماره پلاک:     "+profileORMs.get(i).getPlate());
            carName.setText("نام خوردو:     "+profileORMs.get(i).getCar_name());
        }

    }

}
