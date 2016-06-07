package com.example.fu.vocabsavier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView t1,t2;
    String w,explain;
    SQLiteHelper db = new SQLiteHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db.onUpgrade(db.getWritableDatabase(), 1, 2);

        //db.createWord(new word("first word","lalala"));



        t1=(TextView) findViewById(R.id.textView);
        t1.setText("Day Day Up ^^");

        t2=(TextView) findViewById(R.id.textView2);
        //t2.setHint("type explanation here");
        explain="";

        Button b1= (Button) findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t2.setText("");
                word word = db.readWord();
                if(word==null)
                {
                    t1.setText("Database Empty");
                    explain="";
                }
                else
                {
                    t1.setText(word.getWord());
                    explain=word.getExplain();
                }

            }
        });
        Button b2= (Button) findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t1.setText("adding...");
                t2.setText("");
                Intent startGameIntent = new Intent(getApplicationContext(), addWord.class);
                startActivity(startGameIntent);
                t1.setText("Day Day Up ^^");
            }
        });
        Button b5= (Button) findViewById(R.id.button5);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t2.setText(explain);
            }
        });

    }


}
