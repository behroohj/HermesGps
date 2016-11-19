package com.hermes.gps;

import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.orm.query.Condition;
import com.orm.query.Select;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import cz.msebera.android.httpclient.Header;
import pl.pawelkleczkowski.customgauge.CustomGauge;

public class MainPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {
    DrawerLayout drawer;
    View itm;
    private TextView geoCoding;
    private TextView text1;
    private TextView text2;
    private TextView dateTime;
    private CustomGauge gauge1;
    private CustomGauge gauge2;
    static int topSpeed=0;
    static int lastLocation=0;
    private GoogleMap mMap;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    String serial,username,password,geo,lat,lon;
    TextView name;
    TextView plate;
    private static boolean IsMainpageStart=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        G.overrideFont(getApplicationContext(), "SERIF", "Yekan.ttf");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);
        //SMSUtils.sendSMS(G.context, phoneNumber, message);
        //simcard 2 ham beshe
//        ViewGroup group = (ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content);
//        Font.setAllTextView(group);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        NavigationView navigationV = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);

        SharedPreferences prefs         = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        serial                          = prefs.getString("serial", null);
        username                        = prefs.getString("username", null);
        password                        = prefs.getString("password", null);
        geo                             = prefs.getString("geo", null);

        geoCoding = (TextView)findViewById(R.id.geoCoding);
        gauge2 = (CustomGauge) findViewById(R.id.gauge2);
        gauge2.setValue(120);
        text2  = (TextView) findViewById(R.id.textView2);
        text2.setText(""+120);
        dateTime = (TextView)findViewById(R.id.dateTime);
        gauge1 = (CustomGauge) findViewById(R.id.gauge1);
        text1  = (TextView) findViewById(R.id.textView1);
        name = (TextView)hView.findViewById(R.id.name);
        plate = (TextView)hView.findViewById(R.id.plate);

        UI(serial);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googlemap);
        mapFragment.getMapAsync(MainPage.this);

        if(IsMainpageStart==false) {
            Update(serial);
            IsMainpageStart=true;
        }
//
//        new Thread() {
//            public void run() {
//
//                    try {
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//
//
//                            }
//                        });
//                        Thread.sleep(50);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//            }
//        }.start();
    }
    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if ( keyCode == KeyEvent.KEYCODE_MENU ) {

            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                drawer.openDrawer(GravityCompat.START);
            }
            return true;
        }

        // let the system handle all other key events
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.navProfile) {
            Intent intent = new Intent(MainPage.this,Profile.class);
            startActivity(intent);
        } else if (id == R.id.navTurnOFF) {
            SMSUtils.sendSMS(MainPage.this, "09179168433", "خاموش شد");
        } else if (id == R.id.navCarSelected) {
            Intent intent = new Intent(MainPage.this,CarSelected.class);
            startActivity(intent);
        } else if (id == R.id.navUpdate) {
            Update(serial);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public  void webService(String address)
    {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(address, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                String value = new String(response);
                System.out.println("Profile: "+value);
                JsonParseProfile(value.toString());

            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
            }
            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    }
    public  void webServiceRegister(String address)
    {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(address, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                String value = new String(response);
                System.out.println("Register: "+value);
                JsonParseRegister(value.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
            }
            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    }
    public  void webServiceLastLocation(String address)
    {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(address, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                String value = new String(response);
                System.out.println("LastLocation: "+value);
                JsonParseLastLocation(value.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
            }
            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    }
    public  void webServiceTopSpeed(String address)
    {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(address, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                String value = new String(response);
                System.out.println("TopSpeed: "+value);
                JsonParseTopSpeed(value.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
            }
            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    }
    public  void webServiceGeoCoding(String address)
    {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(address, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                String value = new String(response);

                try {
                    JSONObject json= (JSONObject) new JSONTokener(value.toString()).nextValue();
                    String msg  = (String) json.get("msg");
                    System.out.println("msg"+msg);
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putString("geo", msg);
                    editor.commit();

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
            }
            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    }
    private void JsonParseProfile(String value){


        JSONArray jsonarray = null;
        try {
            jsonarray = new JSONArray(value);
            for(int i=0; i<jsonarray.length(); i++)
            {
                JSONObject issue              = jsonarray.getJSONObject(i);
                int idapp                     = issue.getInt("id");
                String car_name               = issue.getString("car_name");
                String plate                  = issue.getString("plate");
                String owner                  = issue.getString("owner");
                String device_phone_number    = issue.getString("device_phone_number");
                String driver_name            = issue.getString("driver_name");
                String driver_mobile_phone    = issue.getString("driver_mobile_phone");
                String device_type            = issue.getString("device_type");
                String security               = issue.getString("security");
                String device_version         = issue.optString("device_version");
                String setup_time             = issue.optString("setup_time");
                String g_end_time             = issue.optString("g_end_time");
                String note                   = issue.optString("note");
                String serial                 = issue.optString("serial");
                String date                   = issue.optString("date");
                String time                   = issue.optString("time");


                //System.out.println(car_name);
                if((int)ProfileORM.count(ProfileORM.class, "idapp = "+idapp, null) == 0)
                {
                    ProfileORM profileORM=new  ProfileORM();
                    profileORM.setIdapp(idapp);
                    profileORM.setCar_name(car_name);
                    profileORM.setPlate(plate);
                    profileORM.setOwner(owner);
                    profileORM.setDevice_phone_number(device_phone_number);
                    profileORM.setDriver_name(driver_name);
                    profileORM.setDriver_mobile_phone(driver_mobile_phone);
                    profileORM.setDevice_type(device_type);
                    profileORM.setSecurity(security);
                    profileORM.setDevice_version(device_version);
                    profileORM.setSetup_time(setup_time);
                    profileORM.setG_end_time(g_end_time);
                    profileORM.setNote(note);
                    profileORM.setSerial(serial);
                    profileORM.setDate(date);
                    profileORM.setTime(time);
                    profileORM.save();
                }
                else
                {
                    List<ProfileORM> notes = ProfileORM.find(ProfileORM.class, "idapp = "+idapp, null);
                    if (notes.size() > 0) {
                        ProfileORM profileORM = notes.get(0);
                        profileORM.setCar_name(car_name);
                        profileORM.setPlate(plate);
                        profileORM.setOwner(owner);
                        profileORM.setDevice_phone_number(device_phone_number);
                        profileORM.setDriver_name(driver_name);
                        profileORM.setDriver_mobile_phone(driver_mobile_phone);
                        profileORM.setDevice_type(device_type);
                        profileORM.setSecurity(security);
                        profileORM.setDevice_version(device_version);
                        profileORM.setSetup_time(setup_time);
                        profileORM.setG_end_time(g_end_time);
                        profileORM.setNote(note);
                        profileORM.setSerial(serial);
                        profileORM.setDate(date);
                        profileORM.setTime(time);
                        profileORM.save();
                    }
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private void JsonParseRegister(String value){


        JSONArray jsonarray = null;
        try {
            jsonarray = new JSONArray(value);
            for(int i=0; i<jsonarray.length(); i++)
            {
                JSONObject issue              = jsonarray.getJSONObject(i);
                int idapp                     = issue.getInt("id");
                String sex                    = issue.getString("sex");
                String company                = issue.getString("company");
                String national_code          = issue.getString("national_code");
                String mobile                 = issue.getString("mobile");
                String tel                    = issue.getString("tel");
                String address                = issue.getString("address");
                String reserv                 = issue.getString("reserv");
                String access_end_time        = issue.getString("access_end_time");
                String car_serial             = issue.optString("car_serial");
                String note                   = issue.optString("note");
                String date                   = issue.optString("date");
                String time                   = issue.optString("time");

                //System.out.println(sex);
                if((int)RegisterORM.count(RegisterORM.class, "idapp = "+idapp, null) == 0)
                {
                    RegisterORM registerORM=new  RegisterORM();
                    registerORM.setIdapp(idapp);
                    registerORM.setsex(sex);
                    registerORM.setcompany(company);
                    registerORM.setnational_code(national_code);
                    registerORM.setmobile(mobile);
                    registerORM.settel(tel);
                    registerORM.setaddress(address);
                    registerORM.setreserv(reserv);
                    registerORM.setaccess_end_time(access_end_time);
                    registerORM.setcar_serial(car_serial);
                    registerORM.setNote(note);
                    registerORM.setDate(date);
                    registerORM.settel(time);
                    registerORM.save();
                }
                else
                {
                    List<RegisterORM> notes = RegisterORM.find(RegisterORM.class, "idapp = "+idapp, null);
                    if (notes.size() > 0) {
                        RegisterORM registerORM = notes.get(0);
                        registerORM.setsex(sex);
                        registerORM.setcompany(company);
                        registerORM.setnational_code(national_code);
                        registerORM.setmobile(mobile);
                        registerORM.settel(tel);
                        registerORM.setaddress(address);
                        registerORM.setreserv(reserv);
                        registerORM.setaccess_end_time(access_end_time);
                        registerORM.setcar_serial(car_serial);
                        registerORM.setNote(note);
                        registerORM.setDate(date);
                        registerORM.setTime(time);
                        registerORM.save();
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private void JsonParseLastLocation(String value){


        JSONArray jsonarray = null;
        try {
            jsonarray = new JSONArray(value);
            for(int i=0; i<jsonarray.length(); i++)
            {
                JSONObject issue              = jsonarray.getJSONObject(i);
                int idapp                     = issue.getInt("id");
                String A                      = issue.getString("A");
                String B                      = issue.getString("B");
                String C                      = issue.getString("C");
                String D                      = issue.getString("D");
                int E                         = issue.getInt("E");
                String F                      = issue.getString("F");
                String G                      = issue.getString("G");
                String H                      = issue.getString("H");
                String I                      = issue.optString("I");
                String K                      = issue.optString("K");
                String day                    = issue.optString("day");
                String date                   = issue.optString("date");
                String time                   = issue.optString("time");

                //System.out.println(E);
                if((int)LastLocationORM.count(LastLocationORM.class, "idapp = "+idapp, null) == 0)
                {
                    LastLocationORM lastLocationORM=new  LastLocationORM();
                    lastLocationORM.setIdapp(idapp);
                    lastLocationORM.setA(A);
                    lastLocationORM.setB(B);
                    lastLocationORM.setC(C);
                    lastLocationORM.setD(D);
                    lastLocationORM.setE(E);
                    lastLocationORM.setF(F);
                    lastLocationORM.setG(G);
                    lastLocationORM.setH(H);
                    lastLocationORM.setI(I);
                    lastLocationORM.setK(K);
                    lastLocationORM.setday(day);
                    lastLocationORM.setDate(date);
                    lastLocationORM.setTime(time);
                    lastLocationORM.save();
                }
                else
                {
                    List<LastLocationORM> notes = LastLocationORM.find(LastLocationORM.class, "idapp = "+idapp, null);
                    if (notes.size() > 0) {
                        LastLocationORM lastLocationORM = notes.get(0);
                        lastLocationORM.setA(A);
                        lastLocationORM.setB(B);
                        lastLocationORM.setC(C);
                        lastLocationORM.setD(D);
                        lastLocationORM.setE(E);
                        lastLocationORM.setF(F);
                        lastLocationORM.setG(G);
                        lastLocationORM.setH(H);
                        lastLocationORM.setI(I);
                        lastLocationORM.setK(K);
                        lastLocationORM.setday(day);
                        lastLocationORM.setDate(date);
                        lastLocationORM.setTime(time);
                        lastLocationORM.save();
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private void JsonParseTopSpeed(String value){


        JSONArray jsonarray = null;
        try {
            jsonarray = new JSONArray(value);
            for(int i=0; i<jsonarray.length(); i++)
            {
                JSONObject issue              = jsonarray.getJSONObject(i);
                int idapp                     = issue.getInt("id");
                String A                      = issue.getString("A");
                String B                      = issue.getString("B");
                String C                      = issue.getString("C");
                String D                      = issue.getString("D");
                int E                         = issue.getInt("E");
                String F                      = issue.getString("F");
                String G                      = issue.getString("G");
                String H                      = issue.getString("H");
                String I                      = issue.optString("I");
                String K                      = issue.optString("K");
                String day                    = issue.optString("day");
                String date                   = issue.optString("date");
                String time                   = issue.optString("time");

                //System.out.println(E);
                if((int)TopSpeedORM.count(TopSpeedORM.class, "idapp = "+idapp, null) == 0)
                {
                    TopSpeedORM topSpeedORM=new  TopSpeedORM();
                    topSpeedORM.setIdapp(idapp);
                    topSpeedORM.setA(A);
                    topSpeedORM.setB(B);
                    topSpeedORM.setC(C);
                    topSpeedORM.setD(D);
                    topSpeedORM.setE(E);
                    topSpeedORM.setF(F);
                    topSpeedORM.setG(G);
                    topSpeedORM.setH(H);
                    topSpeedORM.setI(I);
                    topSpeedORM.setK(K);
                    topSpeedORM.setday(day);
                    topSpeedORM.setDate(date);
                    topSpeedORM.setTime(time);
                    topSpeedORM.save();
                }
                else
                {
                    List<TopSpeedORM> notes = TopSpeedORM.find(TopSpeedORM.class, "idapp = "+idapp, null);
                    if (notes.size() > 0) {
                        TopSpeedORM topSpeedORM = notes.get(0);
                        topSpeedORM.setA(A);
                        topSpeedORM.setB(B);
                        topSpeedORM.setC(C);
                        topSpeedORM.setD(D);
                        topSpeedORM.setE(E);
                        topSpeedORM.setF(F);
                        topSpeedORM.setG(G);
                        topSpeedORM.setH(H);
                        topSpeedORM.setI(I);
                        topSpeedORM.setK(K);
                        topSpeedORM.setday(day);
                        topSpeedORM.setDate(date);
                        topSpeedORM.setTime(time);
                        topSpeedORM.save();
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Intent intent =new Intent (MainPage.this,MainPage.class);
        startActivity(intent);
        finish();

    }
    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney, Australia, and move the camera.
        final List<LastLocationORM> lastLocationORMs = Select.from(LastLocationORM.class)
                .where(Condition.prop("A").eq(serial))
                .list();
        for(int i = 0; i <lastLocationORMs.size(); i++)
        {
            lastLocation=lastLocationORMs.get(i).getE();
            LatLng sydney = new LatLng(Double.parseDouble(lastLocationORMs.get(i).getB()), Double.parseDouble(lastLocationORMs.get(i).getC()));
            mMap.addMarker(new MarkerOptions().position(sydney).title("نام ماشین"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            mMap.animateCamera( CameraUpdateFactory.zoomTo( 12.0f ) );
        }

    }
    private void UI(String input)
    {
        geoCoding.setText(geo);
        final List<ProfileORM> profileORMs = Select.from(ProfileORM.class)
                .where(Condition.prop("serial").eq(serial))
                .list();
        for(int i = 0; i <profileORMs.size(); i++)
        {
            name.setText(""+profileORMs.get(i).getOwner()+" "+profileORMs.get(i).getCar_name());
            plate.setText(profileORMs.get(i).getDevice_phone_number());
        }

        final List<TopSpeedORM> topSpeedORMs = Select.from(TopSpeedORM.class)
                .where(Condition.prop("A").eq(input))
                .list();
        for(int i = 0; i <topSpeedORMs.size(); i++)
        {
            topSpeed=topSpeedORMs.get(i).getE();
            text1.setText(""+topSpeed);
            gauge1.setValue(topSpeed);
        }

        final List<LastLocationORM> lastLocationORMs = Select.from(LastLocationORM.class)
                .where(Condition.prop("A").eq(input))
                .list();
        for(int i = 0; i <lastLocationORMs.size(); i++)
        {
            lastLocation=lastLocationORMs.get(i).getE();
            lat=lastLocationORMs.get(i).getB();
            lon=lastLocationORMs.get(i).getC();
            text2.setText(""+topSpeed);
            gauge2.setValue(topSpeed);
            dateTime.setText("تاریخ:    "+lastLocationORMs.get(i).getDate()+"     "+"ساعت:    "+lastLocationORMs.get(i).getTime());
        }
    }
    private void Update(String input)
    {
        webServiceLastLocation("http://gpshermes.com/rest/getlast?username="+username+"&password="+password+" ");
        webService("http://gpshermes.com/rest/gettbl?username="+username+"&password="+password+" ");
        webServiceRegister("http://gpshermes.com/rest/getreg?username="+username+"&password="+password+" ");
        webServiceGeoCoding("http://gpshermes.com/rest/getgeo?username="+username+"&password="+password+"&lat="+lat+"&lon="+lon+" ");
        webServiceTopSpeed("http://gpshermes.com/rest/getspeed?username="+username+"&password="+password+" ");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("event", "The onResume() event");
        /// if intenret is not connected! we have start activity and finish
        if(CarSelected.isCarSelected)
        {
        Update(serial);
        CarSelected.isCarSelected=false;
        }

    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("event", "The onPause() event");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("event", "The onStop() event");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("event", "The onDestroy() event");
    }
}

