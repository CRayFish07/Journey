package com.shakool.dao;

import com.shakool.pojo.User;

/**
 * Created by geekgao on 16-3-29.
 */
public interface UserDao {
    User getDecalredInfosWithPhone(String phone);

    User getDecalredInfosWithUserName(String username);

    User getUserWithPhonePasswd(String phone,String passwd);

    User getUserWithUserNamePasswd(String username,String passwd);

    /**
     *
     * @param phone 手机号码
     * @return 数据库中拥有此手机号码的用户个数，返回-1代表出错
     */
    int phoneCount(String phone);

    /**
     *
     * @param newUser 只会将新用户的phone,password,nickname,registtime存进数据库（注册时只有这几个数据）
     */
    void insert(User newUser);

    //获取关注的人,结果中包含关注的人的id,用逗号隔开:"1,2,3,4"
    String getFollowings(int userId);

    //获取userId的粉丝,结果中包含粉丝的id,用逗号隔开:"1,2,3,4"
    String getFollowers(int userId);

    //userId关注followingId
    void following(int userId,int followingId);

    int userIdCount(int userId);

    void deleteFollowing(int userId, int followingId);
}
