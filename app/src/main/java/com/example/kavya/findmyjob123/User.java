package com.example.kavya.findmyjob123;


class User {
    private  String name,mobileno,email,password;

    public User(String name, String mobileno, String email, String password) {
        this.name = name;
        this.mobileno = mobileno;
        this.email = email;
        this.password = password;
    }

   public User() {
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileno() { return mobileno; }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) {
        this.password = password;
    }
}
