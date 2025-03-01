package com.JeongCommunity.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
// application.yml에서 가져오기.
@ConfigurationProperties("jwt")
public class JwtProperties {
    private String issuer;
    private String secretKey;
}
