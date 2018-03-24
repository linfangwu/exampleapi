package se.example.api.apiclient.errorhandler;

import java.io.IOException;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class MyErrorHandler implements ResponseErrorHandler {
	 public MyErrorHandler() {
		 super();
	 }
	@Override
	  public void handleError(ClientHttpResponse response) throws IOException {
	     //System.out.println(response.getBody()) ;
	  }

	  @Override
	  public boolean hasError(ClientHttpResponse response) throws IOException {
	     if ( response.getStatusCode().is2xxSuccessful() )  return true ;
	     
	     return false;
	  }

}
