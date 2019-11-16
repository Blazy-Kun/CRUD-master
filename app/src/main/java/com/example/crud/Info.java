package com.example.crud;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }

    public void createMapIntent(View view){
        Uri uri = Uri.parse("geo:0,0?q=224 2nd Ave, Houghton Estate, Johannesburg, 2198");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW,uri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if(mapIntent.resolveActivity(getPackageManager()) != null){

            startActivity(mapIntent);
        }

    }

    public void createPhoneIntent(View view){

        try{  Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
            phoneIntent.setData(Uri.parse("tel:0123456789"));
            startActivity(phoneIntent);

          /*  Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:"+""));
            startActivity(intent);*/



        }catch(Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }
}
