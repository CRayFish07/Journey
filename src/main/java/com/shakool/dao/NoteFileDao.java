package com.shakool.dao;

import com.shakool.pojo.NoteFile;
import com.shakool.query.NoteFileQuery;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by nibnait on 5/19/16.
 */
public interface NoteFileDao {

    /**添加游记图片/背景音乐*/
    public void addNoteFile(NoteFile noteFile);


    /**根据主键删除*/
    public void deleteByKey(String id);


    /**根据主键查询*/
    public List<NoteFile> getNoteFileList(NoteFileQuery noteFileQuery);
}
