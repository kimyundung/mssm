package com.mssm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 响应数据封装类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseResult {
    private Object data;
    private Meta meta;

}
