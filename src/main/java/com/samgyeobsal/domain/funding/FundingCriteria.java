package com.samgyeobsal.domain.funding;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class FundingCriteria {

    private String type;

    private String place;

    private String sort;

    private int page;
}
