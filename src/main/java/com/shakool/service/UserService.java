package com.shakool.service;

import com.shakool.pojo.User;

/**
 * Created by geekgao on 16-3-29.
 */
public interface UserService {
    boolean verifyWithPhone(String phone,String passwd);

    boolean verifyWithUserName(String phone,String passwd);

    User getDecalredInfosWithPhone(String phone);

    User getDecalredInfosWithUserName(String username);

    boolean phoneExist(String phone);

    boolean userIdExist(int userId);

    void insert(User newUser);

    //获取关注的人,结果中包含关注的人的id,用逗号隔开:"1,2,3,4"
    String getFollowings(int userId);

    //获取userId的粉丝,结果中包含粉丝的id,用逗号隔开:"1,2,3,4"
    String getFollowers(int userId);

    //userId关注followingId
    void following(int userId,int followingId);
}
