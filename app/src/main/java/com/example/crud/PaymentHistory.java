package com.example.crud;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PaymentHistory extends AppCompatActivity {
    String id;
    String [] types={"Java Programming","Information Systems","Life Skills","Math","Physical Sciences","Life Sciences"};
    ArrayList<String> list ;

    private DatabaseReference df;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_history);

        list = new ArrayList<String>();
        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        df = FirebaseDatabase.getInstance().getReference("books");
        for (int x = 0; x < types.length; x++) {
            df = FirebaseDatabase.getInstance().getReference().child("books").child(id).child(types[x]);
            df.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    try {
                        String name = dataSnapshot.child("subject").getValue().toString();
                        String password = dataSnapshot.child("amount").getValue().toString();
                        String date  = dataSnapshot.child("date").getValue().toString();
                        //  Toast.makeText(SessionHistorry.this, name, Toast.LENGTH_SHORT).show();

                        list.add(name +"\nDirect Deposit $ "+password+"\n"+date+"\n_________________________________________________");
                        //  Toast.makeText(SessionHistorry.this, name+" "+"added", Toast.LENGTH_SHORT).show();
                        cool();

                    } catch (Exception a) {
                        // Toast.makeText(SessionHistorry.this, a.toString(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });

        }



    }

    private void cool() {ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        ListView listView = (ListView) findViewById(R.id.payments);
        listView.setAdapter(adapter);
    }

}
