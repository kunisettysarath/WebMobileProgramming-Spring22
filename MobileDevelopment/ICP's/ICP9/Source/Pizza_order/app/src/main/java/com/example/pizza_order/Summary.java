package com.example.pizza_order;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Summary extends AppCompatActivity {

    TextView summary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        summary = findViewById(R.id.summaryText);
        summary.setText(Html.fromHtml("<u>Your Order Summary</u><br/><br/>"));
        if(getIntent() != null){
            summary.append(getIntent().getStringExtra("orderSummary"));
        }
        summary.append(Html.fromHtml("<br/>"));
        summary.setVisibility(View.VISIBLE);



    }

}