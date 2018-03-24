package se.example.api.apiserver.model;

import java.util.List;

public class ApiRequest {
	
	private List<Integer> inputList;
	private Integer inputSum;
	
	public List<Integer> getInputList() {
		return inputList;
	}
	public void setInputList(List<Integer> inputList) {
		this.inputList = inputList;
	}
	public Integer getInputSum() {
		return inputSum;
	}
	public void setInputSum(Integer inputSum) {
		this.inputSum = inputSum;
	}
	
}
