package com.example.alex_ratkovec_flashcards;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {

    Intent data = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        findViewById(R.id.closeBut) .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = getIntent().getStringExtra("stringKey1"); // this string will be 'harry potter`
                String s2 = getIntent().getStringExtra("stringKey2");
                data.putExtra("new_question",s1);
                data.putExtra("new_answer",s2);
                setResult(RESULT_OK, data);
                finish();
            }
        });

        findViewById(R.id.loadButton2) .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent data = new Intent();

                String user_Entered_Question = ((EditText) findViewById(R.id.addTextQuestion)).getText().toString();
                String user_Entered_Answer = ((EditText) findViewById(R.id.addTextAnswer)).getText().toString();

                data.putExtra("new_question",user_Entered_Question);
                data.putExtra("new_answer",user_Entered_Answer);
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}