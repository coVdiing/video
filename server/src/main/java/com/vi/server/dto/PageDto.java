package com.vi.server.dto;


import lombok.*;

import java.util.List;

/**
 * 分页类
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
