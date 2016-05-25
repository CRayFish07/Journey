package com.shakool.dao;

import com.shakool.pojo.DownFile;

import java.util.List;

/**
 * Created by geekgao on 16-5-24.
 * 操作语音包表
 */
public interface VoiceDao {
    List<DownFile> getVoiceList();

    void insert(String filename, String path);
}
