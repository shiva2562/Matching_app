package com.example.lab.stablematching;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class ThirdActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private CollectionReference soref =db.collection("user-data");
    private String cuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        final Button userspr;
        final Button leave;
        final LinearLayout profs;
        userspr = (Button) findViewById(R.id.jointa);
        leave = (Button) findViewById(R.id.leaveta);
        cuser=FirebaseAuth.getInstance().getCurrentUser().getUid();
        soref.document(cuser).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    boolean tab = documentSnapshot.getBoolean("table");
                    if(tab){
                        userspr.setEnabled(false);
                        leave.setEnabled(true);
                    }
                    else{
                        userspr.setEnabled(true);
                        leave.setEnabled(false);
                    }
                }
                else{
                    Toast.makeText(ThirdActivity.this, "no such thing exists", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ThirdActivity.this, "sorry! can't join the table", Toast.LENGTH_SHORT).show();
                    }
                });

        profs = (LinearLayout) findViewById(R.id.vertical1);
        Spinner spinner = findViewById(R.id.spinner1);
        Spinner spinner4 = findViewById(R.id.spinner2);
        Spinner spinner5 = findViewById(R.id.spinner3);
        spinner.setOnItemSelectedListener(this);
        spinner4.setOnItemSelectedListener(this);
        spinner5.setOnItemSelectedListener(this);
        spinner.setOnItemSelectedListener(this);
        List<Integer> ranks = new ArrayList<Integer>();
        ranks.add(1);
        ranks.add(2);
        ranks.add(3);
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, ranks);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner4.setAdapter(dataAdapter);
        spinner5.setAdapter(dataAdapter);
        userspr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soref.document("table").update("tas",FieldValue.arrayUnion(cuser));
                soref.document(cuser).update("table",true);
                soref.document("table").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        List<String> profs= (List<String>) documentSnapshot.get("profs");
                        for (String prof:profs){
                            soref.document(cuser).update("plist",FieldValue.arrayUnion(prof));
                            soref.document(prof).update("plist",FieldValue.arrayUnion(cuser));
                        }
                    }
                });
                userspr.setEnabled(false);
                leave.setEnabled(true);
            }
        });
        leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soref.document("table").update("tas",FieldValue.arrayRemove(cuser));
                soref.document(cuser).update("table",false);
                soref.document("table").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        List<String> profs= (List<String>) documentSnapshot.get("profs");
                        for (String prof:profs){
                            soref.document(cuser).update("plist",FieldValue.arrayRemove(prof));
                            soref.document(prof).update("plist",FieldValue.arrayRemove(cuser));
                        }
                    }
                });
                userspr.setEnabled(true);
                leave.setEnabled(false);

            }
        });
    }
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item;
        switch( parent.getId()){
            case R.id.spinner1:
                item = parent.getItemAtPosition(position).toString();
                TextView textElement = (TextView) findViewById(R.id.ran1);
                textElement.setText(item);
                break;
            case R.id.spinner2:
                item = parent.getItemAtPosition(position).toString();
                textElement = (TextView) findViewById(R.id.ran2);
                textElement.setText(item);
                break;
            case R.id.spinner3:
                item = parent.getItemAtPosition(position).toString();
                textElement = (TextView) findViewById(R.id.ran3);
                textElement.setText(item);
                break;
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
