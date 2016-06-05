package com.shakool.mapper;

import com.shakool.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * Created by geekgao on 16-3-29.
 */
public interface UserMapper {
    @Select("select userid,username,nickname,authority,registTime,score,level,sex,birthday,phone,email,image from user where phone = #{0}")
    User getDecalredInfosWithPhone(String phone);

    @Select("select userid,username,nickname,authority,registTime,score,level,sex,birthday,phone,email,image from user where username = #{0}")
    User getDecalredInfosWithUserName(String username);

    @Select("select * from user where phone = #{0} and password = #{1}")
    User getUserWithPhonePasswd(String phone, String passwd);

    @Select("select * from user where username = #{0} and password = #{1}")
    User getUserWithUserNamePasswd(String username, String passwd);

    /**
     *
     * @param phone 手机号码
     * @return 数据库中拥有此手机号码的用户个数
     */
    @Select("select count(userId) from user where phone = #{0}")
    int phoneCount(String phone);

    @Insert("insert into user(wechatId,weiboId,qqId,username,nickname,password,authority,score,level,sex,birthday,phone,email,image,registtime) values(#{wechatId}, #{weiboId}, #{qqId}, #{username}, #{nickname}, #{password}, #{authority}, #{score}, #{level}, #{sex}, #{birthday}, #{phone}, #{email}, #{image}, #{registtime})")
    void insert(User newUser);

    @Select("select count(userId) from user where userId = #{0}")
    int userIdCount(int userId);
}
