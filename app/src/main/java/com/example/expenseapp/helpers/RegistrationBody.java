package com.example.expenseapp.helpers;

public class RegistrationBody
{
    public String login;
    public String password;
    public String name;
    public String lastname;
    public String url;

    public RegistrationBody(String login, String password, String name, String lastname, String url) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.url = url;
    }

    public RegistrationBody(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegistrationBody{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
