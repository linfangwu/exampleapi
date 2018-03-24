package test.example.api.apiserver.controller;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import se.example.api.apiserver.App;
import se.example.api.apiserver.model.ApiRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
public class ControllerTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;
	private ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setup() {

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
		mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
		mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
	}

	@Test
	public void generateRandomNumbersTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/api/generateRandomNumbers")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn() ;
		System.out.println(result.getResponse().getContentAsString()) ;

	}

	@Test
	public void isCorrectAnswerTestCorrect() throws Exception {
		Integer[] array= {-10, -24, 14, 15} ;
		List<Integer> inputList = Arrays.asList(array) ;
		int inputSum = -5 ;


		ApiRequest request =  new ApiRequest();
		request.setInputList(inputList);
		request.setInputSum(inputSum);

		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter() ;
		String json = ow.writeValueAsString(request) ;

		MvcResult result  = this.mockMvc.perform(post("/api/isCorrectAnswer")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString()) ;
	}

	@Test
	public void isCorrectAnswerTestCorrectWrong() throws Exception {
		Integer[] array= {-10, -24, 14, 15} ;
		List<Integer> inputList = Arrays.asList(array) ;
		int inputSum = 5 ;


		ApiRequest request =  new ApiRequest();
		request.setInputList(inputList);
		request.setInputSum(inputSum);

		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter() ;
		String json = ow.writeValueAsString(request) ;

		MvcResult result  = this.mockMvc.perform(post("/api/isCorrectAnswer")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isBadRequest()).andReturn();
		System.out.println(result.getResponse().getContentAsString()) ;
	}

	@Test
	public void isCorrectAnswerTestCorrectMissingInputNumbersToBeSummed() throws Exception {
		//    	Integer[] array= {-10, -24, 14, 15} ;
		//    	List<Integer> inputList = Arrays.asList(array) ;
		int inputSum = 5 ;


		ApiRequest request =  new ApiRequest();
		request.setInputList(null);
		request.setInputSum(inputSum);

		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter() ;
		String json = ow.writeValueAsString(request) ;

		MvcResult result  = this.mockMvc.perform(post("/api/isCorrectAnswer")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isBadRequest()).andReturn();
		System.out.println(result.getResponse().getContentAsString()) ;
	}

	@Test
	public void isCorrectAnswerTestCorrectMissingClientSum() throws Exception {
		Integer[] array= {-10, -24, 14, 15} ;
		List<Integer> inputList = Arrays.asList(array) ;
		Integer inputSum = null ;

		ApiRequest request =  new ApiRequest();
		request.setInputList(inputList);
		request.setInputSum(inputSum);

		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter() ;
		String json = ow.writeValueAsString(request) ;

		MvcResult result  = this.mockMvc.perform(post("/api/isCorrectAnswer")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isBadRequest()).andReturn();
		System.out.println(result.getResponse().getContentAsString()) ;
	}
    			
   
	
}
