package com.example.assignment2fassih;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static final String MY_PREFS = "myPrefs";
    public static final String USER = "myUser";
    public static final String PASS = "myPass";
    private EditText mEmail, mPassword;
    private Button mButton;
    private TextView mRegister, mForget;
    private Spinner mChoiceUser;
    private String[] users = {"User", "Doctor", "Guest"};
    private String masterPassword, masterEmail;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* SharedPreferences pref = getSharedPreferences(MY_PREFS,MODE_PRIVATE);
        String username = pref.getString(USER, null);
        String password = pref.getString(PASS, null);

        if (username != null || password != null) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }*/

        setupUI();
        setupListeners();
    }

    private void setupUI(){

        mEmail = findViewById(R.id.editText);
        mPassword = findViewById(R.id.editText2);
        mButton = findViewById(R.id.button);
        mRegister = findViewById(R.id.lnkRegister);
        mChoiceUser = findViewById(R.id.choiceSpinner);
        mForget = findViewById(R.id.inkForgetPassword);
        mAuth = FirebaseAuth.getInstance();
        masterEmail = mEmail.getText().toString();
        masterPassword = mPassword.getText().toString();
        FirebaseUser user = mAuth.getCurrentUser();

        /*if(user != null){
            finish();
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        }*/
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mChoiceUser.setAdapter(adapter);
        mChoiceUser.setOnItemSelectedListener(this);
    }

    private void setupListeners(){
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent2);
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataValidation();
            }
        });

        mForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getApplicationContext(), ForgetPasswordActivity.class);
                startActivity(intent3);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {
        // TODO - Custom Code
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO - Custom Code
    }

    public void openHomeActivity() {
         /*else if (mEmail.getText().toString().equals("fassih@gmail.com") && mPassword.getText().toString().equals("123456a!")) {
            Intent intent = new Intent(this, HomeActivity.class);
            Toast toast = Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT);
            toast.show();
            startActivity(intent);
        }*/

    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    private void dataValidation() {
        if (isEmpty(mEmail)) {
            Toast t = Toast.makeText(this, "You must enter a valid email to login!", Toast.LENGTH_SHORT);
            t.show();
            mEmail.setError("Email is required!");
        } else if (isEmail(mEmail) == false) {
            Toast t = Toast.makeText(this, "Enter a valid email to login!", Toast.LENGTH_SHORT);
            t.show();
            mEmail.setError("Valid email is required!");
        } else if (isEmpty(mPassword)) {
            Toast t = Toast.makeText(this, "You must enter your password to login!", Toast.LENGTH_SHORT);
            t.show();
            mPassword.setError("Password is required!");
        } else {
            mAuth.signInWithEmailAndPassword(mEmail.getText().toString(), mPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            finish();
                    }else{
                        Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}

