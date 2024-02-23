package com.dnd.api.auth.apple;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AppleTokenParser {

	public Map<String, String> parseHeaders(String token) {
		try {
			String header = token.split("\\.")[0];
			return new ObjectMapper().readValue(decodeHeader(header), Map.class);
		} catch (JsonProcessingException | ArrayIndexOutOfBoundsException e) {
			throw new IllegalArgumentException("Apple OAuth Identity Token 형식이 올바르지 않습니다.");
		}
	}

	public String decodeHeader(String token) {
		return new String(Base64.getDecoder().decode(token), StandardCharsets.UTF_8);
	}
}
