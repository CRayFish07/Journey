package com.shakool.service;

import com.shakool.pojo.Systemddl;

import java.util.List;

/**
 * Created by nibnait on 5/19/16.
 */
public interface SystemddlService {

    //根据关键词 获取数据字典
    List<Systemddl> getSystemDDL(String keyword);
}
