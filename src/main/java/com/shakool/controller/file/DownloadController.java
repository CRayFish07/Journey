package com.shakool.controller.file;

import com.shakool.service.VoiceService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by geekgao on 16-4-26.
 */
@Controller
@RequestMapping("/file")
public class DownloadController {
    @Autowired
    private VoiceService voiceService;

    @RequestMapping(value = "/voicelist",method = RequestMethod.GET)
    public @ResponseBody String getVoiceList() {
        JSONObject resultJson = new JSONObject();
        JSONArray voices = new JSONArray(voiceService.getVoiceList());
        resultJson.put("voices",voices);
        return resultJson.toString();
    }
}
