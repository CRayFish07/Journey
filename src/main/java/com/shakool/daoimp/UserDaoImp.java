package com.shakool.daoimp;

import com.shakool.dao.UserDao;
import com.shakool.mapper.FollowingMapper;
import com.shakool.mapper.UserMapper;
import com.shakool.pojo.User;
import com.shakool.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

/**
 * Created by geekgao on 16-3-29.
 */
@Repository
public class UserDaoImp implements UserDao {

    public User getDecalredInfosWithPhone(String phone) {
        SqlSession sqlSession = null;
        User user = null;
        try {
            sqlSession = MyBatisUtils.getSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            user = mapper.getDecalredInfosWithPhone(phone);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

        return user;
    }

    public User getDecalredInfosWithUserName(String username) {
        SqlSession sqlSession = null;
        User user = null;
        try {
            sqlSession = MyBatisUtils.getSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            user = mapper.getDecalredInfosWithUserName(username);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

        return user;
    }

    public User getUserWithPhonePasswd(String phone, String passwd) {
        SqlSession sqlSession = null;
        User user = null;
        try {
            sqlSession = MyBatisUtils.getSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            user = mapper.getUserWithPhonePasswd(phone,passwd);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

        return user;
    }

    public User getUserWithUserNamePasswd(String username, String passwd) {
        SqlSession sqlSession = null;
        User user = null;
        try {
            sqlSession = MyBatisUtils.getSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            user = mapper.getUserWithUserNamePasswd(username,passwd);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

        return user;
    }

    public int phoneCount(String phone) {
        SqlSession sqlSession = null;
        int count = -1;
        try {
            sqlSession = MyBatisUtils.getSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            count = mapper.phoneCount(phone);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

        return count;
    }

    public void insert(User newUser) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.getSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            mapper.insert(newUser);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    public String getFollowings(int userId) {
        SqlSession sqlSession = null;
        List<Integer> followings = null;
        try {
            sqlSession = MyBatisUtils.getSession();
            FollowingMapper mapper = sqlSession.getMapper(FollowingMapper.class);
            followings = mapper.getFollowings(userId);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

        return followings.toString();
    }

    public String getFollowers(int userId) {
        SqlSession sqlSession = null;
        List<Integer> followings = null;
        try {
            sqlSession = MyBatisUtils.getSession();
            FollowingMapper mapper = sqlSession.getMapper(FollowingMapper.class);
            followings = mapper.getFollowers(userId);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

        return followings.toString();
    }

    public void following(int userId, int followingId) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.getSession();
            FollowingMapper mapper = sqlSession.getMapper(FollowingMapper.class);
            mapper.following(userId,followingId);
            sqlSession.commit();
        } catch (org.apache.ibatis.exceptions.PersistenceException ignore) {

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    public int userIdCount(int userId) {
        SqlSession sqlSession = null;
        int count = -1;
        try {
            sqlSession = MyBatisUtils.getSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            count = mapper.userIdCount(userId);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

        return count;
    }
}
