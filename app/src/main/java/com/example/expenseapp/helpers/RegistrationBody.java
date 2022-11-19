package com.example.expenseapp.helpers;

public class RegistrationBody
{
    public String login;
    public String password;
    public String name;
    public String lastname;

    public RegistrationBody(String login, String password, String name, String lastname) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
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
