package com.samgyeobsal.domain.fame;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FameVO {

    private String fid;
    private String fstore_name;
    private String ftitle;
    private int totalprice;
    private long totalemail;
    private String fthumb;
    private double fstore_score;
}
