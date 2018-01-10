package com.yss.yunsoso.domain;

import org.apache.solr.client.solrj.beans.Field;

import java.util.Date;

public class YunBean {
    @Field("id")
    private String id;

    @Field("yun_fileName")
    private String fileName;

    @Field("yun_url")
    private String url;

    @Field("yun_format")
    private String format;

    @Field("yun_size")
    private String size;

    @Field("yun_author")
    private String author;

    @Field("yun_uploadDate")
    private Date uploadDate;

    @Field("yun_failureDate")
    private Date failureDate;

    @Field("yun_keyWord")
    private String keyWord;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format == null ? null : format.trim();
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size == null ? null : size.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Date getFailureDate() {
        return failureDate;
    }

    public void setFailureDate(Date failureDate) {
        this.failureDate = failureDate;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord == null ? null : keyWord.trim();
    }
}