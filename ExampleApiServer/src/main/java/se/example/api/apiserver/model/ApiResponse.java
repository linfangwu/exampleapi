package se.example.api.apiserver.model;

import org.springframework.http.HttpStatus;

public class ApiResponse {

	private HttpStatus status;
	private String message;
	private Integer clientAnswer;
	private Integer serverAnswer;
	private String result;

	public  ApiResponse() {
	}

	public ApiResponse(HttpStatus status) {
		this();
		this.status = status;
	}
	
	
	public ApiResponse(HttpStatus status, String message, String result) {
		this();
		this.status = status;
		this.message = message;
		this.result = result ;
	} 


	public ApiResponse(HttpStatus status, Throwable ex) {
		this();
		this.status = status;
		this.message = "Unexpected error";
		this.result = ex.getLocalizedMessage();
	}

	public ApiResponse(HttpStatus status, String message, Throwable ex) {
		this();
		this.status = status;
		this.message = message;
		this.result = ex.getLocalizedMessage();
	}
	
	public ApiResponse(HttpStatus status, String message, int clientAnswer, int serverAnswer, String result ) {
		this();
		this.status = status;
		this.message = message;
		this.clientAnswer = clientAnswer;
		this.serverAnswer = serverAnswer;
		this.result = result ;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Integer getClientAnswer() {
		return clientAnswer;
	}

	public void setClientAnswer(Integer clientAnswer) {
		this.clientAnswer = clientAnswer;
	}

	public Integer getServerAnswer() {
		return serverAnswer;
	}

	public void setServerAnswer(Integer serverAnswer) {
		this.serverAnswer = serverAnswer;
	}
	
	

}

