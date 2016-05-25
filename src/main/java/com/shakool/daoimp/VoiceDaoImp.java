package com.shakool.daoimp;

import com.shakool.dao.VoiceDao;
import com.shakool.mapper.VoiceMapper;
import com.shakool.pojo.DownFile;
import com.shakool.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

/**
 * Created by geekgao on 16-5-24.
 */
@Repository
public class VoiceDaoImp implements VoiceDao{

    public List<DownFile> getVoiceList() {
        SqlSession sqlSession = null;
        List<DownFile> voiceList = null;
        try {
            sqlSession = MyBatisUtils.getSession();
            VoiceMapper mapper = sqlSession.getMapper(VoiceMapper.class);
            voiceList = mapper.getVoiceList();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

        return voiceList;
    }

    public void insert(String filename, String path) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.getSession();
            VoiceMapper mapper = sqlSession.getMapper(VoiceMapper.class);
            mapper.insert(filename,path);
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
