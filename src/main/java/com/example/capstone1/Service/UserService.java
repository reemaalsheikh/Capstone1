package com.example.capstone1.Service;
import com.example.capstone1.Model.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
@RequiredArgsConstructor
public class UserService {




    ArrayList<User> users = new ArrayList<>();

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUsers(User user) {

        users.add(user);
    }

    public boolean UpdateUser(String userId, User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId().equals(userId)) {
                users.set(i, user);
                return true;
            }
        }
        return false;
    }

    public boolean DeleteUser(String userId) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId().equals(userId)) {
                users.remove(i);
                return true;
            }
        }
        return false;
    }
    public User getUserById(String userId) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId().equalsIgnoreCase(userId)) {
                return users.get(i);
            }
        }
      return null;
    }







    }

















