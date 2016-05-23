package com.shakool.query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nibnait on 5/20/16.
 */
public class JourneyNoteQuery extends BaseQuery{


    /**Where条件-------------begin---------------*/
    private String noteId;
    private Integer userId;

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    /**Where条件-------------end---------------*/


    /**
     * ==============================批量查询时的Order条件顺序设置==========================
     * ========
     **/
    public class OrderField {
        public OrderField(String fieldName, String order) {
            super();
            this.fieldName = fieldName;
            this.order = order;
        }
        private String fieldName;
        private String order;

        public String getFieldName() {
            return fieldName;
        }
        public OrderField setFieldName(String fieldName) {
            this.fieldName = fieldName;
            return this;
        }
        public String getOrder() {
            return order;
        }
        public OrderField setOrder(String order) {
            this.order = order;
            return this;
        }
    }
    /**
     * ==============================批量查询时的Order条件顺序设置==========================
     * ========
     **/
    /** 排序列表字段 **/
    private List<OrderField> orderFields = new ArrayList<OrderField>();


    /**
     * 设置排序按属性：updateTime
     *
     * @param isAsc
     *            是否升序，否则为降序
     */
    public JourneyNoteQuery orderbyUpdateTime(boolean isAsc) {
        orderFields.add(new OrderField("updateTime", isAsc ? "ASC" : "DESC"));
        return this;
    }




}
