package com.dnd.common;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import com.dnd.common.dto.ApiResult;
import com.dnd.common.error.BasterdzException;
import com.dnd.common.error.NotFoundException;
import com.dnd.common.error.UnauthorizedException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	private ResponseEntity<?> newResponse(ErrorCode errorCode) {
		return new ResponseEntity<>(ApiResult.error(errorCode), errorCode.getStatus());
	}

	private ResponseEntity<?> newResponseWithMessage(ErrorCode errorCode, String message) {
		return new ResponseEntity<>(ApiResult.error(errorCode, message), errorCode.getStatus());
	}

	@ExceptionHandler({
		IllegalStateException.class, IllegalArgumentException.class,
		TypeMismatchException.class, HttpMessageNotReadableException.class,
		MissingServletRequestParameterException.class, MultipartException.class,
	})
	public ResponseEntity<?> handleBadRequestException(Exception e) {
		log.debug("Bad request exception occurred: {}", e.getMessage(), e);
		return newResponse(ErrorCode.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleRequestValidException(MethodArgumentNotValidException e) {
		StringBuilder builder = new StringBuilder();
		e.getBindingResult().getFieldErrors().forEach(fieldError ->
			builder.append(String.format("[%s](은)는 %s|",
				fieldError.getField(),
				fieldError.getDefaultMessage())));
		return newResponseWithMessage(ErrorCode.METHOD_ARGUMENT_NOT_VALID, builder.toString());
	}

	@ExceptionHandler(HttpMediaTypeException.class)
	public ResponseEntity<?> handleHttpMediaTypeException(Exception e) {
		return newResponse(ErrorCode.UNSUPPORTED_MEDIA_TYPE);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<?> handleMethodNotAllowedException(Exception e) {
		return newResponse(ErrorCode.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(BasterdzException.class)
	public ResponseEntity<?> handleBasterdzException(BasterdzException e) {
		if (e instanceof UnauthorizedException)
			return newResponse(ErrorCode.UNAUTHORIZED);
		if (e instanceof NotFoundException)
			return newResponse(ErrorCode.NOT_FOUND);
		return newResponse(ErrorCode.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({Exception.class, RuntimeException.class})
	public ResponseEntity<?> handleException(Exception e) {
		log.error("Unexpected exception occurred: {}", e.getMessage(), e);
		return newResponse(ErrorCode.INTERNAL_SERVER_ERROR);
	}
}
