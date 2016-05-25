package com.shakool.pojo;

/**
 * Created by geekgao on 16-5-24.
 * 保存在服务器上可以被下载的文件
 */
public class DownFile {
    private String name;//名称
    private String url;//下载路径

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
