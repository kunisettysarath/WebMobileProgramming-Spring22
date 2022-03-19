package com.example.myloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;

public class welcomepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomepage);


        MaterialButton logoutBtn= (MaterialButton)findViewById(R.id.logoutbtn);

        logoutBtn.setOnClickListener(view ->  { //It goes from second page to the login page
    Intent redirectTOHome = new Intent(welcomepage.this, MainActivity.class);
    startActivity(redirectTOHome);
});
}
}

