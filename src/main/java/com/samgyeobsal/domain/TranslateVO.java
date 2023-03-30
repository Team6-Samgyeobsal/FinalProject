package com.samgyeobsal.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TranslateVO {
    private String text;
    private String source;
    private String target;
}
