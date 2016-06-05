package com.shakool.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by geekgao on 16-6-1.
 */
public interface FollowingMapper {
    @Select("select followingId from following where userId=#{0}")
    List<Integer> getFollowings(int userId);

    @Select("select userId from following where followingId=#{0}")
    List<Integer> getFollowers(int userId);

    @Insert("insert into following(userId,followingId) values(#{0},#{1})")
    void following(int userId, int followingId);
}
