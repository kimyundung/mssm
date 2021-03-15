package com.mssm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 携带token值的管理员返回对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ManagerR extends Manager{
    private String token;
}
