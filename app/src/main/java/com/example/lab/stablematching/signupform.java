package com.example.lab.stablematching;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signupform extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button signupasta;
    private EditText signupuser;
    private EditText signuppass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupform);


        Button signupaspr;
        Button signupasta;
        mAuth = FirebaseAuth.getInstance();
        signupuser=(EditText)findViewById(R.id.signupuser);
        signuppass=(EditText)findViewById(R.id.signuppass);
        signupaspr=(Button)findViewById(R.id.signupaspr);
        signupasta=(Button)findViewById(R.id.signupasta);
        signupaspr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();

            }

        });
        signupasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }

        });
    }
    private void register(){
        String username=signupuser.getText().toString();
        String password=signuppass.getText().toString();
        if (arrayofstrings.usernames.contains(username)){

            Toast.makeText(getBaseContext(),"username already exists",Toast.LENGTH_LONG).show();
        }
        else if (username==null || username.trim().equals("") ){
            Toast.makeText(getBaseContext(),"username cannot be empty",Toast.LENGTH_LONG).show();
        }
        else if (password==null || password.trim().equals("") ){
            Toast.makeText(getBaseContext(),"password cannot be empty",Toast.LENGTH_LONG).show();
        }

        else {

            mAuth.createUserWithEmailAndPassword(username,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){

                        Toast.makeText(getBaseContext(),"Successful",Toast.LENGTH_LONG).show();
                    }
                }
            });
            Intent intent1 = new Intent(signupform.this, MainActivity.class);
            startActivity(intent1);
        }
    }
}
