package com.dnd.common;

import jakarta.validation.constraints.NotBlank;

public record SampleRequestDto(
	@NotBlank(message = "빈칸이 올 수 없습니다")
	String id
){

}
