package com.shakool.daoimp;

import com.shakool.dao.SystemddlDao;
import com.shakool.mapper.SystemddlMapper;
import com.shakool.pojo.Systemddl;
import com.shakool.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

/**
 * Created by nibnait on 5/19/16.
 */
@Repository
public class SystemddlDapImp implements SystemddlDao{

    public List<Systemddl> getSystemDDL(String keyword){
        SqlSession sqlSession = null;
        List<Systemddl> systemddl = null;
        try {
            sqlSession = MyBatisUtils.getSession();
            SystemddlMapper mapper = sqlSession.getMapper(SystemddlMapper.class);
            systemddl = mapper.getSystemDDL(keyword);

        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return systemddl;
    }

}
