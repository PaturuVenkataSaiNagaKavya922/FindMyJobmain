package com.example.kavya.findmyjob123;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    Button reg;
    EditText name1,mobno,email, pass;
    ProgressDialog progressDialog;
    private FirebaseAuth mAuthe;
    DatabaseReference reference;


    /*Button signin;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        reg=findViewById(R.id.reg);
        name1=findViewById(R.id.name);
        mobno=findViewById(R.id.mobile);
        email=findViewById(R.id.et1);
        pass=findViewById(R.id.et2);
        mAuthe = FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        reference= FirebaseDatabase.getInstance().getReference("job");



        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String name=name1.getText().toString();
                String mobile=mobno.getText().toString();
                String et1 =email.getText().toString();
                String et2= pass.getText().toString();
                String id=reference.push().getKey();
                User user=new User(name,mobile,et1,et2);
                reference.child(id).setValue(user);
                if (et1.isEmpty() ||et2 .isEmpty()|| mobile.isEmpty()|| name.isEmpty())
                {
                    Toast.makeText(RegisterActivity.this, "enter a mail id", Toast.LENGTH_LONG).show();
                } else {
                    progressDialog.setMessage("SigningUp...");
                    progressDialog.show();
                    mAuthe.createUserWithEmailAndPassword(et1, et2)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>()
                            {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = mAuthe.getCurrentUser();
                                        progressDialog.dismiss();
                                        Toast.makeText(RegisterActivity.this, "Success", Toast.LENGTH_SHORT).show();

                                        startActivity(new Intent(RegisterActivity.this,MainActivity.class));



                                    } else
                                    {
                                        progressDialog.dismiss();
                                        //Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                        Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            }
        });
    }

    public void login(View view) {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
