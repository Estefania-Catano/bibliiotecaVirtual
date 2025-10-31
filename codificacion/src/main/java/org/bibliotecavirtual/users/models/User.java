package org.bibliotecavirtual.users.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;
    /*private String role;*/

    public User(String name, String password /*,String role*/) {
        this.name = name;
        this.password = password;
       /* this.role = role;*/
    }

    public User() {

    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean validCredential(String name, String password) {
        return this.name.equals(name) && this.password.equals(password);
    }


   /* public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }*/
}
