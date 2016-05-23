package com.shakool.serviceimp;

import com.shakool.dao.SystemddlDao;
import com.shakool.pojo.Systemddl;
import com.shakool.service.SystemddlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by nibnait on 5/19/16.
 */
@Service
public class SystemddlServiceImp implements SystemddlService {

    @Autowired
    private SystemddlDao systemddlDao;


    public List<Systemddl> getSystemDDL(String keyword){
        return systemddlDao.getSystemDDL(keyword);
    }
}
