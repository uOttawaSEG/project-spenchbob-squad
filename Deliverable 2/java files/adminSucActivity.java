package com.example.login2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class adminSucActivity extends AppCompatActivity {

    Button addService;
    Button deleteAccount;
    ListView listViewServices;
    EditText ListName;
    EditText ListForm;
    EditText ListDocument;
    DatabaseHelper db;
    public static List<Service> services = new ArrayList<Service>( );
    public static int serviceNumber=3;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_suc);
        ListName = findViewById(R.id.y_ServiceName);
        ListForm = findViewById(R.id.y_forms);
        ListDocument = findViewById(R.id.y_documents);
        text = findViewById(R.id.y_textView);
        text.setText("Administrator Window");
        addService = findViewById(R.id.y_addservice);
        deleteAccount = findViewById(R.id.y_delete);

        //Variables for recording
        String[] forms = "First Name, Last Name, Date of Birth, Address, License Type".split(",");
        String[] documents = "Proof of Residence".split(",");

        //Adding threee services
        Service service1 = new Service("Driver's License", forms,documents);
        service1.id = 0;
        forms = "First Name, Last Name, Date of Birth, Address".split(",");
        documents = "Proof of Residence, Proof of Status".split(",");

        Service service2 = new Service("Health Card", forms,documents);
        service2.id = 1;
        forms = "First Name, Last Name, Date of Birth, Address".split(",");
        documents = "Proof of Residence, A Photo of the Customer".split(",");

        Service service3 = new Service("Photo ID", forms,documents);
        service3.id = 2;

        //Adding services to the list
        services.add(service1);
        services.add(service2);
        services.add(service3);

        listViewServices = findViewById(R.id.y_listview);
        ServiceList serviceAdapter = new ServiceList(adminSucActivity.this, services);
        listViewServices.setAdapter(serviceAdapter);
        db = new DatabaseHelper(this);

        addService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addService();
            }
        });

        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = ListName.getText().toString();

                if(email.isEmpty()) {
                    ListName.setError("Enter The Email to Delete");
                    ListName.requestFocus();
                }
                    else{
                            boolean check = db.chkemail(email);
                            if(check == false){

                                db.delete(email);
                                Toast.makeText(getApplicationContext(),"deleted",Toast.LENGTH_LONG).show();

                            }
                            else{
                                Toast.makeText(getApplicationContext(),"The Account Does Not Exist",Toast.LENGTH_LONG).show();
                            }

                        }
                    }
                }
        );

        listViewServices.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Service service = services.get(position);
                showUpdateDeleteDialog(service.id,service.name);
                return true;
            }
        });

    }

    private void addService(){
        String name = ListName.getText().toString().trim();
        String[] forms = ListForm.getText().toString().split(",");
        String[] documents = ListDocument.getText().toString().split(",");
        if (!TextUtils.isEmpty(name)){
            Toast.makeText(getApplicationContext(), "Service Added", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "Enter the name", Toast.LENGTH_LONG).show();
        }
        Service service = new Service(name, forms,documents);
        services.add(service);
        service.id = serviceNumber;
        serviceNumber++;
        ListName.setText("");
        ListForm.setText("");
        ListDocument.setText("");
        ServiceList serviceAdapter = new ServiceList(adminSucActivity.this, services);
        listViewServices.setAdapter(serviceAdapter);
    }

    private void updateService(int id, String name, String[] form, String[] document){
        Service tempService = new Service(name,form,document);
        tempService.id = id ;
        services.set(services.indexOf(findServiceById(id)),tempService);
        Toast.makeText(getApplicationContext(), "Service Updated", Toast.LENGTH_LONG).show();
        ServiceList serviceAdapter = new ServiceList(adminSucActivity.this, services);
        listViewServices.setAdapter(serviceAdapter);

    }
    public Service findServiceById(int id){
        for(Service service:services){
            if (service.id ==id){
                return service;
            }
        }
        return null;
    }
    public void showUpdateDeleteDialog(int id,String ServiceName){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);
        final EditText editTextName = dialogView.findViewById(R.id.eSName);
        final EditText editTextForm = dialogView.findViewById(R.id.eSForm);
        final EditText editTextDocument = dialogView.findViewById(R.id.eSDocuments);
        final Button update = dialogView.findViewById(R.id.btnUpdate);
        final Button delete = dialogView.findViewById(R.id.btnDelete);

        dialogBuilder.setTitle(ServiceName);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                services.remove(findServiceById(id));
                b.dismiss();
                ServiceList serviceAdapter = new ServiceList(adminSucActivity.this, services);
                listViewServices.setAdapter(serviceAdapter);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] tempdocuments = editTextDocument.getText().toString().split(",");
                String tempname = editTextName.getText().toString().trim();
                String[] tempforms = editTextForm.getText().toString().split(",");

                if(!TextUtils.isEmpty(tempname)){
                    updateService(id,tempname,tempforms,tempdocuments);
                    b.dismiss();

                }
            }
        });



    }



}