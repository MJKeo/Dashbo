package edu.gatech.mkeohane.dash;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import org.w3c.dom.Text;

public class RemindersActivity extends AppCompatActivity {
    DatabaseHelper helper;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    ArrayAdapter<String> adapter;
    ArrayList<String> itemList;
    boolean firstTime = true;

    private static final String TAG = "RemaindersActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);
        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference("reminders");
        helper = new DatabaseHelper(this);


        String[] items={""};
        itemList=new ArrayList<String>(Arrays.asList(items));
        adapter=new ArrayAdapter<String>(this,R.layout.list_item,R.id.txtview,itemList);
        ListView listV=(ListView)findViewById(R.id.list);
        listV.setAdapter(adapter);
        getList();

        Button addBtn = (Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                itemList.clear();
                EditText nameText = (EditText) findViewById(R.id.nameText);
                String name = nameText.getText().toString();
                saveInformation(name);

                updateInfo(name);
                itemList.clear();
            }
        });
    }

    private void updateInfo(String name){
        itemList.add("*" + name);
        adapter.notifyDataSetChanged();
    }

    private void saveInformation(String name) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String id = databaseReference.push().getKey();
        ReminderInfo userInfo = new ReminderInfo(name, user.getUid().substring(user.getUid().length() - 4));
        databaseReference.child(id).setValue(userInfo);

        Toast.makeText(this, "Task saved...", Toast.LENGTH_LONG).show();
    }

    private void getList() {
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(firebaseAuth.getCurrentUser() != null && firebaseAuth.getCurrentUser().getUid() != null) {
                    String fullId = firebaseAuth.getCurrentUser().getUid();
                    String id = fullId.substring(fullId.length() - 4);

                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        ReminderInfo user = ds.getValue(ReminderInfo.class);
                        if(user != null) {
                            String retrievedId = user.getId();
                            if (id.equals(retrievedId)) {
                                updateInfo(user.getName());
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                String tag = "Ouch";
                Log.d(tag, "Major Oof", databaseError.toException());
            }
        });
    }


}
