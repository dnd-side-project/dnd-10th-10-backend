package com.dnd.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dnd.common.dto.ApiResult;
import com.dnd.common.error.BasterdzException;
import com.dnd.common.error.NotFoundException;

import jakarta.validation.UnexpectedTypeException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({
		IllegalArgumentException.class, IllegalStateException.class,
		HttpMessageNotReadableException.class, UnexpectedTypeException.class
	})
	public ApiResult<?> handleBadRequestException(Exception e) {
		log.debug("Bad request exception occurred: {}", e.getMessage(), e);
		return ApiResult.error(HttpStatus.BAD_REQUEST, e);
	}

	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ApiResult<?> handleHttpMediaTypeException(Exception e) {
		return ApiResult.error(HttpStatus.UNSUPPORTED_MEDIA_TYPE, e);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ApiResult<?> handleRequestValidException(MethodArgumentNotValidException e) {
		StringBuilder builder = new StringBuilder();
		e.getBindingResult().getFieldErrors().forEach(fieldError ->
			builder.append(String.format("[%s](은)는 %s, 입력된 값: %s|",
				fieldError.getField(),
				fieldError.getDefaultMessage(),
				fieldError.getRejectedValue())));
		return ApiResult.error(HttpStatus.BAD_REQUEST, "COMMON-02", builder.toString());
	}

	@ExceptionHandler(BasterdzException.class)
	public ApiResult<?> handleBasterdzException(BasterdzException e) {
		if (e instanceof NotFoundException)
			return ApiResult.error(HttpStatus.NOT_FOUND, e.getCode(), e.getMessage());
		return ApiResult.error(HttpStatus.INTERNAL_SERVER_ERROR, e);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({Exception.class, RuntimeException.class})
	public ApiResult<?> handleException(Exception e) {
		log.error("Unexpected exception occurred: {}", e.getMessage(), e);
		return ApiResult.error(HttpStatus.INTERNAL_SERVER_ERROR, e);
	}
}
