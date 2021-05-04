package com.mssm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsStock {
    private Integer id;
    private Integer gid;
    private Integer cid;
    private Integer sid;
    private String gname;
    private String cname;
    private String sname;
    private Integer stock;
}
