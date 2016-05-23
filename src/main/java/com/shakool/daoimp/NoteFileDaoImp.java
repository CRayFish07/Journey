package com.shakool.daoimp;

import com.shakool.dao.NoteFileDao;
import com.shakool.pojo.NoteFile;
import com.shakool.query.NoteFileQuery;
import com.shakool.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

/**
 * Created by nibnait on 5/22/16.
 */
@Repository
public class NoteFileDaoImp implements NoteFileDao{
    /*
    SqlSession sqlSession = null;
    try{
        sqlSession = MyBatisUtils.getSession();
        sqlSession.insert("com.shakool.com.shakool.mapper.mapper.NoteFileMapper.addNoteFile",noteFile);
        sqlSession.commit();
    }catch (IOException e){
        e.printStackTrace();
    }finally {
        if (sqlSession != null) {
            sqlSession.close();
        }
    }

    */
    public void addNoteFile(NoteFile noteFile) {
        SqlSession sqlSession = null;
        try{
            sqlSession = MyBatisUtils.getSession();
            sqlSession.insert("com.shakool.dao.NoteFileDao.addNoteFile",noteFile);
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    public void deleteByKey(String id) {
        SqlSession sqlSession = null;
        try{
            sqlSession = MyBatisUtils.getSession();
            sqlSession.delete("com.shakool.dao.NoteFileDao.deleteByKey",id);
            sqlSession.commit();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    public List<NoteFile> getNoteFileList(NoteFileQuery noteFileQuery) {
        SqlSession sqlSession = null;
        List<NoteFile> noteFiles = null;
        try{
            sqlSession = MyBatisUtils.getSession();
            sqlSession.selectOne("com.shakool.dao.NoteFileDao.getNoteFileList",noteFileQuery);
            sqlSession.commit();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return noteFiles;
    }
}
