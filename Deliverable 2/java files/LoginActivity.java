package com.example.login2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button login ;
    Button signup;
    EditText email;
    EditText password;
    DatabaseHelper database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.x_signin);
        signup = findViewById(R.id.x_signup);
        email = findViewById(R.id.x_email);
        password = findViewById(R.id.x_password);
        database = new DatabaseHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e = email.getText().toString();
                String p = password.getText().toString();

                if(e.isEmpty()){
                    email.setError("Enter Your Email");
                    email.requestFocus();

                }else if(p.isEmpty()){
                    password.setError("Enter Your Password");
                    password.requestFocus();

                } else if(e.equals("wethebest@qq.com")  && p.equals("211314")){
                    Intent intent = new Intent(LoginActivity.this,adminSucActivity.class);
                    startActivity(intent);
                }

                else if(database.chkemail(e)){
                    Toast.makeText(getApplicationContext(),"no such user",Toast.LENGTH_LONG).show();


                }else{
                    if(database.chkemailpassword(e,p)){
                        Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this,LogSucActivity.class) ;
                        startActivity(intent);
                    }
                    else{
                        email.setError("Wrong password Provided");
                        email.requestFocus();
                        password.requestFocus();
                    }
                }


            }


        });

    }
}