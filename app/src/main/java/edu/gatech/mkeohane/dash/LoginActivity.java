package edu.gatech.mkeohane.dash;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText passwordText;
    private EditText usernameText;
    private Button createBtn;
    private Button signInBtn;

    private ProgressDialog pdialogue;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        pdialogue = new ProgressDialog(this);

        passwordText = (EditText) findViewById(R.id.passwordText);
        usernameText = (EditText) findViewById(R.id.usernameText);
        createBtn = (Button) findViewById(R.id.createBtn);
        signInBtn = (Button) findViewById(R.id.signInBtn);

        signInBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                signInUser();
            }
        });

        createBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(LoginActivity.this, SignIn.class));
            }
        });
    }

    private void signInUser() {
        String email = usernameText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();

        if(TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        pdialogue.setMessage("Registering User...");
        pdialogue.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, ToDoActivity.class));
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Incorrect username/password, please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        pdialogue.hide();
    }
}
