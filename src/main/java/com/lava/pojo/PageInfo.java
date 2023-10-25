package com.lava.pojo;

import lombok.Data;

@Data
public class PageInfo {
    private String keyWords;
    private Integer type;
    private Integer pageNum = 1;
    private Integer pageSize = 10;
}
