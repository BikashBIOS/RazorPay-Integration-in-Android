package com.example.razor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {

    private Button pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Checkout.preload(getApplicationContext());

        pay=findViewById(R.id.payb);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpPayment();
            }
        });
    }

    private void setUpPayment() {
        final Activity activity=this;
        final Checkout checkout=new Checkout();

        JSONObject object=new JSONObject();
        try {
            object.put("name", "Bikash");
            object.put("description", "Bikash des");
            object.put("currency", "INR");
            object.put("amount", "100");

            JSONObject preFill=new JSONObject();
            preFill.put("email", "bikashojha101@gmail.com");
            preFill.put("contact", "8260752787");

            checkout.open(activity, object);
        }
        catch (JSONException e){
            Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String razorpayId) {
        Toast.makeText(this, "Payment Successful"+razorpayId, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivity.this, MainActivity2.class));
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment Failed"+s, Toast.LENGTH_SHORT).show();

    }
}