package com.dnd.common;

import jakarta.validation.constraints.NotBlank;

public record SampleRequestDto(
	@NotBlank(message = "ss")
	String id
){

}
