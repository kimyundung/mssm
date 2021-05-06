package com.mssm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsCategory {
    private Integer id;
    private Integer gid;
    private String gname;
    private Integer cid1;
    private Integer cid2;
    private Integer cid3;
}
