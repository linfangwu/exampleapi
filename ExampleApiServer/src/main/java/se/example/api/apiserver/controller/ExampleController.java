package se.example.api.apiserver.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import se.example.api.apiserver.model.ApiRequest;
import se.example.api.apiserver.model.ApiResponse;
import se.example.api.apiserver.service.ExampleService;
import static se.example.api.apiserver.util.CommonUtil.buildResponseEntity;

/**
 * There are 2 APIs in this controller
 * 
 * @author lwu
 *
 */
@RestController
@CrossOrigin(origins = "*") // solved cors problem
public class ExampleController {
	@Autowired
	ExampleService service ;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	{
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
		mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
		mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
	}
	
	/**
	 * get random numbers
	 * @return ResponseEntity
	 */
	@RequestMapping(value ="/api/generateRandomNumbers", method = RequestMethod.GET)
	ResponseEntity<Object> generateRandomNumbers() {
		List<Integer> resultList = service.generateRandomNumbers() ;
		String result = resultList.stream()
				.map(number -> String.valueOf(number))
				.collect(Collectors.joining(", "));
		ApiResponse api = new ApiResponse(HttpStatus.OK, "Please sum the numbers : ",  result) ;
		return buildResponseEntity(api) ;
	}
	
	/**
	 * 
	 * @param apiRequest
	 * @return ResponseEntity<Object>
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@RequestMapping(value ="/api/isCorrectAnswer", method = RequestMethod.POST)
	ResponseEntity<Object> isCorrectAnswer(@RequestBody @Valid String apiRequest) 
			throws JsonParseException, JsonMappingException, IOException {
		if (apiRequest ==null ) {
			throw new IllegalArgumentException("Missing valid request");
		}
		ApiRequest request = mapper.readValue(apiRequest, ApiRequest.class);
		
		if (request.getInputList()== null || request.getInputList().size() == 0) {
			throw new IllegalArgumentException("Missing input numbers to be summed");
		}
		if (request.getInputSum() == null) {
			throw new IllegalArgumentException("Missing input sum from client");
		}
		
		List<Integer> inputList = request.getInputList() ;
		Integer inputSum = request.getInputSum() ;
		
		String message = "Please sum the numbers: " + inputList.stream()
				.map(number -> String.valueOf(number))
				.collect(Collectors.joining(", "));
		
		int sum = service.sum(inputList, inputSum);
		ApiResponse api ;
		if (sum == inputSum) {
			api = new ApiResponse(HttpStatus.OK, message, inputSum, sum, "Correct Answer" );
		}
		else {
			 api = new ApiResponse(HttpStatus.BAD_REQUEST, message, inputSum, sum, "Wrong Answer" );
		}
		
		return buildResponseEntity(api) ;

	}
	
	
	
}