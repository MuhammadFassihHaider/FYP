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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private String masterEmail, masterPassword;
    private EditText mEmail, mPassword, mFullName;
    private TextView mLoginLink;
    private Button mRegisteration;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        setupUI();

        mAuth = FirebaseAuth.getInstance();

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

                if (dataValidation()){
                    masterEmail = mEmail.getText().toString().trim();
                    masterPassword = mPassword.getText().toString().trim();

                    mAuth.createUserWithEmailAndPassword(masterEmail, masterPassword) .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                sendEmailVerification();
                            }else{
                                Toast.makeText(RegisterActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                            }


                        }
                    });


                }

            }
        });
    }

    private void setupUI(){
        mEmail = findViewById(R.id.txtEmail);
        mPassword = findViewById(R.id.txtPwd);
        mRegisteration = findViewById(R.id.btnLogin);
        mLoginLink = findViewById(R.id.lnkLogin);
        mFullName = findViewById(R.id.txtName);
    }

    public static boolean isValidPassword(final String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    private Boolean dataValidation() {
        Boolean result = false;
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
        } else if (mPassword.getText().toString().length() < 8 && !isValidPassword(mPassword.getText().toString())) {
            Toast t = Toast.makeText(this, "You must enter a valid password to register!", Toast.LENGTH_SHORT);
            t.show();
            mPassword.setError("Password must have minimum 8 chars, 1 number, 1 alphabet and one special character");
        }
        else {
            result = true;
            /*Intent intent2 = new Intent(getApplicationContext(), LoginActivity.class);
            intent2.putExtra("email", masterEmail);
            intent2.putExtra("password", masterPassword);
            startActivity(intent2);*/
        }
        return result;
    }

    private void sendEmailVerification(){
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "Successfully Registered, Verification mail sent!", Toast.LENGTH_SHORT).show();
                        mAuth.signOut();
                        finish();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    }else{
                        Toast.makeText(RegisterActivity.this, "Verification mail has not been sent!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}

