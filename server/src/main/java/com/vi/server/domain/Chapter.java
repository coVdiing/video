package com.vi.server.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value="Subject对象", description="课程科目")
public class Chapter {
    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("课程id")
    private String courseId;

    @ApiModelProperty("章节名")
    private String name;

    @ApiModelProperty(value = "创建时间",example = "2021-04-24 14:33:00")
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间",example = "2021-04-24 14:33:00")
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private Date gmtModified;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", courseId=").append(courseId);
        sb.append(", name=").append(name);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append("]");
        return sb.toString();
    }
}