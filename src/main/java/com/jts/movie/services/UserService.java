package com.jts.movie.services;

import com.jts.movie.Exceptions.UserExist;
import com.jts.movie.convertor.UserConvertor;
import com.jts.movie.entities.User;
import com.jts.movie.repositories.UserRepository;
import com.jts.movie.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public String addUser(UserRequest userRequest)
    {
        if(userRepository.findByEmailId(userRequest.getEmailId())!=null)
        {
            throw new UserExist();
        }
        User user = UserConvertor.userDtoToUser(userRequest,passwordEncoder.encode("1234"));
        userRepository.save(user);
        return "User saved successfully";
    }
}
