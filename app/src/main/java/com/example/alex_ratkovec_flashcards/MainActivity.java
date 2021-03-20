package com.example.alex_ratkovec_flashcards;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int currentCardDisplayedIndex = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();
        TextView q1, a1;

        q1= findViewById(R.id.Question1);
        a1= findViewById(R.id.Answer1);


        findViewById(R.id.changeFlashCardArrow).setOnClickListener(new View.OnClickListener()
        {
            @Override
            // User can tap a button to change the text string of the label - Android is
            public void onClick(View v) {

                // don't try to go to next card if you have no cards to begin with
                if (allFlashcards.size() == 0)
                    return;

                // advance our pointer index so we can show the next card
                currentCardDisplayedIndex++;

                // make sure we don't get an IndexOutOfBoundsError if we are viewing the last indexed card in our list
                if(currentCardDisplayedIndex >= allFlashcards.size()) {
                    currentCardDisplayedIndex = 0;
                }
                // set the question and answer TextViews with data from the database
                allFlashcards = flashcardDatabase.getAllCards();
                Flashcard flashcard = allFlashcards.get(currentCardDisplayedIndex);

                q1.setText(flashcard.getAnswer());
                a1.setText(flashcard.getQuestion());

            }
        });

        if (allFlashcards != null && allFlashcards.size() > 0) {
            q1.setText(allFlashcards.get(0).getQuestion());
            a1.setText(allFlashcards.get(0).getAnswer());
        }



        q1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            // User can tap a button to change the text string of the label - Android is
            public void onClick(View v) {

                q1.setVisibility(View.INVISIBLE);
                a1.setVisibility(View.VISIBLE);

            }
        });
        a1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            // User can tap a button to change the text string of the label - Android is
            public void onClick(View v) {

                a1.setVisibility(View.INVISIBLE);
                q1.setVisibility(View.VISIBLE);

            }
        });

        findViewById(R.id.addCardButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Activity2.class);
                intent.putExtra("stringKey1",q1.getText());
                intent.putExtra("stringKey2", a1.getText());
                MainActivity.this.startActivityForResult(intent,100);
            }

        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) { // this 100 needs to match the 100 we used when we called startActivityForResult!
            String newQ = data.getExtras().getString("new_question"); // 'string1' needs to match the key we used when we put the string in the Intent
            String newA = data.getExtras().getString("new_answer");

            ((TextView) findViewById(R.id.Question1)).setText(newQ);
            ((TextView) findViewById(R.id.Answer1)).setText(newA);

            flashcardDatabase.insertCard(new Flashcard(newQ, newA));
            allFlashcards = flashcardDatabase.getAllCards();
        }
    }
}