package com.shakool.pojo;

import java.io.Serializable;

/**
 * 数据字典
 * Created by nibnait on 5/19/16.
 */
public class Systemddl implements Serializable{

    private Integer seqID;		//主键ID(自增长)
    private String keyword;		//数据类型
    private Integer ddlCode;	//数据项的code
    private String ddlName;		//数据项的value

    public Integer getSeqID() {
        return seqID;
    }

    public void setSeqID(Integer seqID) {
        this.seqID = seqID;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getDdlCode() {
        return ddlCode;
    }

    public void setDdlCode(Integer ddlCode) {
        this.ddlCode = ddlCode;
    }

    public String getDdlName() {
        return ddlName;
    }

    public void setDdlName(String ddlName) {
        this.ddlName = ddlName;
    }
}
