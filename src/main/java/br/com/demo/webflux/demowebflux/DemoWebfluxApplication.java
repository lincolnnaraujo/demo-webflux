package br.com.demo.webflux.demowebflux;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = {
		MongoAutoConfiguration.class,
		MongoDataAutoConfiguration.class
})
public class DemoWebfluxApplication {

	private static final Logger log = LoggerFactory.getLogger(DemoWebfluxApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoWebfluxApplication.class, args);
		log.info("------------------------------ APP UP ------------------------------");
	}
}
