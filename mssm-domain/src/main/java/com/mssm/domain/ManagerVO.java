package com.mssm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 携带头像信息的接收前端请求信息类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ManagerVO extends Manager{
    private String originalFilename;
    private String newFilename;
    private String newFilepath;
}
