package com.mssm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * 分类与分页数据的封装
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ListPageInfo {
    private long total;
    private Integer pagenum;
    private Integer pagesize;
    private List list;
}
