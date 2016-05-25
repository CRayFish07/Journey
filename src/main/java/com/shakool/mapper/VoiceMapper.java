package com.shakool.mapper;

import com.shakool.pojo.DownFile;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by geekgao on 16-5-24.
 */
public interface VoiceMapper {
    @Select("select name,url from voice")
    List<DownFile> getVoiceList();

    @Insert("insert into voice(name,url) values(#{0},#{1})")
    void insert(String filename, String path);
}
