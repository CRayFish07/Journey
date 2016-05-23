package com.shakool.controller.file;

import com.shakool.common.ResponseUtils;
import com.shakool.service.NoteFileService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nibnait on 5/21/16.
 */

@Controller
@RequestMapping("/file")
public class NoteFileController {

    @Autowired
    private NoteFileService noteFileService;

    /**
     * 根据主键删除
     * @param fileId
     * @param response
     */
    @RequestMapping(value = "/deleteNoteFile")
    public void deleteJourneyNote(String fileId, HttpServletResponse response){
        JSONObject jo = new JSONObject();
        if (fileId==null){
            jo.put("encode","1");
            jo.put("encode","fileId为空");
            ResponseUtils.renderJson(response,jo.toString());
            return;
        }
        noteFileService.deleteByKey(fileId);

        jo.put("encode","0");
        jo.put("encode","删除成功");
        ResponseUtils.renderJson(response,jo.toString());
    }

    /**
     * 根据主键批量删除
     * @param fileIds
     * @param response
     */
    @RequestMapping(value = "/deleteNoteFiles")
    public void deleteJourneyNotes(String[] fileIds, HttpServletResponse response){
        JSONObject jo = new JSONObject();
        if (fileIds!=null && fileIds.length>0){
            List<String> idList = new ArrayList<String>();
            for (int i=0;i<fileIds.length;i++){
                idList.add(fileIds[i]);
            }
            noteFileService.deleteByKeys(idList);
            jo.put("encode","0");
            jo.put("encode","删除成功");
            ResponseUtils.renderJson(response,jo.toString());
        }else {
            jo.put("encode","1");
            jo.put("encode","fileIds为空");
            ResponseUtils.renderJson(response,jo.toString());
        }
    }


}
