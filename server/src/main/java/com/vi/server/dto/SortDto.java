package com.vi.server.dto;

/**
 * @Author: vi
 * @Date: 2021-05-16 17:24
 * @Version: 1.0
 * @Description: 排序
 */
public class SortDto {
    private Integer oldSort;
    private Integer newSort;

    public Integer getOldSort() {
        return oldSort;
    }

    public void setOldSort(Integer oldSort) {
        this.oldSort = oldSort;
    }

    public Integer getNewSort() {
        return newSort;
    }

    public void setNewSort(Integer newSort) {
        this.newSort = newSort;
    }

    @Override
    public String toString() {
        return "SortDto{" +
                "oldSort=" + oldSort +
                ", newSort=" + newSort +
                '}';
    }

    public SortDto(Integer oldSort, Integer newSort) {
        this.oldSort = oldSort;
        this.newSort = newSort;
    }

    public SortDto() {
    }
}
