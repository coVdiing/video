package com.vi.server.dto;

public class SectionPageDto extends PageDto {
    private String chapterId;
    private String courseId;

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "SectionPageDto{" +
                "chapterId='" + chapterId + '\'' +
                ", courseId='" + courseId + '\'' +
                ", page=" + page +
                ", pageSize=" + pageSize +
                ", total=" + total +
                ", list=" + list +
                '}';
    }
}
