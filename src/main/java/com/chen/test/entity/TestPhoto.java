package com.chen.test.entity;

import java.util.Objects;

public class TestPhoto {
    private String id;
    private String zkbh;
    private String createTime;

    public TestPhoto() {
    }

    public TestPhoto(String id, String zkbh, String createTime) {
        this.id = id;
        this.zkbh = zkbh;
        this.createTime = createTime;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getZkbh() {
        return zkbh;
    }

    public void setZkbh(String zkbh) {
        this.zkbh = zkbh;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TestPhoto{" +
                "id='" + id + '\'' +
                ", zkbh='" + zkbh + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestPhoto)) return false;
        TestPhoto testPhoto = (TestPhoto) o;
        return Objects.equals(id, testPhoto.id) &&
                Objects.equals(zkbh, testPhoto.zkbh) &&
                Objects.equals(createTime, testPhoto.createTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, zkbh, createTime);
    }
}
