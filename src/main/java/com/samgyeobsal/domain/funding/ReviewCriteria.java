package com.samgyeobsal.domain.funding;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewCriteria {
    private String fid;
    private String sort;
    private String type;
    private int page;
}
