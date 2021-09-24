package com.cm.xzg.controller;

import com.cm.xzg.bean.UserDo;
import com.cm.xzg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class userController {
    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    @ResponseBody
    public ResponseEntity<String> add(@Valid @RequestBody UserDo user, BindingResult result) {
        if (result.hasErrors()) {
            for (ObjectError error: result.getAllErrors()) {
                return ResponseEntity.ok(error.getDefaultMessage());
            }
        }
        return ResponseEntity.ok().body(userService.add(user));
    }
}
