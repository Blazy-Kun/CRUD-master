package com.example.crud;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.paypal.android.sdk.payments.PayPalConfiguration;

import java.util.Calendar;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;
public class BookSession extends AppCompatActivity {
    String sub;
    String level;
    private static final int CART_LOADER = 0;

    /** Adapter for the ListView */


    private static PayPalConfiguration config = new PayPalConfiguration()

            // Start with mock environment.  When ready, switch to sandbox (ENVIRONMENT_SANDBOX)
            // or live (ENVIRONMENT_PRODUCTION)
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)

            .clientId("AVYjGE0Ej4oFVZEJ2ug-i5cHctls8-Gpj7NL5Hq69LkU7WRkueAC8DnyYOpie5zaaYCUW-mcKmHAd6OC");
Spinner  s1,s2;
EditText editText;
CalendarView calendar ;
    String res="null";
    private DatabaseReference df;
    String [] types={"Java Programming","Information Systems","Life Skills","Math","Physical Sciences","Life Sciences"};
    String [] types2={"Primary Level","High School","Undergraduate"};
    String id;
    private DatabaseReference df2;
String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_session);
        calendar = (CalendarView)findViewById(R.id.cal);
        df = FirebaseDatabase.getInstance().getReference("books");
        df2 = FirebaseDatabase.getInstance().getReference("payments");
editText = (EditText)findViewById(R.id.specify);

       s1=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                types);
        s1.setAdapter(spinnerArrayAdapter);
        Intent intent = getIntent();
     id  = intent.getStringExtra("id");
        name  = intent.getStringExtra("name");


        Toast.makeText(this, "idffff"+id, Toast.LENGTH_SHORT).show();
        s2=(Spinner)findViewById(R.id.grade);
        ArrayAdapter spinnerArrayAdapter2 = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                types2);
        s2.setAdapter(spinnerArrayAdapter2);


        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                // TODO Auto-generated method stub

               res=("Date is : " + dayOfMonth +" / " + (month+1) + " / " + year);
                Toast.makeText(BookSession.this, res, Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void NewSession(View view) {
try{


    sub = s1.getSelectedItem().toString();
 level= s2.getSelectedItem().toString();




        Session session = new Session(id,sub,"25",level,"pending",res);

        df.child(id).child(sub).setValue(session);
        Toast.makeText(this, "Session Booked nt", Toast.LENGTH_SHORT).show();
    pay();


}catch(Exception e){
    Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
}

    }
    void pay(){
        // PAYMENT_INTENT_SALE will cause the payment to complete immediately.
        // Change PAYMENT_INTENT_SALE to
        //   - PAYMENT_INTENT_AUTHORIZE to only authorize payment and capture funds later.
        //   - PAYMENT_INTENT_ORDER to create a payment for authorization and capture
        //     later via calls from your server.

        PayPalPayment payment = new PayPalPayment(new BigDecimal(25), "USD", "Being payment for items ordered" ,
                PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(this, PaymentActivity.class);

        // send the same configuration for restart resiliency
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);

        startActivityForResult(intent, 0);
    }
    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
            if (confirm != null) {
                try {
                    Log.i("paymentExample", confirm.toJSONObject().toString(4));
                    Toast.makeText(this, "Thank you!", Toast.LENGTH_SHORT).show();
                    Payment pay = new Payment("25",sub,id);
                    df2.child(id).child(sub).setValue(pay);
                    finish();
                    // TODO: send 'confirm' to your server for verification.
                    // see https://developer.paypal.com/webapps/developer/docs/integration/mobile/verify-mobile-payment/
                    // for more details.

                } catch (JSONException e) {
                    Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
                }
            }
        }
        else if (resultCode == Activity.RESULT_CANCELED) {
            Log.i("paymentExample", "The user canceled.");
            Toast.makeText(this, "The user canceled.", Toast.LENGTH_SHORT).show();
        }
        else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
            Log.i("paymentExample", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
            Toast.makeText(this, "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.", Toast.LENGTH_SHORT).show();
        }
    }
}
