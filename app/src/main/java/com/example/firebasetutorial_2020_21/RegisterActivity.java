package com.example.firebasetutorial_2020_21;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextUserName, editTextPassword, editTextEmail;
    private Button buttonSend;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private Users users;

    private long maxid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();
                String email = editTextEmail.getText().toString();

                users = new Users(userName, password, email);

                databaseReference.child(String.valueOf(maxid+1)).setValue(users);


                Toast.makeText(RegisterActivity.this, "Sikeres adat felvétel", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void init() {

        editTextUserName = findViewById(R.id.editRegisztracioUserName);
        editTextEmail = findViewById(R.id.editRegisztracioEmail);
        editTextPassword = findViewById(R.id.editRegisztracioPassword);
        buttonSend = findViewById(R.id.buttonAdatbevitel);

        firebaseDatabase = FirebaseDatabase.getInstance();              //adatbázis inicializálása
        databaseReference = firebaseDatabase.getReference("Users");     //táblázat megadás

        users = new Users();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                    maxid = snapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}