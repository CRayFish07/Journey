package com.shakool.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 游记图片、游记背景音乐
 * Created by nibnait on 5/19/16.
 */
public class NoteFile implements Serializable{

    private String fileId;
    private String userId;
    private String url;
    private Date uploadTime;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
}
