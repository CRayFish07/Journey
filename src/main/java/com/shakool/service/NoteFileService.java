package com.shakool.service;

import com.shakool.pojo.NoteFile;
import com.shakool.query.NoteFileQuery;

import java.util.List;

/**
 * Created by nibnait on 5/19/16.
 */
public interface NoteFileService extends BaseService {
    /**添加游记图片/背景音乐*/
    public void addNoteFile(NoteFile noteFile);

    /**根据noteId 删除此游记下的所有文件*/
    public void deleteByNoteId(String id);

    /**根据主键删除*/
    public void deleteByKey(String id);

    /**根据主键批量删除*/
    public void deleteByKeys(List<String> ids);

    /**根据主键查询*/
    public List<NoteFile> getNoteFileList(NoteFileQuery noteFileQuery);
}
