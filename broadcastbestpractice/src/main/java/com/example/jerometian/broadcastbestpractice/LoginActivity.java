package com.example.jerometian.broadcastbestpractice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.support.v7.widget.AppCompatEditText;
import android.view.Window;

/**
 * Created by jjtian on 2015/10/27.
 */
public class LoginActivity  extends BaseActivity{


    private AppCompatEditText passwordEdit;
    private AppCompatEditText accountEdit;

    private AppCompatButton login;

    private AppCompatCheckBox rememberpassword;

    private SharedPreferences pref;

    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton floatingActionButton = (FloatingActionButton)findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        accountEdit = (AppCompatEditText)findViewById(R.id.account);

        passwordEdit = (AppCompatEditText)findViewById(R.id.password);

        rememberpassword = (AppCompatCheckBox)findViewById(R.id.remember);
        pref =  PreferenceManager.getDefaultSharedPreferences(this);
        login = (AppCompatButton)findViewById(R.id.login);
        boolean isRemember = pref.getBoolean("remember_password",false);
        if (isRemember)
        {
            String account = pref.getString("account", "");
            String password = pref.getString("password","");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberpassword.setChecked(true);

        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        String account = accountEdit.getText().toString();
                String password  = passwordEdit.getText().toString();
                if ( account.equals("admin") && password.equals("123456"))
                {
                    editor = pref.edit();
                    if ( rememberpassword.isChecked())
                    {
                        editor.putBoolean("remember_password",true);
                        editor.putString("account",account);
                        editor.putString("password",password);
                    }else
                    {
                        editor.clear();
                    }
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Snackbar.make(v,"account or password is invalid.",Snackbar.LENGTH_LONG).setAction("action",null).show();
                }
            }
        });


    }
}
