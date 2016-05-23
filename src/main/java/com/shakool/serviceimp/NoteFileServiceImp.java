package com.shakool.serviceimp;

import com.shakool.dao.NoteFileDao;
import com.shakool.pojo.NoteFile;
import com.shakool.query.NoteFileQuery;
import com.shakool.service.NoteFileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

/**
 * Created by nibnait on 5/19/16.
 */
@Service
public class NoteFileServiceImp extends BaseServiceImpl implements NoteFileService{

    @Resource
    NoteFileDao noteFileDao;

    /**添加 游记图片/背景音乐*/
    public void addNoteFile(NoteFile noteFile) {
        noteFileDao.addNoteFile(noteFile);
    }

    /**根据noteId 删除此游记下的所有文件*/
    public void deleteByNoteId(String id) {
        NoteFileQuery noteFileQuery= new NoteFileQuery();
        /*noteFileQuery.setNoteId(id);
        List<NoteFile> noteFiles = getNoteFileList(noteFileQuery);

        //先删除服务器上的文件
        deleteServerFile(noteFiles);*/
    }

    private void deleteServerFile(List<NoteFile> noteFiles){
        String webRoot = getPath("/");
        for (NoteFile noteFile:noteFiles){
            //先删除服务器上的文件
            String bathpath = noteFile.getUrl();
            String filePath = webRoot + bathpath;
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
            //再删除 数据库中的数据
            deleteByKey(noteFile.getFileId());

        }
    }
    private void deleteServerFileByFileId(String id) {
        NoteFileQuery noteFileQuery= new NoteFileQuery();
        noteFileQuery.setFileId(id);
        List<NoteFile> noteFiles = getNoteFileList(noteFileQuery);
        NoteFile noteFile = noteFiles.get(0);
        String webRoot = getPath("/");
        String bathpath = noteFile.getUrl();
        String filePath = webRoot + bathpath;
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    /**根据主键删除*/
    public void deleteByKey(String id) {
        //先删除服务器上的文件
        deleteServerFileByFileId(id);
        //再删除 数据库中的数据
        noteFileDao.deleteByKey(id);
    }

    /**根据主键批量删除*/
    public void deleteByKeys(List<String> ids) {

        for (String id:ids){
            deleteByKey(id);
        }
    }


    /**根据主键查询*/
    public List<NoteFile> getNoteFileList(NoteFileQuery noteFileQuery) {
        return noteFileDao.getNoteFileList(noteFileQuery);
    }
}
