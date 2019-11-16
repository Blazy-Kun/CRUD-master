package com.example.crud;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SendMessage extends AppCompatActivity {
EditText editText;
    private DatabaseReference df;
String  message="";
    private String id;
    private String strEditText;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        editText = (EditText)findViewById(R.id.message);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        name   = intent.getStringExtra("name");
        df = FirebaseDatabase.getInstance().getReference("messages");



    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                strEditText = data.getStringExtra("message");
                editText.setText(strEditText);
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        editText.setText(strEditText);
    }

    public void send(View view) {
try{

    String g=  df.push().getKey();
    Message m = new Message(id,g,editText.getText().toString());

    df.child(name).child(g).setValue(m);

    editText.setText(null);
    Snackbar.make(view, "Message Sent", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();

}catch(Exception e){
    Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
}



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }

    public void speech(View view) {



        Intent i = new Intent(this,Voice.class);
        startActivityForResult(i, 1);

    }
}
