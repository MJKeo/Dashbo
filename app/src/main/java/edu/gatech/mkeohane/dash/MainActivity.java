package edu.gatech.mkeohane.dash;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView click = (TextView) findViewById(R.id.clickText);
        click.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), SignIn.class);
                startActivity(startIntent);
            }
        });
        TextView website = (TextView) findViewById(R.id.textView5);
        website.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String link = "https://www.dashbo.tech";
                Uri web = Uri.parse(link);
                startActivity(new Intent(Intent.ACTION_VIEW, web));
            }
        });
    }

}

