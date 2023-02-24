package com.example.users.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.users.entity.User;
import com.example.users.repository.UserRepository;
import com.example.users.service.UserService;

import exception.UsernameAlreadyUsedException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
@RestController
public class ConnectionController {
	@Autowired
    private UserService userService;

    @Autowired
    private UserRepository userDao;

    @Autowired
    private SimpMessagingTemplate template;

   

   

    @RequestMapping(value = "/listUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<User> findConnectedUsers() {
        return userDao.findAll();
    }

    @RequestMapping(value = "/clearAll", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clearAll() {
        userDao.deleteAll();
    }
}
