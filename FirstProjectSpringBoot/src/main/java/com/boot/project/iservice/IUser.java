package com.boot.project.iservice;

import com.boot.project.entities.User;
import com.boot.project.request.UserRequest;
import java.util.List;

public interface IUser {
    User createUser(UserRequest userRequest) throws Exception;

    List<User> getAll();
}
