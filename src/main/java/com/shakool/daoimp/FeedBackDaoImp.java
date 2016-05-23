package com.shakool.daoimp;

import com.shakool.dao.FeedBackDao;
import com.shakool.mapper.FeedBackMapper;
import com.shakool.pojo.Suggestion;
import com.shakool.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.io.IOException;

/**
 * Created by geekgao on 16-3-31.
 */
@Repository
public class FeedBackDaoImp implements FeedBackDao {

    public void insert(Suggestion suggestion) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.getSession();
            FeedBackMapper mapper = sqlSession.getMapper(FeedBackMapper.class);
            mapper.insert(suggestion);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
