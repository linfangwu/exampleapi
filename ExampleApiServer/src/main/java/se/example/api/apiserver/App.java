package se.example.api.apiserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author lwu
 *
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class App 
{
	private static Logger logger = LoggerFactory.getLogger(App.class) ;
	public static void main( String[] args )
    {
		SpringApplication.run(App.class, args);
		logger.info("spring boot api started ...");
    }
}
