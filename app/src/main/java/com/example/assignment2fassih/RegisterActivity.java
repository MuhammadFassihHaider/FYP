package com.example.assignment2fassih;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private String masterEmail, masterPassword;
    private EditText mEmail, mPassword, mFullName;
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

    private void setupUI() {
        mEmail = findViewById(R.id.txtEmail);
        mPassword = findViewById(R.id.txtPwd);
        mRegisteration = findViewById(R.id.btnLogin);
        mLoginLink = findViewById(R.id.lnkLogin);
        mFullName = findViewById(R.id.txtName);
    }

    private void dataValidation() {
        if (isEmpty(mEmail)) {
            Toast t = Toast.makeText(this, "You must enter an email to register!", Toast.LENGTH_SHORT);
            t.show();
            mEmail.setError("Email is required!");
        } else if (isEmail(mEmail) == false) {
            Toast t = Toast.makeText(this, "Enter a valid email to register!", Toast.LENGTH_SHORT);
            t.show();
            mEmail.setError("Valid email is required!");
        } else if (isEmpty(mFullName)) {
            Toast t = Toast.makeText(this, "You must enter your name to register!", Toast.LENGTH_SHORT);
            t.show();
            mFullName.setError("Name is required!");
        } else if (isEmpty(mPassword)) {
            Toast t = Toast.makeText(this, "You must enter a valid password to register!", Toast.LENGTH_SHORT);
            t.show();
            mPassword.setError("Password is required!");
        } else {
            Intent intent2 = new Intent(getApplicationContext(), LoginActivity.class);
            intent2.putExtra("email", masterEmail);
            intent2.putExtra("password", masterPassword);
            startActivity(intent2);
        }
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

}

