package com.mssm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 参数分装类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QueryInfo {
    private Integer level;
    private String query;
    private Integer pagenum;
    private Integer pagesize;
}
