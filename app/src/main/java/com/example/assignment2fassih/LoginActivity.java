package com.example.assignment2fassih;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static final String MY_PREFS = "myPrefs";
    public static final String USER = "myUser";
    public static final String PASS = "myPass";
    private EditText mUsername, mPassword;
    private Button mButton;
    private TextView mRegister;
    private Spinner mChoiceUser;
    private String users[] = {"User", "Doctor", "Guest"};
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

        mUsername = (EditText) findViewById(R.id.editText);
        mPassword = (EditText) findViewById(R.id.editText2);
        mButton = (Button) findViewById(R.id.button);
        mRegister = (TextView) findViewById(R.id.lnkRegister);
        mChoiceUser = (Spinner) findViewById(R.id.choiceSpinner);

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
                openActivity2();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO - Custom Code
    }

    public void openActivity2(){

        if(mUsername.getText().toString().equals(masterEmail) && mPassword.getText().toString().equals(masterPassword)){
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
        else if(mUsername.getText().toString().equals("fassih") && mPassword.getText().toString().equals("123")){
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
    }

}

