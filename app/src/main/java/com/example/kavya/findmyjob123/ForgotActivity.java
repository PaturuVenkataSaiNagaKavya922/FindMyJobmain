package com.example.kavya.findmyjob123;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotActivity extends AppCompatActivity {
    EditText mailid,pwd1,pwd2;
    FirebaseAuth kauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        mailid=findViewById(R.id.email);
        pwd1=findViewById(R.id.rpwd);
        pwd2=findViewById(R.id.cpwd);
        kauth=FirebaseAuth.getInstance();
    }
    public void reset(View view)
    {
        String mail = mailid.getText().toString();
        if(mail.isEmpty()){
            Toast.makeText(this, "Field cant be empty", Toast.LENGTH_SHORT).show();
        }

        kauth.sendPasswordResetEmail(mail).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ForgotActivity.this, "RESENT EMAIL SENT", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ForgotActivity.this, "ENTER VALID EMAIL", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void goback(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}


