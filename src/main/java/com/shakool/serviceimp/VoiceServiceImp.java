package com.shakool.serviceimp;

import com.shakool.dao.VoiceDao;
import com.shakool.pojo.DownFile;
import com.shakool.service.VoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by geekgao on 16-5-24.
 */
@Service
public class VoiceServiceImp implements VoiceService{
    @Autowired
    private VoiceDao voiceDao;

    public List<DownFile> getVoiceList() {
        return voiceDao.getVoiceList();
    }

    public void insert(String filename, String path) {
        voiceDao.insert(filename,path);
    }
}
