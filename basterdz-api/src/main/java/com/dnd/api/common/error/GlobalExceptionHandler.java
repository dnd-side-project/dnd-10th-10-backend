package com.dnd.api.common.error;

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

import com.dnd.api.common.dto.ApiResult;
import com.dnd.common.exception.BadRequestException;
import com.dnd.common.exception.BasterdzException;
import com.dnd.common.exception.ErrorCode;
import com.dnd.common.exception.NotFoundException;
import com.dnd.common.exception.UnauthorizedException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	private ResponseEntity<?> newResponse(ErrorCode errorCode, HttpStatus httpStatus) {
		return new ResponseEntity<>(ApiResult.error(errorCode), httpStatus);
	}

	private ResponseEntity<?> newResponseWithMessage(ErrorCode errorCode, String message, HttpStatus httpStatus) {
		return new ResponseEntity<>(ApiResult.error(errorCode, message), httpStatus);
	}

	@ExceptionHandler({
		IllegalStateException.class, IllegalArgumentException.class,
		TypeMismatchException.class, HttpMessageNotReadableException.class,
		MissingServletRequestParameterException.class, MultipartException.class,
	})
	public ResponseEntity<?> handleBadRequestException(Exception e) {
		log.info("Bad request exception occurred: {}", e.getMessage(), e);
		return newResponse(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleRequestValidException(MethodArgumentNotValidException e) {
		StringBuilder builder = new StringBuilder();
		e.getBindingResult().getFieldErrors().forEach(fieldError ->
			builder.append(String.format("[%s](은)는 %s|",
				fieldError.getField(),
				fieldError.getDefaultMessage())));
		return newResponseWithMessage(ErrorCode.METHOD_ARGUMENT_NOT_VALID, builder.toString(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMediaTypeException.class)
	public ResponseEntity<?> handleHttpMediaTypeException(Exception e) {
		return newResponse(ErrorCode.UNSUPPORTED_MEDIA_TYPE, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<?> handleMethodNotAllowedException(Exception e) {
		return newResponse(ErrorCode.METHOD_NOT_ALLOWED, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(BasterdzException.class)
	public ResponseEntity<?> handleBasterdzException(BasterdzException e) {
		if (e instanceof UnauthorizedException)
			return newResponse(e.getErrorCode(), HttpStatus.UNAUTHORIZED);
		if (e instanceof NotFoundException)
			return newResponse(e.getErrorCode(), HttpStatus.NOT_FOUND);
		if (e instanceof BadRequestException)
			return newResponse(e.getErrorCode(), HttpStatus.BAD_REQUEST);
		return newResponse(ErrorCode.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({Exception.class, RuntimeException.class})
	public ResponseEntity<?> handleException(Exception e) {
		log.error("Unexpected exception occurred: {}", e.getMessage(), e);
		return newResponse(ErrorCode.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
