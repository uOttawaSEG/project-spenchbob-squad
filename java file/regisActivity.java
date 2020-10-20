package com.example.dude;

import androidx.appcompat.app.AppCompatActivity;
//import static com.example.dude.MainActivity.addEmployee;
//import static com.example.dude.MainActivity.addUser;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class regisActivity extends AppCompatActivity {


    private String type;
    private EditText username;
    private EditText password;
    private EditText birthday;
    private EditText firstname;
    private EditText lastname;
    private EditText address;
    private RadioButton user;
    private RadioButton employee;
    private Button cancel;
    private Button register;
    private TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);
        username = findViewById(R.id.ID_username);
        password = findViewById(R.id.ID_password);
        birthday = findViewById(R.id.ID_birthday);
        firstname = findViewById(R.id.ID_firstname);
        lastname = findViewById(R.id.ID_lastname);
        address = findViewById(R.id.ID_address);
        cancel = findViewById(R.id.ID_CANCEL);
        register = findViewById(R.id.ID_register);
        user = findViewById(R.id.ID_User);
        employee = findViewById(R.id.ID_employee);
        message = findViewById(R.id.ID_message);
        if(user.isChecked())
            type = "user";
        if(employee.isChecked())
            type = "employee";

        //go back to main menu without storing information
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(regisActivity.this, MainActivity.class));
            }
        });

        //go back to main menu with storing information
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {
                    File file = new File(Environment.getExternalStorageDirectory(),"members.txt");
                    FileWriter myWriter = new FileWriter("members.txt");
                    myWriter.write(firstname.getText().toString()+" "+lastname.getText().toString()+" "
                            +" "+birthday.getText().toString()+" "+address.getText().toString()+" "+
                            password.getText().toString()+" "+username.getText().toString() +type);
                    myWriter.close();
                }catch(IOException e){

                }
                if(firstname.getText().toString().equals(null)){
                    message.setText(" firstname has left blank");
                }
                else if(lastname.getText().toString().equals(null)){
                    message.setText(" lastname has left blank");
                }
                else if(birthday.getText().toString().equals(null)){
                    message.setText("birthday has left blank");
                }
                else if(birthday.getText().toString().length()!=8){
                    message.setText("birthday is in wrong format");
                }
                else if(address.getText().toString().equals(null)){
                    message.setText("address has left blank");
                }
                else if(password.getText().toString().equals(null)){
                    message.setText("password has left blank");
                }
                else if(username.getText().toString().equals(null)){
                    message.setText("username has left blank");
                }
                else if(user.isSelected()==false&&employee.isSelected()==false){
                    message.setText("please check and select one of user or employee");
                }
                else if(user.isSelected()==true&&employee.isSelected()==true){
                    message.setText("you can't select both user and employee");
                }
                else {
                    startActivity(new Intent(regisActivity.this, MainActivity.class));
                }
            }
        });
    }

}