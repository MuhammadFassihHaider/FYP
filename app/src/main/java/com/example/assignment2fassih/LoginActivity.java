package com.example.assignment2fassih;

import androidx.appcompat.app.AppCompatActivity;

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
        setupListners();
    }

    private void setupUI(){
        masterEmail = getIntent().getStringExtra("email");
        masterPassword = getIntent().getStringExtra("password");

        mEmail = findViewById(R.id.editText);
        mPassword = findViewById(R.id.editText2);
        mButton = findViewById(R.id.button);
        mRegister = findViewById(R.id.lnkRegister);
        mChoiceUser = findViewById(R.id.choiceSpinner);
        mForget = findViewById(R.id.inkForgetPassword);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mChoiceUser.setAdapter(adapter);
        mChoiceUser.setOnItemSelectedListener(this);
    }

    private void setupListners(){
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

        if (mEmail.getText().toString().equals(masterEmail) && mPassword.getText().toString().equals(masterPassword)) {
            Intent intent = new Intent(this, HomeActivity.class);
            Toast toast = Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT);
            toast.show();
            startActivity(intent);

        } else if (mEmail.getText().toString().equals("fassih@gmail.com") && mPassword.getText().toString().equals("123456a!")) {
            Intent intent = new Intent(this, HomeActivity.class);
            Toast toast = Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT);
            toast.show();
            startActivity(intent);
        } else{
            Toast toast = Toast.makeText(this, "Wrong password or email", Toast.LENGTH_SHORT);
            toast.show();
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
            openHomeActivity();
        }
    }

}

