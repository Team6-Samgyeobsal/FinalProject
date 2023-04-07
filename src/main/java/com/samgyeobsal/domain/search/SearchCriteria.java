package com.samgyeobsal.domain.search;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchCriteria {
    private String fstatus;
    private String keyword;
    private int page;
}
