package com.shakool.serviceimp;

import com.shakool.dao.JourneyNoteDao;
import com.shakool.pojo.JourneyNote;
import com.shakool.pojo.NoteFile;
import com.shakool.query.JourneyNoteQuery;
import com.shakool.query.NoteFileQuery;
import com.shakool.service.JourneyNoteService;
import com.shakool.service.NoteFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;

/**
 * Created by nibnait on 5/19/16.
 */
@Service
public class JourneyNoteServiceImp implements JourneyNoteService {


    @Autowired
    JourneyNoteDao journeyNoteDao;
    @Resource
    private NoteFileService noteFileService;

    public void addJourneyNote(JourneyNote journeyNote) {
        journeyNoteDao.addJourneyNote(journeyNote);
    }

    public List<JourneyNote> getJourneyNoteListWithPage(JourneyNoteQuery journeyNoteQuery) {

        List<JourneyNote> journeyNotes = journeyNoteDao.getJourneyNoteListWithPage(journeyNoteQuery);

        return journeyNotes;
    }

    public int getJourneyNoteListCount(JourneyNoteQuery journeyNoteQuery) {
        return journeyNoteDao.getJourneyNoteListCount(journeyNoteQuery);
    }

    public void updateJourneyNoteByKey(JourneyNote journeyNote) {
        journeyNoteDao.updateJourneyNoteByKey(journeyNote);
    }

    public void deleteByKey(String id) {
        noteFileService.deleteByNoteId(id);
        journeyNoteDao.deleteByKey(id);
    }

    public void deleteByKeys(List<String> ids) {

/*      【不需要删除游记对应的服务器文件了。。(数据库中已经没有noteId这一选项了 :)】
        for (String id:ids){
            noteFileService.deleteByNoteId(id);
        }
*/

        journeyNoteDao.deleteByKeys(ids);
    }


}
