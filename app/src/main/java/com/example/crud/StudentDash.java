package com.example.crud;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.crud.R;
import com.paypal.android.sdk.df;

public class StudentDash extends AppCompatActivity {
 String [] options = {"Book Session","Send Message","Help","Payment History","About ATEAMS","Call Us","Session History"};
ListView listView;
String id;
    private MediaPlayer mp;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dash);

        listView=(ListView)findViewById(R.id.options)
        ;        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                options);
        listView.setAdapter(spinnerArrayAdapter);


        Intent intent = getIntent();
        id   = intent.getStringExtra("id");
        name   = intent.getStringExtra("name");
        Toast.makeText(this,"id:"+ id, Toast.LENGTH_SHORT).show();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){


                    booksession();
                }
                if(i==1){

                    sendmessage();

                }

                if(i==3){
                    payhistory();


                }
                if(i==6){

                    sessionhistory();

                }


                if(i==5){
                    try{  Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
                        phoneIntent.setData(Uri.parse("tel:0123456789"));
                        startActivity(phoneIntent);

          /*  Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:"+""));
            startActivity(intent);*/



                    }catch(Exception e){
                        Toast.makeText(StudentDash.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    private void sendmessage() {

        mp = MediaPlayer.create(this,R.raw.success_task);
        mp.start();
        Intent intent = new Intent(this,SendMessage.class);
        intent.putExtra("id",id);
        intent.putExtra("name",name);
        startActivity(intent);
    }

    private void payhistory() {
        Intent intent = new Intent(this,PaymentHistory.class
        );
        intent.putExtra("id",id);
        startActivity(intent);


    }

    private void sessionhistory() {
        Intent intent = new Intent(this,SessionHistorry.class);
        intent.putExtra("id",id);
        startActivity(intent);

    }

    private void booksession() {

        Intent intent = new Intent(this,BookSession.class);
        intent.putExtra("id",id);
        intent.putExtra("name",name);
        startActivity(intent);
    }
}
