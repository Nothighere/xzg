package com.cm.xzg.service;

import com.cm.xzg.bean.UserDo;
import com.cm.xzg.cons.CommonConst;
import com.cm.xzg.repository.UserDao;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    private  final  static org.slf4j.Logger logger = LoggerFactory.getLogger(UserService. class);
    public String add(UserDo user) {
        String result = userDao.insert(user);
        return result;
    }

    /**
     * 获取所有用户信息
     * @return
     * @throws Exception
     */
    public List<UserDo> getAllUsers() throws Exception{
        List<UserDo> resList = userDao.getAllUsers();
        return resList;
    }

    /**
     * 获取指定日期报名人数
     * @param signUpDate
     * @return
     * @throws Exception
     */
    public int getSignUpNum(String signUpDate) throws Exception{
        int num = 0;
        try {
            num = userDao.getSumOfSignUp(signUpDate);
        } catch (Exception e){
            logger.error("获取报名人数失败,cause:"+ e.getMessage());
        }
        return num;
    }

    /**
     * 查询指定日期参加人数是否超过限制 true:未超过, false:超过
     * @param signUpDate
     * @return
     */
    public boolean isOutOfLimit(String signUpDate){
        boolean flg = false;
        try {
            int limit = getSignUpNum(signUpDate);
            // 报名人数还未到上限
            if (CommonConst.SignUpLimit > limit){
                flg = true;
            }else {
                flg = false;
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return flg;
    }

    /**
     * 查询电话号码是否已经注册
     * @param phoneNum
     * @return
     */
    public int isRegeisted(String phoneNum){
        int num = 0;
        try{
            num = userDao.regeistedCheckByPhoneNum(phoneNum);
        }catch (Exception e){
            logger.error("查询电话号码是否注册时出错 cause:"+ e.getMessage());
        }
        return num;
    }
}
