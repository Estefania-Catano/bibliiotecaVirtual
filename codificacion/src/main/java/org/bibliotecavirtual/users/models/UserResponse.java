package org.bibliotecavirtual.users.models;

public class UserResponse {
    
    private Long id;
    private String name;
    private String password;
    /*private String role;*/

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.password = user.getPassword();
    }

    public UserResponse() {

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

