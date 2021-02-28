package com.alsoft27.tdd.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6057545596714671172L;

}
