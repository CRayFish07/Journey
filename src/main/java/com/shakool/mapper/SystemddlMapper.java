package com.shakool.mapper;

import com.shakool.pojo.Systemddl;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by nibnait on 5/19/16.
 */
public interface SystemddlMapper {

    //根据关键词 获取数据字典
    @Select("select ddlCode,ddlName from systemddl where keyword=#{0}")
    List<Systemddl> getSystemDDL(String keyword);

}
