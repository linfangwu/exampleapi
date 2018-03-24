package test.se.example.api.apiserver.service;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import se.example.api.apiserver.App;
import se.example.api.apiserver.service.ExampleService;
import static org.junit.Assert.assertTrue;

/**
 * 
 * @author lwu
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
public class ExampleServiceTest {
	
	@Autowired
	private ExampleService service ;
	
	@Test
	public void generateRandomNumbersTest() {
		List<Integer> result = service.generateRandomNumbers() ;
		System.out.println(result) ;
		assertTrue(result.size()>1 && result.size()<=10) ;
		
	}
	
	@Test
	public void isResultCorrectTest() {
		Integer[] array= {-10, -24, 14, 15} ;
		List<Integer> inputList = Arrays.asList(array) ;
		int inputSum = -5 ;
		assertTrue(service.sum(inputList, inputSum)== -5) ;
		
	}

}
