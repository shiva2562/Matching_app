package com.example.lab.stablematching;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signupform extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupform);


        Button signupaspr;
        Button signupasta;
        final EditText signupuser;
        final EditText signuppass;
        signupuser=(EditText)findViewById(R.id.signupuser);
        signuppass=(EditText)findViewById(R.id.signuppass);
        signupaspr=(Button)findViewById(R.id.signupaspr);
        signupasta=(Button)findViewById(R.id.signupasta);
        signupaspr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                    arrayofstrings.usernames.add(username);
                    arrayofstrings.passwords.add(password);

                    Intent intent1 = new Intent(signupform.this, MainActivity.class);
                    startActivity(intent1);
                }
            }

        });
        signupasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=signupuser.getText().toString();
                String password=signuppass.getText().toString();
                if (arrayofstrings.usernamesta.contains(username)){

                    Toast.makeText(getBaseContext(),"username already exists",Toast.LENGTH_LONG).show();
                }
                else if (username==null || username.trim().equals("") ){
                    Toast.makeText(getBaseContext(),"username cannot be empty",Toast.LENGTH_LONG).show();
                }
                else if (password==null || password.trim().equals("") ){
                    Toast.makeText(getBaseContext(),"password cannot be empty",Toast.LENGTH_LONG).show();
                }

                else {

                    arrayofstrings.usernamesta.add(username);
                    arrayofstrings.passwordsta.add(password);

                    Intent intent1 = new Intent(signupform.this, MainActivity.class);
                    startActivity(intent1);
                }
            }

        });
    }
}
