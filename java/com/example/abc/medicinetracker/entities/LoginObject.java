package com.example.abc.medicinetracker.entities;


public class LoginObject {

    private String user_id;
    private String username;
    private String email;
    private String address;
    private String phone;
    private String loggedIn;
    private String password;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LoginObject(String user_id, String username, String email, String address, String phone, String loggedIn , String password) {
        this.user_id = user_id;
        this.username = username;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.loggedIn = loggedIn;

        this.password=password;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
    public String getPassword() {
        return password;
    }


    public String getLoggedIn() {
        return loggedIn;
    }
}
