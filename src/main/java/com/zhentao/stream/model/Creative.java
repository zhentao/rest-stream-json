package com.zhentao.stream.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "creative")
public class Creative {

    private long id;
    private String screenShotLocation;
    private boolean sound;

    private String creativeUrl;
    private String creativeHtml;
    private String largestSwf;
    private String largestImg;
    private String clickThroughUrl;
    private String landingUrl;
    private int height;
    private int width;
    private int loadTime;
    private String md5;
    private Date created;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getScreenShotLocation() {
        return screenShotLocation;
    }

    public void setScreenShotLocation(String path) {
        this.screenShotLocation = path;
    }

    public boolean isSound() {
        return sound;
    }

    public void setSound(boolean sound) {
        this.sound = sound;
    }

    public String getCreativeUrl() {
        return creativeUrl;
    }

    public void setCreativeUrl(String creativeUrl) {
        this.creativeUrl = creativeUrl;
    }

    public String getCreativeHtml() {
        return creativeHtml;
    }

    public void setCreativeHtml(String creativeHtml) {
        this.creativeHtml = creativeHtml;
    }

    public String getLargestSwf() {
        return largestSwf;
    }

    public void setLargestSwf(String largestSwf) {
        this.largestSwf = largestSwf;
    }

    public String getLargestImg() {
        return largestImg;
    }

    public void setLargestImg(String largestImg) {
        this.largestImg = largestImg;
    }

    public String getClickThroughUrl() {
        return clickThroughUrl;
    }

    public void setClickThroughUrl(String clickThroughUrl) {
        this.clickThroughUrl = clickThroughUrl;
    }

    public String getLandingUrl() {
        return landingUrl;
    }

    public void setLandingUrl(String landingUrl) {
        this.landingUrl = landingUrl;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(int loadTime) {
        this.loadTime = loadTime;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Creative() {
    }

}
