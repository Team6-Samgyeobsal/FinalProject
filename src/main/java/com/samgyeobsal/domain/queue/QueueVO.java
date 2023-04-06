package com.samgyeobsal.domain.queue;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
@Setter
public class QueueVO {
    private String mname;
    private String qid;
    private String fid;
    private Date qdate;
    private String fstore_name;
    private String oid;
}
