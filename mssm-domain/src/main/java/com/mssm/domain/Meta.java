package com.mssm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 状态封装类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Meta {
    private Integer status;
    private String msg;
}
