package com.example.message.controller;

import com.example.message.domain.dto.UserDto;
import com.example.message.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserController(UserService userService) {
        this.service = userService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerUser(@RequestBody UserDto userDto, HttpServletResponse response) {
        if (!service.createUser(userDto)){
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }
    }

    @RequestMapping(value = "/{mail}", method = RequestMethod.GET)
    public UserDto getByMail(@PathVariable String mail, HttpServletResponse response) {
        UserDto userDto = service.getUserByMail(mail);
        if (userDto == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return userDto;
    }

    private final UserService service;
}
