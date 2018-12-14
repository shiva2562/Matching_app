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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class signupform extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference refe =db.collection("user-data");
    private EditText signupuser;
    private EditText signuppass;
    private EditText nameuser;
    private String cuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupform);


        Button signupaspr;
        Button signupasta;
        mAuth = FirebaseAuth.getInstance();
        signupuser=(EditText)findViewById(R.id.signupuser);
        signuppass=(EditText)findViewById(R.id.signuppass);
        nameuser=(EditText)findViewById(R.id.username0);
        signupaspr=(Button)findViewById(R.id.signupaspr);
        signupasta=(Button)findViewById(R.id.signupasta);
        signupaspr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register("pr");

            }

        });
        signupasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register("ta");
            }

        });
    }
    private void register(final String rol){
        final String username=signupuser.getText().toString();
        final String password=signuppass.getText().toString();
        String username0=nameuser.getText().toString();
        final userbase users=new userbase(username,username0,rol);
        mAuth.createUserWithEmailAndPassword(username,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        cuser=FirebaseAuth.getInstance().getCurrentUser().getUid();
                        refe.document(cuser).set(users);
                        if(rol=="pr") {
                            Intent intent = new Intent(signupform.this, SecondActivity.class);
                            startActivity(intent);
                        }
                        else if (rol=="ta"){
                            Intent intent = new Intent(signupform.this, ThirdActivity.class);
                            startActivity(intent);
                        }
                    }
                }
            });

    }
}
