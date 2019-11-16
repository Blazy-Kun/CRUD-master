package com.example.crud;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SignIn extends AppCompatActivity {
    DatabaseReference df;
    EditText n,p;
    Button b;
    String user;
    String id;
    private MediaPlayer mp;
    private Handler h;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        n= (EditText)findViewById(R.id.NAME);
        p= (EditText)findViewById(R.id.pass);
        df = FirebaseDatabase.getInstance().getReference("users");


    }

    private void go() {
        Toast.makeText(this, "kjhasdhg", Toast.LENGTH_SHORT).show();
    }


    public void login(View view) {


        df = FirebaseDatabase.getInstance().getReference().child("users").child(p.getText().toString());
  df.addValueEventListener(new ValueEventListener() {


      @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try{
         name = dataSnapshot.child("username").getValue().toString();
                String password =dataSnapshot.child("password").getValue().toString();
                    Toast.makeText(SignIn.this, name, Toast.LENGTH_SHORT).show();
                    user =dataSnapshot.child("user").getValue().toString();
                id = dataSnapshot.child("id").getValue().toString();
                if(p.getText().toString().equals(password)&&name.equals(n.getText().toString())){
                    Toast.makeText(SignIn.this, "Welcome back"+user, Toast.LENGTH_SHORT).show();
                    if(user.equalsIgnoreCase("Iam Student")){
                       transfer(1);
                    }
                    if(user.equalsIgnoreCase("Iam Lecturer")){
                  
                       transfer(0);
                    }

                         n.setText("");
                        p.setText("");
                        sound();

                }}catch(Exception a){
sound2();

                    transfer(2);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    void transfer(int result){
        if(result==1&& user.equalsIgnoreCase("Iam Student")){
            Intent intent = new Intent(this, StudentDash.class);
           // Toast.makeText(this, n.getText().toString(), Toast.LENGTH_SHORT).show();
            intent.putExtra("Username",n.getText().toString());
            intent.putExtra("id",id);
            intent.putExtra("name",name);
            startActivity(intent);
        }

        if(result==0&& user.equalsIgnoreCase("Iam Lecturer")){
            Intent intent = new Intent(this, LecturerDashBoard.class);
            // Toast.makeText(this, n.getText().toString(), Toast.LENGTH_SHORT).show();
            intent.putExtra("name",n.getText().toString());
            intent.putExtra("id",id);
            startActivity(intent);
        }
        
        if(result==2){
            Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
        }
    }
    public void reg(View view) {
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
    }

    void sound(){
        mp = MediaPlayer.create(this,R.raw.success_lesson);
        mp.start();
    }   void sound2(){
        mp = MediaPlayer.create(this,R.raw.fail);
        mp.start();
    }

    public void speech(View view) {
        Intent intent = new Intent(this,Voice.class);
        startActivity(intent);




    }
}
