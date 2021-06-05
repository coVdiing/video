package com.vi.server.vo;

import lombok.Data;

/**
 * @Author: vi
 * @Date: 2021-05-30 12:04
 * @Version: 1.0
 * @Description:
 */
@Data
public class UserVo {
    private String id;
    private String loginName;
    private String name;
    private String loginToken;
}
