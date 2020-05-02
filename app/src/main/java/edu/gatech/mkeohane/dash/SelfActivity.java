package edu.gatech.mkeohane.dash;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelfActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self);

        Button toDoBtn = (Button) findViewById(R.id.toDoBtn);
        toDoBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), ToDoActivity.class);
                startActivity(startIntent);
            }
        });

        Button calendarBtn = (Button) findViewById(R.id.calendarBtn);
        calendarBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), CalendarActivity.class);
                startActivity(startIntent);
            }
        });
    }
}
