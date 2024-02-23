package com.dnd.api.auth.apple;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties("oauth.apple")
public class AppleProperties {

	private String iss;

	private String clientId;

	private String nonce;

}
