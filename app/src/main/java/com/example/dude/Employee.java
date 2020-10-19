package com.example.dude;

public class Employee {

    private String firstname;
    private String lastname;
    private String birthday;
    private String address;
    private String password;
    private String username;

    public Employee(){

    }

    public Employee(String firstname,String lastname,String birthday,String address,String password,String username){
        firstname = this.firstname;
        lastname = this.lastname;
        birthday = this.birthday;
        address = this.address;
        password = this.password;
        username = this.username;
    }

    public String getType() {
        return "employee";
    }

    public String getFirstname() {
        return firstname;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
}
