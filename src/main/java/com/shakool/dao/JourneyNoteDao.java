package com.shakool.dao;

import com.shakool.pojo.JourneyNote;
import com.shakool.query.JourneyNoteQuery;

import java.util.List;

/**
 * Created by nibnait on 5/19/16.
 */
public interface JourneyNoteDao {
    /**添加 游记*/
    public void addJourneyNote(JourneyNote journeyNote);

    /**分页查询游记*/
    public List<JourneyNote> getJourneyNoteListWithPage(JourneyNoteQuery journeyNoteQuery);


    /**get TotalRows*/
    public int getJourneyNoteListCount(JourneyNoteQuery journeyNoteQuery);


    /**更新游记*/
    public void updateJourneyNoteByKey(JourneyNote journeyNote);

    /**根据主键删除*/
    public void deleteByKey(String id);

    /**根据主键批量删除*/
    public void deleteByKeys(List<String> ids);

}
