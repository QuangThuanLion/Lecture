package com.boot.project.services;

import com.boot.project.entities.Role;
import com.boot.project.entities.User;
import com.boot.project.iservice.IUser;
import com.boot.project.repositories.RoleRepository;
import com.boot.project.repositories.UserRepository;
import com.boot.project.request.UserRequest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements IUser {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    /**
     * @param userRequest
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public User createUser(UserRequest userRequest)  {
        final String roleId = userRequest.getRoleId();
        Optional<Role> roleOptional = roleRepository
                .findById(UUID.fromString(roleId));
        Role role = Optional.ofNullable(roleOptional)
                .get()
                .orElseThrow(
                        () -> new NullPointerException("Cannot find any roleId"));
        String firstName = Optional
                .ofNullable(userRequest.getFirstName())
                .orElse("N/A");
        String lastName = Optional
                .ofNullable(userRequest.getLastName())
                .orElse("N/A");

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.getRoles().add(role);

        User savedUser = userRepository.save(user);
        return savedUser;
    }

    /**
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
       return userRepository.findAll();
    }
}
