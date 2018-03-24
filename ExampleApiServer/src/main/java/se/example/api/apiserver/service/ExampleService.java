package se.example.api.apiserver.service;

import java.util.List;

public interface ExampleService {
	/**
	 * 
	 * @return a random list of numbers, capacity of the list is from 2 to 20 
	 */
	List<Integer> generateRandomNumbers() ;
	/**
	 * calculate the sum of list, and compare the result
	 * @param inputList: a list of integer
	 * @param inputSum: sum from client
	 * @return boolean
	 */
    Integer sum(List<Integer> inputList, int inputSum) ;
}
