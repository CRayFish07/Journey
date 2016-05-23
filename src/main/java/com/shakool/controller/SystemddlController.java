package com.shakool.controller;

import com.shakool.common.ResponseUtils;
import com.shakool.pojo.Systemddl;
import com.shakool.service.SystemddlService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by nibnait on 5/21/16.
 */
@Controller
public class SystemddlController {

    @Autowired
    private SystemddlService systemddlService;

    @RequestMapping(value = "/note/getHumanType")
    public void getHumanType(HttpServletResponse response){

        List<Systemddl> humanTypes = systemddlService.getSystemDDL("人物");

        JSONArray array = new JSONArray(humanTypes);
        JSONObject jo = new JSONObject();
        jo.put("humanTypes",array);

        ResponseUtils.renderJson(response, jo.toString());
    }


}
