package com.vi.server.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 分页类
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageDto<T> {
    // 当前页码
    int page;
    // 每页条数
    int pageSize;
    // 总记录数
    long total;
    // 结果集
    List<T> list;
}
