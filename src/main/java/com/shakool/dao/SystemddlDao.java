package com.shakool.dao;

import com.shakool.pojo.Systemddl;

import java.util.List;

/**
 * Created by nibnait on 5/19/16.
 */
public interface SystemddlDao {

    //根据关键词 获取数据字典
    List<Systemddl> getSystemDDL(String keyword);
}
