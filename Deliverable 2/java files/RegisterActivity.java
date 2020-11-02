package com.example.login2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText firstname;
    EditText lastname;
    EditText email;
    EditText password;
    EditText Confirm;
    TextView warning;
    Button register;
    Switch identity;
    DatabaseHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Confirm = findViewById(R.id.z_reeneter);
        register = findViewById(R.id.z_signup);
        identity = findViewById(R.id.z_switch);
        database = new DatabaseHelper(this);
        firstname = findViewById(R.id.z_firstname);
        lastname = findViewById(R.id.z_lastname);
        email = findViewById(R.id.z_email);
        password = findViewById(R.id.z_password);
        warning = findViewById(R.id.z_warning);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Getting text
                String email2 = email.getText().toString();
                String password2 = password.getText().toString();
                String firstName2 = firstname.getText().toString();
                String lastName2 = lastname.getText().toString();
                String confirm2 = Confirm.getText().toString();

                //Bullet Proofing
                if(firstName2.isEmpty()){
                    firstname.setError("First Name Empty");
                    firstname.requestFocus();
                }else if(lastName2.isEmpty()){
                    lastname.setError("Last Name Empty");
                    lastname.requestFocus();
                }
                else if (email2.isEmpty()){
                    email.setError("Email Empty");
                    email.requestFocus();
                }else if(password2.isEmpty()){
                    password.setError("Password Empty");
                    password.requestFocus();
                }else if(confirm2.isEmpty()){
                    Confirm.setError("Password Empty");
                    Confirm.requestFocus();
                }else if(!firstName2.isEmpty()&&!lastName2.isEmpty()&&!email2.isEmpty()&&!password2.isEmpty()&&!confirm2.isEmpty()) {
                    if (!password2.equals(confirm2)) {
                        Confirm.setError(("Two passwords don't match"));
                        Confirm.requestFocus();
                    }
                    else{ Intent intent;
                        if(!identity.isChecked()){
                            boolean check = database.chkemail(email2);
                            if(check){
                                String name = firstName2+" "+lastName2;
                                boolean insert = database.insert(email2,password2,"customer",name);
                                if(insert){
                                    intent=new Intent(RegisterActivity.this,CusRegSucActivity.class);
                                    startActivity(intent);
                                }else{
                                    warning.setText("Fail to Register");

                                }
                            }
                            else{
                                warning.setText("User already exists");
                            }

                        }
                        else{
                            boolean check =database.chkemail(email2);
                            if(check){
                                String name = firstName2+" "+lastName2;
                                boolean insert = database.insert(email2,password2,"Employee",name);
                                if(insert){
                                    intent=new Intent(RegisterActivity.this,CusRegSucActivity.class);
                                    startActivity(intent);
                                }else{
                                    warning.setText("False to Register");
                                }
                            }
                            else{
                                warning.setText("User already exists");
                            }

                        }
                    }
                }
            }
        });
    }
}
