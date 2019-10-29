package com.example.assignment2fassih;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    private Button mButton2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mButton2 = (Button) findViewById(R.id.recordECGSignal);

        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity();
            }
        });
    }

    public void openActivity(){
        SharedPreferences sharedPrefs =getSharedPreferences(LoginActivity.MY_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.clear();
        editor.commit();
        Intent intent = new Intent(this, RecordECGActivity.class);
        startActivity(intent);
    }
}
