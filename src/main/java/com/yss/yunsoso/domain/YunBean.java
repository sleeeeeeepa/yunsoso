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
    private Long size;

    @Field("yun_sizeFormat")
    private String sizeFormat;

    @Field("yun_author")
    private String author;

    @Field("yun_keyWord")
    private String keyWord;
    @Field("yun_lockUrl")
    private String lockUrl;

    private Date uploadDate;

    private Date failureDate;

    private Date createDate;

    private String recycle;

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

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size == null ? null : size;
    }

    public String getSizeFormat() {
        return sizeFormat;
    }

    public void setSizeFormat(String sizeFormat) {
        this.sizeFormat = sizeFormat == null ? null : sizeFormat.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord == null ? null : keyWord.trim();
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLockUrl() {
        return lockUrl;
    }

    public void setLockUrl(String lockUrl) {
        this.lockUrl = lockUrl == null ? null : lockUrl.trim();
    }

    public String getRecycle() {
        return recycle;
    }

    public void setRecycle(String recycle) {
        this.recycle = recycle == null ? null : recycle.trim();
    }
}