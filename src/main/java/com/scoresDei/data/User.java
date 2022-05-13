package com.scoresDei.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import java.util.List;

@Entity
@Table(name = "score_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String passwordHash;
    private String telephone;
    private String email;
    private boolean isAdmin;

    @ManyToMany(mappedBy = "user")
    private List<Event> events;

    public User(String username, String passwordHash, String telephone, String email, String isAdmin) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.telephone = telephone;
        this.email = email;
        this.isAdmin = isAdmin != null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public List<Event> getGeneratedEvents() {
        return events;
    }

    public void setGeneratedEvents(List<Event> generatedEvents) {
        this.events = generatedEvents;
    }

    @Override
    public String toString() {
        return "User [email=" + email + ", generatedEvents=" + events + ", id=" + id + ", isAdmin=" + isAdmin
                + ", passwordHash=" + passwordHash + ", telephone=" + telephone + ", username=" + username + "]";
    }
}
