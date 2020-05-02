package edu.gatech.mkeohane.dash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TeamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        Button goToTeamBtn = (Button) findViewById(R.id.goToTeamBtn);
        goToTeamBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), MyTeamActivity.class);
                startActivity(startIntent);
            }
        });
    }
}
