package com.example.crud;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.crud.R;

import static com.example.crud.Welcome.username;

public class LecturerDashBoard extends AppCompatActivity {
    String [] options = {"Submit tutor CV ","Tutor Assignment","Attendance","Help","Session History","About Us"};
    private ListView listView;
    private String id;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_dash_board);

        listView=(ListView)findViewById(R.id.options)
        ;        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                options);
        listView.setAdapter(spinnerArrayAdapter);

 /*       Intent i =new Intent(getApplicationContext(), MyService.class);
        startService(i);*/
        Intent intent = getIntent();
        id   = intent.getStringExtra("id");
      name   = intent.getStringExtra("name");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            if(i==0){

                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto", "17601754@vcconnect.co.za", null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "This is my subject text");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "**Please upload your file** ");
                    startActivity(Intent.createChooser(emailIntent, null));
                }
                if(i==2) {

                    attendance();
                }



                if(i==5){
                    try{  /*Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
                        phoneIntent.setData(Uri.parse("tel:0123456789"));
                        startActivity(phoneIntent);*/

          /*  Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:"+""));
            startActivity(intent);*/

yow();

                    }catch(Exception e){
                        Toast.makeText(LecturerDashBoard.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    private void yow() {

        Intent  g = new Intent(this,Info.class);
        startActivity(g);
    }

    private void attendance() {     Intent phoneIntent = new Intent(this,Attendance.class);
        phoneIntent.putExtra("id",id);
        phoneIntent.putExtra("name",name);

       startActivity(phoneIntent);
    }
}
