package com.td.kira.lab1ex5newloginregister;

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
import com.google.firebase.auth.FirebaseAuth;

public class PasswordActivity extends AppCompatActivity {

    private EditText passwordEmail;
    private Button resetPassword;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        passwordEmail = (EditText) findViewById(R.id.etForgotPassEmail);
        resetPassword = (Button) findViewById(R.id.btResetPassword);
        firebaseAuth= FirebaseAuth.getInstance();

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail= passwordEmail.getText().toString().trim();

                if(useremail.equals("")){
                    Toast.makeText(PasswordActivity.this,"Please enter your registration email", Toast.LENGTH_LONG).show();
                }else{
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(PasswordActivity.this,"Password reset email trimis", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(PasswordActivity.this, MainActivity.class));
                            }else{
                                Toast.makeText(PasswordActivity.this,"Password reset email eshuat", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });
    }
}
