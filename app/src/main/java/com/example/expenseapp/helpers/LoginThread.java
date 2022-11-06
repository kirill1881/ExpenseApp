package com.example.expenseapp.helpers;

public class LoginThread extends Thread{
    private String login;
    private String password;

    public LoginThread(String login, String password){
        this.login = login;
        this.password = password;
    }
    
}
