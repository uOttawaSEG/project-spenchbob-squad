package com.example.dude;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity{

    /* Define the UI elements */

    private EditText eName;
    private EditText ePassword;
    private Button eRegister;
    private Button eLogin;
    private TextView id_show;

    String userName = "";
    String userPassword = "";

    String isValid = "nothing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Bind the XML views to Java Code Elements */
        eName = findViewById(R.id.eName);
        ePassword = findViewById(R.id.ePassword);
        eRegister = findViewById(R.id.eRegister);
        eLogin = findViewById(R.id.eLogin);
        id_show = findViewById(R.id.ID_show);

        //create a new file if not created
        try{

            File members = new File(Environment.getExternalStorageDirectory(),"members.txt");
                if(members.createNewFile()){
                }
                else {
                }
        }catch(IOException e){
        }

        /* Describe the logic when the login button is clicked */
        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* Obtain user inputs */
                userName = eName.getText().toString();
                userPassword = ePassword.getText().toString();

                /* Check if the user inputs are empty */
                if(userName.isEmpty() || userPassword.isEmpty())
                {
                    /* Display a message toast to user to enter the details */
                    Toast.makeText(MainActivity.this, "Please enter name and password!", Toast.LENGTH_LONG).show();

                }else {

                    /* Validate the user inputs */
                    isValid = validate(userName, userPassword);


                    /* If not valid */
                    if (isValid.equals("nothing")) {

                            id_show.setText("wrong username or password");
                    }
                    /* If valid */
                   else {

                        /* Allow the user in to your app by going into the next activity */
                        startActivity(new Intent(MainActivity.this, success.class));
                    }

                }
            }
        });
        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, regisActivity.class));
            }
        });

    }

    /* Validate the credentials */
    private String validate(String username, String password) {
        //If everything is right then enters to secondary screen
        if (username.equals("admin") && password.equals("123admin456")) {
            return "admin";
        }
        try {
            File myFile = new File(Environment.getExternalStorageDirectory(), "members.txt");
            Scanner scanner = new Scanner(myFile);
            while (scanner.hasNext()) {
                //Scanning information
                String firstname2 = scanner.next();
                String lastname2 = scanner.next();
                String birthday2 = scanner.next();
                String address2 = scanner.next();
                String password2 = scanner.next();
                String username2 = scanner.next();
                String type2 = scanner.next();
                if (username.equals(username2) && password.equals(password2)) {
                    return type2;
                }

            }
        } catch (FileNotFoundException x) {

        }
        return "nothing";
    }


}
