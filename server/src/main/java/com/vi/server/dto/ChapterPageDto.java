package com.vi.server.dto;

import lombok.Data;


public class ChapterPageDto extends PageDto {
    private String courseId;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "ChapterPageDto{" +
                "courseId='" + courseId + '\'' +
                ", page=" + page +
                ", pageSize=" + pageSize +
                ", total=" + total +
                ", list=" + list +
                '}';
    }
}
