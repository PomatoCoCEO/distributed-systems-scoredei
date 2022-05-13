package com.scoresDei.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import java.util.List;

public class UserDTO {
    private String username;
    private String password;
    private String telephone;
    private String email;
    private String adminCode;
    private String isAdmin;

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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String isAdmin() {
        return isAdmin;
    }

    public void setAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(String code) {
        this.adminCode = code;
    }

    @Override
    public String toString() {
        return "UserDTO [adminCode=" + adminCode + ", email=" + email + ", isAdmin=" + isAdmin + ", password="
                + password + ", telephone=" + telephone + ", username=" + username + "]";
    }
}
