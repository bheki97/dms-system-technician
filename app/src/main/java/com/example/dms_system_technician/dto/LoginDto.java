package com.example.dms_system_technician.dto;

public class LoginDto {
    private String username;
    private String password;

    public LoginDto() {
    }

    public LoginDto(String email, String password) {
        this.username = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginDto{" +
                "email='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
