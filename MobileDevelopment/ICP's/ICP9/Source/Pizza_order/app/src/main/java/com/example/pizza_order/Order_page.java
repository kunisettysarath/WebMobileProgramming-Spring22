package com.example.pizza_order;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//import org.apache.commons.*;

public class Order_page extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    //variables for the price of pizzas
    private static final Integer PIZZA_PRICE = 9;
    private static final Integer CHICKEN_PRICE = 18;
    private static final Integer VEGGIE_PRICE = 13;
    private static final Integer OP_PRICE = 8;
    float totalPrice;
    Integer quantity = 0;
    String orderSummary;
    public String type;
    //variables
    RadioGroup RDG;
    RadioButton RDB;
    EditText userNameText;
    TextView quantityTextView;
    CheckBox chickenChecked, veggieChecked,  opChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);
//asigning values from the order page from respective id's
        quantityTextView = findViewById(R.id.quantity_text_view);
        userNameText = findViewById(R.id.user_input);
        chickenChecked = findViewById(R.id.chic_checkBox);
        veggieChecked = findViewById(R.id.veg_checkBox);
        opChecked = findViewById(R.id.other_checkBox);
        RDG=findViewById(R.id.radiogrp);
//button fucntionalty to the summary of order

        Button detailsBtn = findViewById(R.id.orderinfo);
        detailsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                orderSummary(view);
            }
        });
//button to make the order
        Button placeOrderBtn = findViewById(R.id.placeorder);
        placeOrderBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                orderPizzaMain(view);
            }
        });


    }


    private boolean isUserEmpty(){
        String userName = userNameText.getText().toString();
        if(userName == null || userName.isEmpty()){
            Context context = getApplicationContext();
            String upperLimitToast = getString(R.string.userNull);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return true;
        }
        return false;
    }
//Function to collect the deatils(summary) of order.
    private String fetchDetails() {
        boolean chicken = chickenChecked.isChecked();
        boolean veggie = veggieChecked.isChecked();
       // boolean pepperoni = pepperoniChecked.isChecked();
        boolean other = opChecked.isChecked();
        totalPrice = calculatePrice(chicken, veggie,other, quantity);

        return fetchOrderSummary(userNameText.getText().toString(),RDB.getText().toString(),chicken, veggie, other, totalPrice);
    }
//intent to go to the summary page.
    public void orderSummary(View view) {
        if (!isUserEmpty()) {
            orderSummary = fetchDetails();
            Intent intent = new Intent(Order_page.this, Summary.class);
            intent.putExtra("orderSummary", orderSummary);
            startActivity(intent);
        }    }
    //Function that will send mail notification
    public void orderPizzaMain(View view) {
        if (!isUserEmpty()) {
            orderSummary = fetchDetails();
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("plain/text");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"venulinga63@gmail.com"});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Order Summary");
            emailIntent.putExtra(Intent.EXTRA_TEXT, orderSummary);
            startActivity(Intent.createChooser(emailIntent, ""));
        }
    }

    // onclick veiw to get the radio button value.
    public void onClick(View v) {
        int selectedID = RDG.getCheckedRadioButtonId();
        RDB = findViewById(selectedID);

    }


//funtion to get the order summary in a string format
    private String fetchOrderSummary(String Name, String size,boolean chicken, boolean veggie,
                                      boolean op, float totalPrice) {
        String orderSummaryMessage ="Order By: " + Name + "\n"+ "Size : "+size+"\n"+
                getString(R.string.order_summary_chicken,boolToString(chicken)) + "\n" +
                getString(R.string.order_summary_vegge, boolToString(veggie)) + "\n" +
                getString(R.string.order_summary_Other, boolToString(op)) + "\n" +
                getString(R.string.order_summary_quantity, quantity) + "\n" +
                getString(R.string.order_summary_total_price,totalPrice ) + "\n" +
                getString(R.string.thank_you)+"\n\n"+
                "Note: chicken = $18, veggie = $13, other = $8" ;
        return orderSummaryMessage;
    }
//function to get the R.string values  from the booloen values.
    private String boolToString(boolean bool) {
        return bool ? (getString(R.string.yes)) : (getString(R.string.no));}

//fucntion for the price caluculation.
    private float calculatePrice(boolean chicken, boolean veggie,  boolean other, Integer quantity) {
        int basePrice = PIZZA_PRICE;
        if (chicken) {
            basePrice += CHICKEN_PRICE;
        }
        if (veggie) {
            basePrice += VEGGIE_PRICE;
        }

        if(other){
            basePrice +=OP_PRICE;
        }
        return quantity * basePrice;
    }
//Function to increase the no of pizzas.
    public void increment(View view) {
        if (quantity < 20) {
            quantity = quantity + 1;
            display(quantity);
        } else {
            Log.i("PizzaOrder", "Please select less than 20 Pizzas");
            Context context = getApplicationContext();
            String lowerLimitToast = "Please select less than 20 Pizzas";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, lowerLimitToast, duration);
            toast.show();
            return;
        }    }
//function to decrease the no of pizzas.
    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            display(quantity);
        } else {
            Log.i("pizzaOrder", "Please select atleast one Pizza");
            Context context = getApplicationContext();
            String upperLimitToast = "Please select atleast one Pizza";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return;
        }    }
//function to display the no of pizzas.
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);    }


    @Override


    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}