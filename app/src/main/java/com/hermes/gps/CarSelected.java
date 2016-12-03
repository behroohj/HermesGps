package com.hermes.gps;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CarSelected extends AppCompatActivity {

    public static final String MY_PREFS_NAME = "MyPrefsFile";
    final List<String> list         =  new ArrayList<String>();
    private  boolean isFirst=false;
    Spinner selcetCar;
    TextView deviceVersion;
    TextView deviceType   ;
    TextView driverNumber ;
    TextView driver       ;
    TextView deviceNumber ;
    TextView owner        ;
    TextView plate        ;
    TextView carName      ;
    String serial         ;
    public static boolean isCarSelected=false;
    public static boolean isSerialFill =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_selected);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        selcetCar                       = (Spinner) findViewById(R.id.selcetCar);
        SharedPreferences prefs         = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        serial                          = prefs.getString("serial", null);

         deviceVersion          = (TextView)findViewById(R.id.deviceVersion);
         deviceType             = (TextView)findViewById(R.id.deviceType);
         driverNumber           = (TextView)findViewById(R.id.driverNumber);
         driver                 = (TextView)findViewById(R.id.driver);
         deviceNumber           = (TextView)findViewById(R.id.deviceNumber);
         owner                  = (TextView)findViewById(R.id.owner);
         plate                  = (TextView)findViewById(R.id.plate);
         carName                = (TextView)findViewById(R.id.carName);

            List(serial);
            ArrayAdapter<String> adpapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
            adpapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            selcetCar.setAdapter(adpapter);

            SpiinerSelector(serial);
    }

    private void List(final String input)
    {
        list.add("خودرو انتخاب شده"+"_"+input);
        final List<ProfileORM> profileORMs = ProfileORM.listAll(ProfileORM.class);
        for(int i = 0; i <profileORMs.size(); i++)
        {
            list.add(profileORMs.get(i).getCar_name()+"_"+profileORMs.get(i).getSerial());
        }
    }
    private void SpiinerSelector(final String input)
    {
            selcetCar.setOnItemSelectedListener(
                    new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Object item = parent.getItemAtPosition(position);
                            StringTokenizer tokens = new StringTokenizer(item.toString(), "_");
                            String first = tokens.nextToken();// this will contain "Fruit"
                            String second = tokens.nextToken();// this will contain " they taste good"
                            Snackbar.make(view, "خودرو: " + first + "\nشماره سریال: " + second, Snackbar.LENGTH_LONG).show();
                            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                            editor.putString("serial", second);
                            editor.commit();
                            UI(second);
                            isSerialFill=true;
                        }
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
    }
    private void UI(String input)
    {
        final List<ProfileORM> profileORMs = Select.from(ProfileORM.class)
                .where(Condition.prop("serial").eq(input))
                .list();
        for(int i = 0; i <profileORMs.size(); i++)
        {
            deviceVersion.setText("ورژن دستگاه:     "+profileORMs.get(i).getDevice_version());
            deviceVersion.setVisibility(View.GONE);
            deviceType.setText("نوع دستگاه:     "+profileORMs.get(i).getDevice_type());
            deviceType.setVisibility(View.GONE);
            driverNumber.setText("شماره راننده:     "+profileORMs.get(i).getDevice_version());
            driverNumber.setVisibility(View.GONE);
            driver.setText("نام راننده:     "+profileORMs.get(i).getDriver_name());
            driverNumber.setVisibility(View.GONE);
            deviceNumber.setText("شماره دستگاه:     "+profileORMs.get(i).getDevice_phone_number());
            owner.setText("مالک دستگاه:     "+profileORMs.get(i).getOwner());
            plate.setText("شماره پلاک:     "+profileORMs.get(i).getPlate());
            carName.setText("نام خوردو:     "+profileORMs.get(i).getCar_name());
        }

        deviceVersion.setVisibility(View.GONE);
        driverNumber.setVisibility(View.GONE);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        isCarSelected=true;
    }
}
