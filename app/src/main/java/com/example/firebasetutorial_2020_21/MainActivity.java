package com.example.firebasetutorial_2020_21;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editUserName, editPassword;
    private Button buttonRegisztracio, buttonBejelentkezes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        buttonRegisztracio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void init() {

        editUserName = findViewById(R.id.editBejelentUserName);
        editPassword = findViewById(R.id.editBejelentPassword);
        buttonBejelentkezes = findViewById(R.id.buttonBejelent);
        buttonRegisztracio = findViewById(R.id.buttonRegisztracio);
    }
}