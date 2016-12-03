package com.hermes.gps;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.orm.query.Condition;
import com.orm.query.Select;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import cz.msebera.android.httpclient.Header;

public class CustomSelection extends AppCompatActivity {

    public static final String MY_PREFS_NAME = "MyPrefsFile";
    String serial,username,password;
    Spinner day   ;
    Spinner month ;
    Spinner year  ;
    Spinner h1    ;
    Spinner h2    ;

    String dateFormate;
    String dayString   ;
    String monthString ;
    String yearString  ;
    String h1String    ;
    String h2String    ;

    final List<String> listDay           =  new ArrayList<String>();
    final List<String> listMonth         =  new ArrayList<String>();
    final List<String> listYear          =  new ArrayList<String>();
    final List<String> listH1            =  new ArrayList<String>();
    final List<String> listH2            =  new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        G.overrideFont(getApplicationContext(), "SERIF", "Yekan.ttf");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_selection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences prefs         = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        serial                          = prefs.getString("serial", null);
        username                        = prefs.getString("username", null);
        password                        = prefs.getString("password", null);

        day          = (Spinner)findViewById(R.id.day);
        month        = (Spinner)findViewById(R.id.month);
        year         = (Spinner)findViewById(R.id.year);
        h1           = (Spinner)findViewById(R.id.h1);
        h2           = (Spinner)findViewById(R.id.h2);

        String dateofLast="";
        final List<LastLocationORM> lastLocationORMs = Select.from(LastLocationORM.class)
                .where(Condition.prop("A").eq(serial))
                .orderBy("idapp desc")
                .limit("1")
                .list();
        for(int i = 0; i <lastLocationORMs.size(); i++)
        {
            dateofLast=lastLocationORMs.get(i).getDate();
        }

        StringTokenizer tokens = new StringTokenizer(dateofLast, "/");
        String yearSplite   = tokens.nextToken();
        String monthSplite  = tokens.nextToken();
        String daySplite    = tokens.nextToken();

        listDay.add(daySplite);
        listDay.add(" ");
        listDay.add("1");
        listDay.add("2");
        listDay.add("3");
        listDay.add("4");
        listDay.add("5");
        listDay.add("6");
        listDay.add("7");
        listDay.add("8");
        listDay.add("9");
        listDay.add("10");
        listDay.add("11");
        listDay.add("12");
        listDay.add("13");
        listDay.add("14");
        listDay.add("15");
        listDay.add("16");
        listDay.add("17");
        listDay.add("18");
        listDay.add("19");
        listDay.add("20");
        listDay.add("21");
        listDay.add("22");
        listDay.add("23");
        listDay.add("24");
        listDay.add("25");
        listDay.add("26");
        listDay.add("27");
        listDay.add("28");
        listDay.add("29");
        listDay.add("30");
        listDay.add("31");
        //listDay.remove(daySplite);

        listMonth.add(monthSplite);
        listMonth.add(" ");
        listMonth.add("1");
        listMonth.add("2");
        listMonth.add("3");
        listMonth.add("4");
        listMonth.add("5");
        listMonth.add("6");
        listMonth.add("7");
        listMonth.add("8");
        listMonth.add("9");
        listMonth.add("10");
        listMonth.add("11");
        listMonth.add("12");
        //listMonth.remove(monthSplite);

        listYear.add(yearSplite);
        listYear.add(" ");
        listYear.add("1395");
        listYear.add("1396");
        listYear.add("1397");
        listYear.add("1398");
        listYear.add("1399");
        listYear.add("1400");
        //listYear.remove(yearSplite);

        listH1.add("0");
        listH1.add("1");
        listH1.add("2");
        listH1.add("3");
        listH1.add("4");
        listH1.add("5");
        listH1.add("6");
        listH1.add("7");
        listH1.add("8");
        listH1.add("9");
        listH1.add("10");
        listH1.add("11");
        listH1.add("12");
        listH1.add("13");
        listH1.add("14");
        listH1.add("15");
        listH1.add("16");
        listH1.add("17");
        listH1.add("18");
        listH1.add("19");
        listH1.add("20");
        listH1.add("21");
        listH1.add("22");
        listH1.add("23");

        listH2.add("23");
        listH2.add("22");
        listH2.add("21");
        listH2.add("20");
        listH2.add("19");
        listH2.add("18");
        listH2.add("17");
        listH2.add("16");
        listH2.add("15");
        listH2.add("14");
        listH2.add("13");
        listH2.add("12");
        listH2.add("11");
        listH2.add("10");
        listH2.add("9");
        listH2.add("8");
        listH2.add("7");
        listH2.add("6");
        listH2.add("5");
        listH2.add("4");
        listH2.add("3");
        listH2.add("2");
        listH2.add("1");
        listH2.add("0");

        ArrayAdapter<String> adpapterDay = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listDay);
        adpapterDay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        day.setAdapter(adpapterDay);

        ArrayAdapter<String> adpapterMonth = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listMonth);
        adpapterMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        month.setAdapter(adpapterMonth);

        ArrayAdapter<String> adpapterYear = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listYear);
        adpapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year.setAdapter(adpapterYear);

        ArrayAdapter<String> adpapterH1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listH1);
        adpapterH1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        h1.setAdapter(adpapterH1);

        ArrayAdapter<String> adpapterH2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listH2);
        adpapterH2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        h2.setAdapter(adpapterH2);

        daySelection();
        monthSelection();
        yearSelection();
        h1Selection();
        h2Selection();

        Button submit  = (Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateFormate=yearString+"/"+monthString+"/"+dayString;
                webServiceCustomSelection("http://gpshermes.com/rest/customselection?username="+username+"&password="+password+"&date="+dateFormate+"&h1="+h1String+"&h2="+h2String+"&serial="+serial+"");
            }
        });
    }
    public  void webServiceCustomSelection(String address)
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
                Intent intent = new Intent(CustomSelection.this,MapView.class);
                intent.putExtra("STRING_I_NEED", value.toString());
                startActivity(intent);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Toast.makeText(CustomSelection.this, "خودرو در زمان انتخابی حرکتی نداشته است.", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    }
    private void daySelection()
    {
        day.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Object item = parent.getItemAtPosition(position);
                        dayString=item.toString();
                    }
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
    }
    private void monthSelection()
    {
        month.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Object item = parent.getItemAtPosition(position);
                        monthString=item.toString();
                    }
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
    }
    private void yearSelection()
    {
        year.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Object item = parent.getItemAtPosition(position);
                        yearString=item.toString();
                    }
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
    }
    private void h1Selection()
    {
        h1.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Object item = parent.getItemAtPosition(position);
                        h1String=item.toString();
                    }
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
    }
    private void h2Selection()
    {
        h2.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Object item = parent.getItemAtPosition(position);
                        h2String=item.toString();
                    }
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
    }
}
