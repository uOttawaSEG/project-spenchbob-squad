package com.example.login2;

public class Service {

    String name;
    String[] form;
    String[] document;

    int id;

    public Service(String name,String[] form,  String[] documents){
        this.name = name;
        this.form = form;
        this.document= documents;

    }

    public Service(int id){
        this.id = id;
    }

    public Service(String name){
        form = new String[30];
        this.name = name;
        document = new String[30];
    }
}
