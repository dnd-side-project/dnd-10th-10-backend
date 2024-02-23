package com.dnd.api.auth;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties("jwt")
public class JwtTokenProperties {

	private String issuer;

	private String secretKey;

	private int expirySeconds;
}
