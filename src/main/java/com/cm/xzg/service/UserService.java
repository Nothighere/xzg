package com.cm.xzg.service;

import com.cm.xzg.bean.UserDo;
import com.cm.xzg.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public String add(UserDo user) {
        String result = userDao.insert(user);
        return result;
    }
}
