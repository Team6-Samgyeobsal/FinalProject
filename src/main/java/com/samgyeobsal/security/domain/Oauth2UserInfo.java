package com.samgyeobsal.security.domain;

import java.util.Map;

public interface Oauth2UserInfo {

    Map<String, Object> getAttributes();

    String getProviderId();

    String getProvider();

    String getEmail();

    String getName();
}
