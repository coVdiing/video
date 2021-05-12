package com.vi.server.vo;

import com.vi.server.domain.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryVo extends Category {
    List<CategoryVo> children = new ArrayList<>();

    public List<CategoryVo> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryVo> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "CategoryVo{" +
                "children=" + children +
                '}';
    }
}
