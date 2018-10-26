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
    private DocumentReference ref =db.document("user-data/users");
    private CollectionReference refe =db.collection("user-data");
    Button signupasta;
    private EditText signupuser;
    private EditText signuppass;
    private EditText nameuser;
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
        String username0=nameuser.getText().toString();

            mAuth.createUserWithEmailAndPassword(username,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){

                        Toast.makeText(getBaseContext(),"Successful",Toast.LENGTH_LONG).show();
                    }
                }
            });
            userbase users=new userbase(username,username0);
            refe.add(users).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Toast.makeText(signupform.this, "Success", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(signupform.this, "Fail", Toast.LENGTH_SHORT).show();
                }
            });
            /*ref.set(users).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(signupform.this, "Success", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(signupform.this, "fail", Toast.LENGTH_SHORT).show();
                }
            });*/
            Intent intent1 = new Intent(signupform.this, MainActivity.class);
            startActivity(intent1);


    }
}
