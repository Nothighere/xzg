package com.cm.xzg.controller;

import com.cm.xzg.bean.UserDo;
import com.cm.xzg.service.UserService;
import lombok.extern.slf4j.XSlf4j;
import org.slf4j.ILoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime signUpDate = user.getRegeisteDate();
        String signUpDateStr = df.format(signUpDate);
        // 查看是否到达报名上限
        if (!userService.isOutOfLimit(signUpDateStr)){
            return ResponseEntity.ok().body("选定日期内名额已满！");
        }
        // 检查报名手机号是否已经报名
        int res = userService.isRegeisted(user.getPhoneNumber());
        if (res > 0){
            return ResponseEntity.ok("该手机号已经报名！");
        }
        return ResponseEntity.ok().body(userService.add(user));
    }


    @PostMapping("/getAllUsers")
    @ResponseBody
    public ResponseEntity<List<UserDo>> getAllUsers(){
        List <UserDo> resList = new ArrayList<>();
        try {
            resList = userService.getAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(resList);
    }

//    @PostMapping("/checkByPhone")
//    @ResponseBody
//    public ResponseEntity<Integer> checkByPhone(@RequestBody UserDo userDo){
//
//        int res = userService.isRegeisted(userDo.getPhoneNumber());
//        return ResponseEntity.ok(res);
//    }


}
