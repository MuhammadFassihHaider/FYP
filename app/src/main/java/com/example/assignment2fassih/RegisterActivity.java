package com.example.assignment2fassih;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private String masterEmail, masterPassword;
    private EditText mEmail, mPassword;
    private TextView mLoginLink;
    private Button mRegisteration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        setupUI();
        setupListners();
    }

    private void setupListners() {
        mLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        mRegisteration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                masterEmail = mEmail.getText().toString();
                masterPassword = mPassword.getText().toString();
                dataValidation();
            }
        });
    }

    private void setupUI(){
        mEmail = (EditText) findViewById(R.id.txtEmail);
        mPassword = (EditText) findViewById(R.id.txtPwd);
        mRegisteration = (Button) findViewById(R.id.btnLogin);
        mLoginLink = (TextView) findViewById(R.id.lnkLogin);
    }

    private void dataValidation() {
            Intent intent2 = new Intent(getApplicationContext(), LoginActivity.class);
            intent2.putExtra("email", masterEmail);
            intent2.putExtra("password", masterPassword);
            startActivity(intent2);
    }

}

