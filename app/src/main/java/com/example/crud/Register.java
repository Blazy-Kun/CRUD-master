package com.example.crud;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {
    EditText n,p;
    Button b;
    DatabaseReference df,df2;
    int status =0;
Spinner spinner;
    String [] types={"Iam Lecturer","Iam Student"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        df = FirebaseDatabase.getInstance().getReference("users");
        df2 = FirebaseDatabase.getInstance().getReference("Settings");

        n= (EditText)findViewById(R.id.NAME);
        p= (EditText)findViewById(R.id.pass);
        b= (Button) findViewById(R.id.v);
        b.setEnabled(false);

      spinner=(Spinner)findViewById(R.id.duple);
        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                types);
        spinner.setAdapter(spinnerArrayAdapter);

    }

    public void Create(View view) {


            try{

                df = FirebaseDatabase.getInstance().getReference().child("users").child(p.getText().toString());
                df.addValueEventListener(new ValueEventListener() {
                   @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        try{
                            String name = dataSnapshot.child("username").getValue().toString();
                            String password =dataSnapshot.child("password").getValue().toString();

                            Toast.makeText(Register.this, name+" "+password, Toast.LENGTH_SHORT).show();

                            if(p.getText().toString().equals(password)&&name.equals(n.getText().toString())){
                                Toast.makeText(Register.this, "Welcome Back\n "+name, Toast.LENGTH_SHORT).show();

                                n.setText("");
                                p.setText("");


                            }}catch(Exception a){

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                /////////////////////////////////////////////
                String id = df.push().getKey();
                //creating an Artist Object
                User user = new User(id,n.getText().toString(),p.getText().toString(),spinner.getSelectedItem().toString());
                //Settings set = new Settings(n.getText().toString(),"walking","Imperial");
                //Saving the Artist
                df.child("").setValue(user);
             //  df2.child(n.getText().toString()).setValue(set);

                //setting edittext to blank again
                //displaying a success toast
                Toast.makeText(this, n.getText().toString()+" Succesfully Created", Toast.LENGTH_LONG).show();
                n.setText("");
                p.setText("");
                Intent intent = new Intent(this,SignIn.class);
                startActivity(intent);

            }
            catch(Exception a){
                Toast.makeText(this, a.toString(), Toast.LENGTH_SHORT).show();
            }
        }


    public void check(View view) {



        df = FirebaseDatabase.getInstance().getReference().child("users").child(p.getText().toString());
        df.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try{
                    String name = dataSnapshot.child("username").getValue().toString();
                    String password =dataSnapshot.child("password").getValue().toString();

                    if(name.equals(n.getText().toString())){
                        Toast.makeText(Register.this, "Try Another Username", Toast.LENGTH_SHORT).show();

                        n.setText("");
                        p.setText("");



                    }else{



                    }
                 }catch(Exception a){
                        if(!n.getText().toString().equals("")||!p.getText().toString().equals("")){
                            b.setEnabled(true);
                        }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
