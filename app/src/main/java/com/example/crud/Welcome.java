package com.example.crud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Welcome extends AppCompatActivity {
    public static String []Menu={"Change Settings","Navigation","About","Profile","Logs","Pictures"};
    static String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);



        Intent intent = getIntent();
        username   = intent.getStringExtra("Username");

        TextView text = (TextView)findViewById(R.id.users);
        text.setText(username+" Pick an Activity!");
        ArrayList<String> list = new ArrayList<String>();
        for(int x=0;x<Menu.length;x++){
            list.add(Menu[x]);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        ListView listView = (ListView)findViewById(R.id.list_items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Welcome.this, Menu[i].toString(), Toast.LENGTH_SHORT).show();
                transfer(i,username);

            }
        });
    }
    public void transfer (int list_item,String user){
        if(list_item==0){
           // Intent intent = new Intent(this,Settings_User.class);
           // intent.putExtra("Username",user);


          //  startActivity(intent);
        }
        if(list_item==1){
         //   Intent intent = new Intent(this,BeginJourney.class);
          //  intent.putExtra("Username",user);
//

        //    startActivity(intent);
        }
        if(list_item==2){
           //ntent intent = new Intent(this,Main2Activity.class);
           // intent.putExtra("Username",user);
           // Toast.makeText(this, user, Toast.LENGTH_SHORT).show();


       //     startActivity(intent);
        }



    }

    public void shut(View view) {
        System.exit(0);
    }


    public void logout(View view) {
        System.exit(1);
    }
}
