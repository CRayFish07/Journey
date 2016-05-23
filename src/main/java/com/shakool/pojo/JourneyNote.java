package com.shakool.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 游记表
 * Created by nibnait on 5/19/16.
 */
public class JourneyNote implements Serializable{

    private String noteId;
    private String title;
    private Integer userId;
    private String content;
    private String tag;
    private String category;
    private Date departureDate;
    private String travelDays;
    private int human;
    private String fee;
    private Date updateTime;

    private Set<NoteFile> noteFiles = new HashSet<NoteFile>();

    public Set<NoteFile> getNoteFiles() {
        return noteFiles;
    }

    public void setNoteFiles(Set<NoteFile> noteFiles) {
        this.noteFiles = noteFiles;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public String getTravelDays() {
        return travelDays;
    }

    public void setTravelDays(String travelDays) {
        this.travelDays = travelDays;
    }

    public int getHuman() {
        return human;
    }

    public void setHuman(int human) {
        this.human = human;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "JourneyNote{" +
                "noteId='" + noteId + '\'' +
                ", title='" + title + '\'' +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", tag='" + tag + '\'' +
                ", category='" + category + '\'' +
                ", departureDate=" + departureDate +
                ", travelDays='" + travelDays + '\'' +
                ", human=" + human +
                ", fee='" + fee + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }

    public JourneyNote(){}

    public JourneyNote(String noteId, String title, Integer userId, String content, String tag, String category, Date
            departureDate, String travelDays, int human, String fee, Date updateTime, Set<NoteFile> noteFiles) {
        this.noteId = noteId;
        this.title = title;
        this.userId = userId;
        this.content = content;
        this.tag = tag;
        this.category = category;
        this.departureDate = departureDate;
        this.travelDays = travelDays;
        this.human = human;
        this.fee = fee;
        this.updateTime = updateTime;
        this.noteFiles = noteFiles;
    }
}
