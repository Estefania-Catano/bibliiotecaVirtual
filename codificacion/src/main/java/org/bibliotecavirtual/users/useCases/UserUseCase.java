package org.bibliotecavirtual.users.useCases;
import org.bibliotecavirtual.users.models.User;
import org.bibliotecavirtual.users.datasources.UserDatasource;

import java.util.*;


public class UserUseCase {
    private final UserDatasource userDatasource;

   public UserUseCase(){
       this.userDatasource = new UserDatasource();
   }

   // Create New User

    public String create (User user) {
        try {
            this.userDatasource.save(user);
            return "User created successfully";
        } catch (Exception e) {
            return "Error creating user: " + e.getMessage();
        }

    }

    // read Users
    public String all(){
        try {
            if (userDatasource.findAll().isEmpty()) {
                return "No registered users.";
            }
            
            StringBuilder result = new StringBuilder();
            result.append("=== REGISTERED USERS ===\n");
            for (int i = 0; i < userDatasource.findAll().size(); i++) {
                User user = userDatasource.findAll().get(i);
                result.append(i + 1)
                        .append(". User: ")
                        .append(user.getName())
                        .append("\n");
            }
            return result.toString();

        } catch (Exception e) {
            return "Error reading users: " + e.getMessage();
        }
    }

    // Update User
    public String update (int index, User userIn) {
        try {
            User userFound = new User();
            for (int i = 0; i < userDatasource.findAll().size(); i++) {
                if (index == i) {
                    User user = userDatasource.findAll().get(index);
                    user.setName(userIn.getName());
                    user.setPassword(userIn.getPassword());
                    userFound = user;
                }
            }
            return "User updated successfully: " + userFound.getName();
        } catch (Exception e) {
            return "Error updating user: " + e.getMessage();
        }

    }

    // Delete User
    public String delete (int index) {
        try {
            userDatasource.deleteById((long) index);
            return "User has been deleted successfully";
        } catch (Exception e) {
            return "Error deleting user: " + e.getMessage();
        }
}

    public boolean authenticate(String name, String password) {
        List<User> users = userDatasource.findAll();
        for (User user : users) {
            if (user.validCredential(name, password)) {
                return true;
            }
        }
        return false;
    }


}
