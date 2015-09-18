/**
 * 
 */
package com.excelsiorsoft.gatherer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author sleyzerzon
 *
 */
@SpringBootApplication
public class Application {
	
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);
		//app.addListeners(new ApplicationPidFileWriter());
		app.run(args);
		//app.run(BrokerFeedApplication.class, args);
	}

}
