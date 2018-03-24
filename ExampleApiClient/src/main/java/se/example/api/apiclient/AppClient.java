package se.example.api.apiclient;

/**
 * 
 * @author lwu
 *
 */
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import se.example.api.apiclient.errorhandler.MyErrorHandler;
import se.example.api.apiclient.model.ApiRequest;
import se.example.api.apiclient.model.ApiResponse;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


public class AppClient 
{
	private static final MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    private static final ObjectMapper mapper = new ObjectMapper();
    private static RestTemplate restTemplate = new RestTemplate() ;
    
    private static String rootPath ="http://localhost:9080" ;
    private static String requestQuestionUrl = "/api/generateRandomNumbers" ;
    private static String isCorrectAnswerUrl = "/api/isCorrectAnswer" ;
    
    
    public static void main( String[] args ) throws JsonParseException, IOException
    {
    	//set up header and mapper
    	setUp();
    	//get a list of numbers from server
    	List<Integer> listOfRandomNumber = getAListOfRandomNumberFromServer();

    	Integer clientSum = null;
    	Scanner sc =new Scanner(System.in);
    	System.out.println("Enter your Answer, answer must be an integer: ");
    	if (sc.hasNextInt()) {
    		clientSum = sc.nextInt();
    	}
    	sc.close();

    	getIsCorrectFromServer(listOfRandomNumber,  clientSum);

    }
    
    public static void setUp() {
    	 if (headers.get("Content-Type") == null) {
             headers.add("Content-Type", "application/json");
         }
         mapper.enable(SerializationFeature.INDENT_OUTPUT);
         mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
         mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
         mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
         mapper.setSerializationInclusion(Include.NON_NULL);
         
         restTemplate.setErrorHandler(new MyErrorHandler());
    }
    
    public static List<Integer> getAListOfRandomNumberFromServer() throws JsonParseException, IOException {
    	HttpEntity<Object> requestQuestion = new HttpEntity<Object>( headers);
    	ResponseEntity<String> result = restTemplate.exchange(rootPath+requestQuestionUrl,
         		  HttpMethod.GET, 
         		  requestQuestion, 
         		  String.class);
         if (null!= result && !result.getStatusCode().is2xxSuccessful()) {
        	 System.out.println("Error retrive random numbers from server");
        	 return null ;
         }
         else {
        	 ApiResponse request = mapper.readValue(result.getBody(), ApiResponse.class);
        	 System.out.println (request.getMessage() + " " + request.getResult() );
        	
        	 return Arrays.asList( request.getResult().split(","))
        			 .stream()
        			 .mapToInt( str -> Integer.parseInt(str.trim())) 
        			 .boxed()
        			 .collect(Collectors.toList()) ;
        			 
         }
    }
    
    public static void getIsCorrectFromServer(List<Integer> randomList, Integer clientSum) throws JsonParseException, IOException {
    	ApiRequest request = new ApiRequest() ;
    	request.setInputList(randomList);
    	request.setInputSum(clientSum);
    	
    	HttpEntity<Object> requestQuestion = new HttpEntity<Object>( request, headers);
    	ResponseEntity<String> result = restTemplate.exchange(rootPath+isCorrectAnswerUrl,
         		  HttpMethod.POST, 
         		  requestQuestion, 
         		  String.class);
    	
    	if (null == result) {
    		 System.out.println("Error retrive result from server");
    		 return ;
    	}
    	 ApiResponse requestObject = mapper.readValue(result.getBody(), ApiResponse.class);
    	 System.out.println(result.getStatusCode()) ;
    	 System.out.println(result.getBody()) ;
    	 
    }
    
    
   
}
