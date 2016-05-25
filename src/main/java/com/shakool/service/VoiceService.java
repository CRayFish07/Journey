package com.shakool.service;

import com.shakool.pojo.DownFile;

import java.util.List;

/**
 * Created by geekgao on 16-5-24.
 */
public interface VoiceService {
    List<DownFile> getVoiceList();
    //path就是服务器端文件存储路径，前面加上服务器ip和端口就可以直接下载
    void insert(String filename,String path);
}
