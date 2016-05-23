package com.shakool.controller.note;

import com.shakool.common.ResponseUtils;
import com.shakool.pojo.JourneyNote;
import com.shakool.pojo.NoteFile;
import com.shakool.query.JourneyNoteQuery;
import com.shakool.service.JourneyNoteService;
import com.shakool.service.NoteFileService;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 游记模块
 * Created by nibnait on 5/19/16.
 */

@Controller
@RequestMapping("/note")
public class JourneyNoteController {

    @Autowired
    private JourneyNoteService journeyNoteService;
    @Autowired
    private NoteFileService noteFileService;

    /**
     * 保存游记
     * @param journeyNote
     * @param response
     */
    @RequestMapping(value = "/addJourneyNote")
    public void addJourneyNote(JourneyNote journeyNote,HttpServletResponse response) {
        JSONObject jo = new JSONObject();

        if (journeyNote.getUserId()==null){
            jo.put("encode","1");
            jo.put("msg","作者不能为空");
            ResponseUtils.renderJson(response,jo.toString());
            return;
        }
        if (!StringUtils.isNotBlank(journeyNote.getTitle())){
            jo.put("encode","1");
            jo.put("msg","标题不能为空");
            ResponseUtils.renderJson(response,jo.toString());
            return;
        }
        if (!StringUtils.isNotBlank(journeyNote.getContent())){
            jo.put("encode","1");
            jo.put("msg","正文不能为空");
            ResponseUtils.renderJson(response,jo.toString());
            return;
        }

        String noteId = UUID.randomUUID().toString();
        journeyNote.setNoteId(noteId);
        journeyNote.setUpdateTime(new Date());
        journeyNoteService.addJourneyNote(journeyNote);

/*
        for(NoteFile noteFile : noteFiles){
            String url = noteFile.getUrl();
            String noteFileId = url.substring(url.lastIndexOf("/"),url.lastIndexOf("."));
            noteFile.setFileId(noteFileId);
            noteFile.setNoteId(noteId);
            noteFile.setUploadTime(new Date());
            noteFileService.addNoteFile(noteFile);
        }
*/

        jo.put("encode","0");
        jo.put("msg","保存成功");
        ResponseUtils.renderJson(response,jo.toString());
    }


    /**
     * 查询游记
     * @param userId
     * @param pageNo
     * @param pageSize
     * @param response
     */

    @RequestMapping(value = "/getJourneyNote")
    public void getJourneyNote(Integer userId,Integer pageNo, Integer pageSize,HttpServletResponse response){
        JSONObject jo = new JSONObject();
        if (userId==null){
            jo.put("encode","1");
            jo.put("msg","用户名为空");
            ResponseUtils.renderJson(response,jo.toString());
            return;
        }

        JourneyNoteQuery journeyNoteQuery = new JourneyNoteQuery();
        if (pageNo!=null){
            journeyNoteQuery.setPageNo(pageNo);
        }
        if (pageSize!=null){
            journeyNoteQuery.setPageSize(pageSize);
        }
        journeyNoteQuery.setUserId(userId);         //where条件
        journeyNoteQuery.orderbyUpdateTime(false);  //按更新时间 降序
        List<JourneyNote> journeyNotes = journeyNoteService.getJourneyNoteListWithPage(journeyNoteQuery);
        JSONArray array = new JSONArray(journeyNotes);
        int totalRows = journeyNoteService.getJourneyNoteListCount(journeyNoteQuery);

        jo.put("encode","0");
        jo.put("msg","成功");
        jo.put("pageNo",journeyNoteQuery.getPageNo());
        jo.put("pageSize",journeyNoteQuery.getPageSize());
        jo.put("totalRows",totalRows);
        jo.put("userId",userId);
        jo.put("journeyNotes",array);
        ResponseUtils.renderJson(response,jo.toString());
    }


    /**
     * 更新游记
     * @param journeyNote
     * @param response
     */
    @RequestMapping(value = "/updateJourneyNote")
    public void updateJourneyNote(JourneyNote journeyNote, HttpServletResponse response) {

        JSONObject jo = new JSONObject();

        if (journeyNote.getNoteId()==null){
            jo.put("encode","1");
            jo.put("msg","游记Id为空");
            ResponseUtils.renderJson(response,jo.toString());
            return;
        }

        //-------------只是因为 userId为int,所以如果不重新设置一下userId，更新之后的journeyNote.userId会变为0---------------
        JourneyNoteQuery journeyNoteQuery = new JourneyNoteQuery();
        journeyNoteQuery.setNoteId(journeyNote.getNoteId());
        List<JourneyNote> notes = journeyNoteService.getJourneyNoteListWithPage(journeyNoteQuery);
        JourneyNote note = notes.get(0);
        journeyNote.setUserId(note.getUserId());
        journeyNote.setHuman(note.getHuman());
        //---------------------------------



//        noteFileService.deleteByNoteId(journeyNote.getNoteId());
        journeyNoteService.updateJourneyNoteByKey(journeyNote);

/*        for (NoteFile noteFile : noteFiles){
            String url = noteFile.getUrl();
            String noteFileId = url.substring(url.lastIndexOf("/"), url.lastIndexOf("."));
            noteFile.setFileId(noteFileId);
            noteFile.setUploadTime(new Date());
            noteFile.setNoteId(journeyNote.getNoteId());
            noteFileService.addNoteFile(noteFile);
        }*/

        jo.put("encode","0");
        jo.put("msg","更新成功");
        ResponseUtils.renderJson(response,jo.toString());
    }


    /**
     * 根据主键删除
     * @param noteId
     * @param response
     */
    @RequestMapping(value = "/deleteJourneyNote")
    public void deleteJourneyNote(String noteId, HttpServletResponse response){
        JSONObject jo = new JSONObject();
        if (noteId==null){
            jo.put("encode","1");
            jo.put("msg","noteId为空");
            ResponseUtils.renderJson(response,jo.toString());
            return;
        }
        journeyNoteService.deleteByKey(noteId);

        jo.put("encode","0");
        jo.put("msg","删除成功");
        ResponseUtils.renderJson(response,jo.toString());
    }

    /**
     * 根据主键批量删除
     * @param noteIds
     * @param response
     */
    @RequestMapping(value = "/deleteJourneyNotes")
    public void deleteJourneyNotes(String[] noteIds, HttpServletResponse response){
        JSONObject jo = new JSONObject();
        if (noteIds!=null && noteIds.length>0){
            List<String> idList = new ArrayList<String>();
            for (int i=0;i<noteIds.length;i++){
                idList.add(noteIds[i]);
            }
            journeyNoteService.deleteByKeys(idList);
            jo.put("encode","0");
            jo.put("msg","删除成功");
            ResponseUtils.renderJson(response,jo.toString());
        }else {
            jo.put("encode","1");
            jo.put("msg","noteIds为空");
            ResponseUtils.renderJson(response,jo.toString());
        }
    }

}