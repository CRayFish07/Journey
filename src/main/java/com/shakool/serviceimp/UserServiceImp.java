package com.shakool.serviceimp;

import com.shakool.dao.UserDao;
import com.shakool.pojo.User;
import com.shakool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by geekgao on 16-3-29.
 */
@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    public boolean verifyWithPhone(String phone, String passwd) {
        if (userDao.getUserWithPhonePasswd(phone,passwd) != null) {
            return true;
        }
        return false;
    }

    public boolean verifyWithUserName(String username, String passwd) {
        if (userDao.getUserWithUserNamePasswd(username,passwd) != null) {
            return true;
        }
        return false;
    }

    public User getDecalredInfosWithPhone(String phone) {
        return userDao.getDecalredInfosWithPhone(phone);
    }

    public User getDecalredInfosWithUserName(String username) {
        return userDao.getDecalredInfosWithUserName(username);
    }

    public boolean phoneExist(String phone) {
        int count = userDao.phoneCount(phone);
        if (count == 1) {
            return true;
        } else if (count == 0) {
            return false;
        } else if (count > 1) {
            throw new IllegalStateException("手机号重复!手机号是：" + phone);
        } else {
            throw new IllegalStateException("查询手机号存在个数出错.手机号是：" + phone);
        }

    }

    public void insert(User newUser) {
        userDao.insert(newUser);
    }

    public String getFollowings(int userId) {
        String result = userDao.getFollowings(userId);
        result = result.replace(" ","");
        result = result.substring(1,result.length() - 1);
        return result;
    }

    public void deleteFollowing(int userId, int followingId) {
        userDao.deleteFollowing(userId,followingId);
    }

    public String getFollowers(int userId) {
        String result = userDao.getFollowers(userId);
        result = result.replace(" ","");
        result = result.substring(1,result.length() - 1);
        return result;
    }

    public void following(int userId, int followingId) {
        userDao.following(userId,followingId);
    }

    public boolean userIdExist(int userId) {
        return userDao.userIdCount(userId) >= 1;
    }
}
