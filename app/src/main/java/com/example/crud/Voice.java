package com.example.crud;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class Voice extends AppCompatActivity {


  static  TextView txvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);
        txvResult = (TextView) findViewById(R.id.textView);

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        } else {
            Toast.makeText(this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
        }
    }

    public void getSpeechInput(View view) {




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txvResult.setText(result.get(0));
                    if(txvResult.getText().toString().equalsIgnoreCase("Log in")||txvResult.getText().toString().equalsIgnoreCase("Sign in")||
                            txvResult.getText().toString().equalsIgnoreCase("Login")||txvResult.getText().toString().equalsIgnoreCase("SignIn")){
                        Intent intent = new Intent(this, SignIn.class);



                        startActivity(intent);
                    }
                    if(txvResult.getText().toString().equalsIgnoreCase("Register")||txvResult.getText().toString().equalsIgnoreCase("Sign up")||
                            txvResult.getText().toString().equalsIgnoreCase("Create Account")||txvResult.getText().toString().equalsIgnoreCase("SignUp")||txvResult.getText().toString().equalsIgnoreCase("Add User")){
                        Intent intent = new Intent(this, Register.class);



                        startActivity(intent);
                    }
                }
                break;
        }

        Intent intent = new Intent();
        intent.putExtra("message", txvResult.getText());
        setResult(RESULT_OK, intent);
        finish();

    }
}
