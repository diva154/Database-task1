package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        //1.create user table
        userService.createUsersTable();

        //2.Add users to database( console outputs will print automatically via Saveuser)
        userService.saveUser("Jia","Khan",(byte)20);
        userService.saveUser("Alice","Smith",(byte) 22);
        userService.saveUser("Bob","Oclean",(byte) 30);
        userService.saveUser("Henry","Brown",(byte) 45);

        //3.Get all users from database and print them to comsole
        List<User> users = userService.getAllUsers();
        for (User user : users){
            System.out.println(user);
        }

        //4.Clean the Users table
        userService.cleanUsersTable();

        //5.Drop the Users table
        userService.dropUsersTable();
    }
}
