package com.shakool.controller.file;

import com.shakool.common.ResponseUtils;
import com.shakool.common.UploadUtils;
import com.shakool.pojo.NoteFile;
import com.shakool.service.NoteFileService;
import com.shakool.service.VoiceService;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Date;

/**
 * Created by nibnait on 5/19/16.
 */
@Controller
@RequestMapping("/file")
public class UploadController {

    @Autowired
    private NoteFileService noteFileService;
    @Autowired
    private VoiceService voiceService;

    //上传游记图片 到本地服务器
    @RequestMapping(value = "/uploadImg" ,method = RequestMethod.POST)
    public void uploadImg(@RequestParam(required = false) MultipartFile img, String userId, HttpServletResponse response, HttpServletRequest request) {
        JSONObject jo = UploadUtils.uploadFile(img, "NoteImg",request);
        util(jo,userId,response);
        ResponseUtils.renderJson(response, jo.toString());
    }

    /**判断userId是否为空 & 讲jo.path保存到数据库中*/
    private void util(JSONObject jo, String userId,HttpServletResponse response) {
        if (!StringUtils.isNotBlank(userId)){
            jo.put("encode",1);
            jo.put("msg","userId不能为空");
            ResponseUtils.renderJson(response, jo.toString());
            return;
        }

        NoteFile noteFile = new NoteFile();
        String path = (String) jo.get("path");
        String noteFileId = path.substring(path.lastIndexOf("/"),path.lastIndexOf(".")).substring(1);
        noteFile.setFileId(noteFileId);
        noteFile.setUserId(userId);
        noteFile.setUrl(path);
        noteFile.setUploadTime(new Date());
        noteFileService.addNoteFile(noteFile);
    }


    //上传游记背景音乐 到本地服务器
    @RequestMapping(value = "/uploadMusic")
    public void uploadMusic(@RequestParam(required = false) MultipartFile music, String userId, HttpServletResponse response, HttpServletRequest request) {
        JSONObject jo = UploadUtils.uploadFile(music, "NoteMusic",request);
        util(jo,userId,response);
        ResponseUtils.renderJson(response, jo.toString());
    }

    //上传语音包
    @RequestMapping(value = "/uploadvoice", method = RequestMethod.POST)
    public @ResponseBody String uploadVoice(@RequestParam(required = false) MultipartFile voice, HttpServletRequest request) {
        JSONObject jo = UploadUtils.uploadFile(voice, "Voices",request);
        jo.remove("url");//不需要url，只需要返回path，即本地地址，客户端在前面加上服务器ip和端口号就能访问了
        //这种路径：upload/Voices/1019c2e2-6d09-40f3-bd65-13294c77df4b.mp3
        String filePath = jo.getString("path");
        String fileName = voice.getOriginalFilename();
        //"."的位置
        int index = fileName.lastIndexOf('.');
        if (index != -1) {
            fileName = fileName.substring(0,index);
        }
        voiceService.insert(fileName,filePath);
        return jo.toString();
    }

}
