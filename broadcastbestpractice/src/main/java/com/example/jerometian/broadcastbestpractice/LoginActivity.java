package com.example.jerometian.broadcastbestpractice;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
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

        login = (AppCompatButton)findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        String account = accountEdit.getText().toString();
                String password  = passwordEdit.getText().toString();
                if ( account.equals("admin") && password.equals("123456"))
                {
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
