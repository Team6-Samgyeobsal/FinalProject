package com.samgyeobsal.type;

import lombok.Getter;

@Getter
public enum MyLocale {
    KO("ko", "ko"),
    JA("ja","ja"),
    EN("en","en"),
    CH("zh", "zh-CN");

    private final String acceptLang;
    private final String param;

    MyLocale(String acceptLang, String param) {
        this.acceptLang = acceptLang;
        this.param = param;
    }
}
