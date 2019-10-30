package com.example.assignment2fassih;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ForgetPasswordActivity extends AppCompatActivity {
    private Button mForgetButton;
    private EditText mEmail;
    private String masterEmail, masterPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        setupUI();
        setupListeners();
    }

    private void setupListeners() {
        mForgetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                masterEmail = null;
                masterPassword = null;
                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                i.putExtra("email", masterEmail);
                i.putExtra("password", masterPassword);
                startActivity(i);
            }
        });
    }

    private void setupUI() {
        mForgetButton = (Button) findViewById(R.id.btnForget);
        mEmail = (EditText) findViewById(R.id.txtForgetPassword);
        masterEmail = getIntent().getStringExtra("email");
        masterPassword = getIntent().getStringExtra("password");
    }
}