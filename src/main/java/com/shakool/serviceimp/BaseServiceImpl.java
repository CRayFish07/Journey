package com.shakool.serviceimp;

import com.shakool.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * Created by nibnait on 2016/5/8.
 */
@Service
public class BaseServiceImpl implements ServletContextAware, BaseService {

    private ServletContext servletContext;

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    //获取当前目录的realpath
    public String getPath(String name){
        return this.servletContext.getRealPath(name);
    }
}
