package com.example.myloginapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

TextView username=(TextView)findViewById(R.id.username);
        TextView password =(TextView)findViewById(R.id.password);

        MaterialButton loginbtn= (MaterialButton)findViewById(R.id.loginbtn);

        //admin63 and admin63



        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!username.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
                    if ((username.getText().toString().equals("admin63")) && (password.getText().toString().equals("admin63"))) {
                        Intent intent = new Intent(MainActivity.this,welcomepage.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "Redirected to welcome", Toast.LENGTH_SHORT).show();
                    } else { //incorrect username/password
                        if (!username.getText().toString().equals("admin63") && !password.getText().toString().equals("admin63")) {
                            Toast.makeText(MainActivity.this, "Incorrect Username & Password", Toast.LENGTH_SHORT).show();
                        } else if (!username.getText().toString().equals("admin63")) {
                            Toast.makeText(MainActivity.this, "Username InCorrect", Toast.LENGTH_SHORT).show();
                        } else if (!password.getText().toString().equals("admin63")) {
                            Toast.makeText(MainActivity.this, "Password InCorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else { //missing details(username/password)
                    Toast.makeText(MainActivity.this, "You need to enter valid username/password", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}