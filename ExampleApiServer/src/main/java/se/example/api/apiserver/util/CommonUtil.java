package se.example.api.apiserver.util;

import org.springframework.http.ResponseEntity;

import se.example.api.apiserver.model.ApiResponse;

public class CommonUtil {
	public static  ResponseEntity<Object> buildResponseEntity(ApiResponse api) {
		return new ResponseEntity<>(api, api.getStatus());
	}

}
