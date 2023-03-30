package com.samgyeobsal.security.domain;

import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;

import java.util.Map;

public class GoogleUserInfo implements Oauth2UserInfo{
    private Map<String, Object> attributes;
    public GoogleUserInfo(Map<String,Object> attributes) {


//        ClientRegistration{
//            registrationId='google',
//                    clientId='~',
//                    clientSecret='~',
//                    clientAuthenticationMethod=org.springframework.security.oauth2.core.ClientAuthenticationMethod@4fcef9d3,
//                    authorizationGrantType=org.springframework.security.oauth2.core.AuthorizationGrantType@5da5e9f3,
//                    redirectUri='{baseUrl}/{action}/oauth2/code/{registrationId}',
//                    scopes=[profile, email],
//            providerDetails=org.springframework.security.oauth2.client.registration.ClientRegistration$ProviderDetails@5b8ec6e5,
//                    clientName='Google'
//        }

        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getProviderId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getProvider() {
        return "google";
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

}
