package com.example.fu.vocabsavier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by FU on 6/6/2016.
 */
public class addWord extends AppCompatActivity {
    word newWord;
    SQLiteHelper db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_word);
        db = new SQLiteHelper(getApplicationContext());

        EditText t1=(EditText) findViewById(R.id.editText);
        t1.setHint("type word here");

        EditText t2=(EditText) findViewById(R.id.editText2);
        t2.setHint("type explanation here");



        Button b3= (Button) findViewById(R.id.button3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.createWord(new word(((EditText) findViewById(R.id.editText)).getText().toString(),
                        ((EditText) findViewById(R.id.editText2)).getText().toString()));
                finish();
                //t.setText("next word");
            }
        });
        Button b4= (Button) findViewById(R.id.button4);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //t.setText("add word");
                finish();
            }
        });

    }

}
