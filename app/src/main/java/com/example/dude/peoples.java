package com.example.dude;

public class peoples {
    private static Employee[]employees;
    private static User[] userss;
    public peoples(){
        employees = new Employee[10];
        userss = new User[10];
    }

    public static void addEmployee(String firstname,String lastname,String birthday,String address,String password,String username){
        for(int a=0; a<employees.length;a++){
            if(employees[a]==null)
                employees[a] = new Employee(firstname,lastname,birthday,address,password,username);
        }
    }

    public static void addUser(String firstname,String lastname,String birthday,String address,String password,String username){
        for(int a=0; a<userss.length;a++){
            if(userss[a]==null)
                userss[a] = new User(firstname,lastname,birthday,address,password,username);
        }
    }

    public static Employee[] getEmployee(){
        return employees;
    }

    public static User[] getUserss(){
        return userss;
    }

}
