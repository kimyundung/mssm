package com.mssm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class File {
    private Integer id;
    private Integer gid;
    private String originalFilename;
    private String newFilename;
    private String newFilepath;
    private String fastDFSFileId;
    private String fastDFSPath;
    private Integer topPic;
}
