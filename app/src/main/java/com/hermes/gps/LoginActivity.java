package com.hermes.gps;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

import static android.Manifest.permission.READ_CONTACTS;
/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText email                = (EditText)findViewById(R.id.email);
        final EditText password             = (EditText)findViewById(R.id.password);
        final Button email_sign_in_button   = (Button)findViewById(R.id.email_sign_in_button);
        email_sign_in_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().toString()==null || email.getText().toString().equals("") || password.getText().toString()==null || password.getText().toString().equals(""))
                {
                   //Snack..
                    Snackbar.make(view, "لطفا همه فیلد ها را وارد نمایید", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
                else
                {
                    webServiceLogin("http://gpshermes.com/rest/login?password="+password.getText().toString()+"&username="+email.getText().toString()+" ",email.getText().toString(),password.getText().toString(),"",view);
                }

            }
        });
    }

    public void webServiceLogin(String address,String userName,String password,String deviceID,final View view)
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
                //if is ok login we can save Shareprefrences
                // define a boolian Login
                Snackbar.make(view, "ورود با موفقیت انجام شد", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Intent intent = new Intent(LoginActivity.this,MainPage.class);
                startActivity(intent);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Snackbar.make(view, "ورود با موفقیت انجام نشد", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    }
}

