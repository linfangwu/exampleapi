package se.example.api.apiserver.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * 
 * @author lwu
 *
 */
@Service
public class ExampleServiceImpl implements ExampleService{
	
	@Override
	public List<Integer> generateRandomNumbers(){
		int min = -100 ;
		int max = 100 ;
		//random length of the returned list, length>1 and length<=10
		int randomLength = (int)(Math.random()*10 +2) ;
		List<Integer> resultList =  new ArrayList<>();
		
		for (int i= 0; i<randomLength; i++ ) {
			int number = min + (int)(Math.random() * (max - min) + 1) ;
			resultList.add(number) ;
		}
		
		return resultList ;
	}
	
	@Override
	public Integer sum(List<Integer> inputList, int inputSum) {
		 return inputList.stream().mapToInt( i->i.intValue()).sum();
		
	}

}
