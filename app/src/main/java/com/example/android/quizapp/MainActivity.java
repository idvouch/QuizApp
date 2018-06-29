package com.example.android.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String extra_key = "com.example.quizapp.NAME_INPUT";
    EditText mNameInput;
    Button getStarted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        //retrieving and casting EditText and Button elements from the layout xml using findviewbyid

        mNameInput = (EditText) findViewById(R.id.name_EditView);

        getStarted = (Button) findViewById(R.id.get_started);




//        setting onclicklistener on the button

        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                converting the edit text input into string
                String extra_value = mNameInput.getText().toString();

//                logic to ensure that each user fills in their name/username before they can start quiz
                if (extra_value.length() < 1) {
                    Toast.makeText(getApplicationContext(), "Please fill in your User Name to get Started ", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                    intent.putExtra(extra_key, extra_value);

                    startActivity(intent);
                }


            }
        });


    }



}

